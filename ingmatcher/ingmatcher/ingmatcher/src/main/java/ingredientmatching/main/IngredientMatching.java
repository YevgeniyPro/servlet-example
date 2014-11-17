package ch.ingredientmatching.main;

import java.util.List;

import org.apache.log4j.Logger;

import ch.ingredientmatching.lexer.LexWord;
import ch.ingredientmatching.lexer.Lexer;
import ch.ingredientmatching.matcher.MatchWord;
import ch.ingredientmatching.matcher.Matcher;

public class IngredientMatching {
	static Logger log = Logger.getLogger(IngredientMatching.class);

	/**
	 * Don't forget to perform Graph.closeDataset()
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String input = args[0];
		List<MatchWord> results = runMatchingProcess(input);
	}

	
	/**
	 * normal workflow, like it should also look like in the main
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static List<MatchWord> runMatchingProcess(String input) throws Exception {
		Lexer lexer = new Lexer();
		List<LexWord> lexWords = lexer.runLexer(input);

		// run matcher
		Matcher matcher = new Matcher();
		List<MatchWord> matchWords = matcher.runMatcher(lexWords);

		// convert matcher output to something outputable
		return matchWords;
	}
	
	
}
