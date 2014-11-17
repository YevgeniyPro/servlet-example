package ch.ingredientmatching.lexer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;

import ch.ingredientmatching.setup.Config;

/**
 * implements an analyzer which is needed to index and afterwards query the
 * index with n-grams. it also implements a StopFilter which uses the list from
 * ch.ingredientmatching.lex/FoodProductBlacklist.java. the words from this list
 * are not indexed or queried. this helps for performance and should also
 * simplify the lexer
 * 
 * @author nicu
 * 
 */
public class NGramAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {

		Tokenizer source = new NGramTokenizer(reader, Config.MIN_N_GRAM_SIZE, Config.MAX_N_GRAM_SIZE);
		CharArraySet charArraySet = StopFilter.makeStopSet(Config.LUCENE_VERSION,
				InputBlackList.blackList, true);
		TokenStream filter = new StopFilter(Config.LUCENE_VERSION, source, charArraySet);

		return new TokenStreamComponents(source, filter);
	}
}