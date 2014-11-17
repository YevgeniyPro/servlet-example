package ch.ingredientmatching.setup;

import org.apache.log4j.Logger;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * all absolute accesses to the file system are defined here. stores values that
 * are used by several classes and have to be the same in all of them, or values
 * that are important for tuning the results.
 * 
 * @author nicu
 * 
 */
public final class Config {
	static Logger log = Logger.getLogger(Config.class);
	// package: lexer
	// Index
	public static final Version LUCENE_VERSION = Version.LUCENE_4_10_2; // has to
																	// match the
																	// library
																	// version
	public static final int MIN_N_GRAM_SIZE = 2;
	public static final int MAX_N_GRAM_SIZE = 2;
	public static final String EATERNITY_INDEX_PATH = "src/main/resources/eaternityIndex";
	public static final String SPELLCHECKER_INDEX_PATH = "src/main/resources/spellcheckerIndex";
	public static final int NUMBER_OF_RESULTS_TO_RETURN = 15;

	
	// has to be a number that is never used as an ID! (negative numbers are
	// used in the eaternityDb), if an Id has this value, it means it was not
	// found (at least until now)
	public static final int ID_ERROR_CODE = 0;

	// package: main
	public static final String TEST_OUTPUT_FILE_PATH = "src/test/resources/test_output_file.txt";
	public static final String TEST_INPUT_FOLDER = "src/test/resources/benchmark_tests";

	// package: matcher
	//decide which config file should be used for the matcher
	public static ConfigMatcherEnhanced configMatcher = new ConfigMatcherEnhanced();
}
