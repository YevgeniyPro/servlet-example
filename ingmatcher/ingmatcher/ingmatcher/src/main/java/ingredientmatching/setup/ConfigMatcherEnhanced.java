package ch.ingredientmatching.setup;


import ch.ingredientmatching.lexer.LexWord;

/**
 * config class for the matcher, takes the lenght of the input word into account
 * @author nicu
 *
 */
public class ConfigMatcherEnhanced {
	public final double DECLINE_TRESHHOLD_QUALITY_CLOSE_RESULT = 0.2;
	public final double DECLINE_QUALITY_CLOSE_RESULT = 0.2;
	public final double DECLIN_QUALITY_INSIDE_PARENTHESIS = 0.3;
	public final double DECLIN_QUALITY_AFTER_COMMA = 0.3;
	public final double DECLIN_QUALITY_WORD_PART = 0.15;
	public final double ENHANCE_QUALITY_DOUBLE_MATCH = 0.2;
	public final double ENHANCE_QUALITY_NOT_SINGLE_WORD = 0.0;
	public final double ENHANCE_QUALITY_DOUBLE_WORD_PART = 0.05;
	
	public double qualityFromLevenshtein(LexWord lexWord){
		return 1.0 - ((double)lexWord.getLevenshteinEaternity() / ((double)lexWord.getInputString().length()/1.2)/2);

	
	}

}
