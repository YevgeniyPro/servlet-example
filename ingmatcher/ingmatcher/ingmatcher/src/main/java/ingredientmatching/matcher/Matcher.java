package ch.ingredientmatching.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ch.ingredientmatching.lexer.LexWord;
import org.apache.log4j.Logger;

import ch.ingredientmatching.setup.Config;

/**
 * sorts all the results from the index and possible graph search.
 * 
 * quality: ... to 0.2 -> very bad result, wrong
 * 
 * 0.2 to 0.4 -> bad result
 * 
 * 0.4 to 0.8 -> good result, could be correct, but still needs manual
 * validation
 * 
 * 0.8 to ... -> very good result, probably correct
 * 
 * in Setup.Config, the config class for the matcher can be chosen. the
 * matcher's config class defines the parameters that influence the quality
 * calculation and a method is defined which returns a quality, only from the
 * levenshtein distance
 * 
 * 
 * @author nicu
 * 
 */
public class Matcher {
	static Logger log = Logger.getLogger(Matcher.class);

	private List<MatchWord> matches;
	private Comparator<MatchWord> comp = new MatchWordComparator();

	public List<MatchWord> runMatcher(List<LexWord> lexWordsInput) {
		matches = new ArrayList<MatchWord>();
		// create the MatchWordList where every occurring id is represented by a
		// MatchWord
		for (LexWord lexWord : lexWordsInput) {
			addLexWordToMatches(lexWord);
		}

		// decline quality if close together
		declineQualityOfCloseResults();

		// sort matches
		Collections.sort(matches, comp);
		return matches;
	}

	/**
	 * if in the end two different results have about the same quality it is
	 * harder to decide which the better one is. therefore the qualities
	 * decline. they certainly can't be very good results anymore, but need
	 * manual evaluation, therefore decline quality. if there are two very good
	 * results, their quality should be declined and than should be lower than
	 * 0.8 in order to be processed manually
	 */
	private void declineQualityOfCloseResults() {
		log.info("declineQualityOfCloseResults");
		List<Integer> ids = new ArrayList<Integer>();
		for (MatchWord outer : matches) {
			log.debug("outer: " + outer.getId());
			for (MatchWord inner : matches) {
				log.debug("inner: " + inner.getId());
				if (Math.abs(inner.getQuality() - outer.getQuality()) <= Config.configMatcher.DECLINE_TRESHHOLD_QUALITY_CLOSE_RESULT
						&& !inner.equals(outer)) {

					if (!ids.contains(outer.getId())) {
						log.debug("added to ids: " + outer.getId());
						ids.add(outer.getId());
					}
				}
			}
		}
		// now decline quality of all being in ids
		for (MatchWord matchWord : matches) {
			if (ids.contains(matchWord.getId())) {
				log.debug("declined quality of " + matchWord.getId());
				matchWord.setQuality(matchWord.getQuality()
						- Config.configMatcher.DECLINE_QUALITY_CLOSE_RESULT);
			}
		}
	}

	/**
	 * adds a new lexWord to the resulting matches by either creating and
	 * adding a new MatchWord or adding the LexWord to an existing MatchWord
	 * and adjusting the quality
	 * 
	 * @param lexWord
	 */
	private void addLexWordToMatches(LexWord lexWord) {
		log.info("addLexWordToMatches");
		boolean notInMatches = true;
		for (MatchWord matchWord : matches) {
			if (matchWord.getId() == lexWord.getEaternityIndexId()) {
				// is in matches
				addToExistingMatch(lexWord, matchWord);
				notInMatches = false;
			}
		}
		if (notInMatches) {
			log.debug(lexWord.getInputString()
					+ " was not already in matches, added now");
			// did not exist in matches, therefore has to be added
			double quality = simpleQuality(lexWord);
			matches.add(new MatchWord(lexWord.getEaternityIndexId(), quality,
					lexWord));
		}
	}

	/**
	 * if an id occurs again the quality enhances and the maximum of the
	 * existing quality or the one from the new lexWord is taken. a word that
	 * occurs 3 times with 0.4 which is quite bad would still need manual
	 * evaluation, a word occurring twice with 0.6 would now be a very good hit.
	 * a special case has to be made for splitted words, because it is very
	 * likely that one part of the to two parts from the original word gets the
	 * same result as the original word, given the original word has a result.
	 * In this case the maximum is taken.
	 * 
	 * 
	 * 
	 * @param lexWord
	 * @param matchWord
	 */
	private void addToExistingMatch(LexWord lexWord, MatchWord matchWord) {
		log.info(lexWord.getInputString() + " addToExistingMatch");
		// TODO: maybe needs improvement
		double matchQuality = matchWord.getQuality();
		double newQuality = simpleQuality(lexWord);
		int newPosition = lexWord.getPossitionInInputString();
		boolean wordPartRelation = false;
		// check if somehow related meaning is the original word of a word part
		// or wise versa (meaning they have the same position)
		for (LexWord matchLexWord : matchWord.getLexWords()) {
			if (matchLexWord.getPossitionInInputString() == newPosition) {
				wordPartRelation = true;
			}
		}
		if (wordPartRelation) {
			log.debug("wordPartRelation");
			// if the two results are closely related take the maximum of the
			matchWord.setQuality(Math.max(newQuality, matchQuality) + Config.configMatcher.ENHANCE_QUALITY_DOUBLE_WORD_PART);
		} else {
			log.debug("normal case, not wordPartRelation, enhance quality");
			// "normal" case of adjusting the quality
			// TODO: the max made it worse!!!
			matchWord.setQuality(Math.max(matchQuality, newQuality)
					+ Config.configMatcher.ENHANCE_QUALITY_DOUBLE_MATCH);
		}
		// in any case, add the new lexWord to the matchWord
		matchWord.addLexWord(lexWord);
	}

	/**
	 * first attempt to get a quality, looks at different parameters from the
	 * lexical analysis. if a word was inside parenthesis or after a comma, it
	 * can't be a perfect hit any more, therefore decline quality, also if it's
	 * a wordpart. if it's not a single word, enhance quality. quality is
	 * originally derived from the levenshtein distance. the methods which does
	 * this is in the matcher's config file
	 * 
	 * 
	 * @param lexWord
	 * @return
	 */
	private double simpleQuality(LexWord lexWord) {
		log.info("simpleQuality");

		double quality = Config.configMatcher.qualityFromLevenshtein(lexWord);

		if (lexWord.isAfterComma()) {
			quality = quality - Config.configMatcher.DECLIN_QUALITY_AFTER_COMMA;
		}
		if (lexWord.isInsideParentheses()) {
			quality = quality
					- Config.configMatcher.DECLIN_QUALITY_INSIDE_PARENTHESIS;
		}
		if (lexWord.isWordPart()) {
			quality = quality - Config.configMatcher.DECLIN_QUALITY_WORD_PART;
		}
		if (!lexWord.isSingleWord()) {
			quality = quality
					+ Config.configMatcher.ENHANCE_QUALITY_NOT_SINGLE_WORD;
		}
		log.debug(lexWord.getInputString() + " quality = " + quality);
		return quality;
	}

}
