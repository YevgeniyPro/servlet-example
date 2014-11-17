package ch.ingredientmatching.setup;

import ch.ingredientmatching.lexer.LexWord;

/**
 * config class for the matcher. simplest possible form, takes levenshtein directly as quality
 * @author nicu
 *
 */
public class ConfigMatcherSimple {
	public final double DECLINE_TRESHHOLD_QUALITY_CLOSE_RESULT = 0.1;
	public final double DECLINE_QUALITY_CLOSE_RESULT = 0.2;
	public final double DECLIN_QUALITY_INSIDE_PARENTHESIS = 0.2;
	public final double DECLIN_QUALITY_AFTER_COMMA = 0.2;
	public final double DECLIN_QUALITY_WORD_PART = 0.15;
	public final double ENHANCE_QUALITY_DOUBLE_MATCH = 0.2;
	public final double ENHANCE_QUALITY_NOT_SINGLE_WORD = 0.1;
	public final double ENHANCE_QUALITY_DOUBLE_WORD_PART = 0.0;
	
	public double qualityFromLevenshtein(LexWord lexWord){
		return 1.0 - (lexWord.getLevenshteinEaternity() / 10.0);
	}
}
