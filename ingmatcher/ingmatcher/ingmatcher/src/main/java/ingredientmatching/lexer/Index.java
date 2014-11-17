package ch.ingredientmatching.lexer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ch.ingredientmatching.setup.Config;

/**
 * may only be used by the lexer to query the eaternity and the graph index.
 * only initialize ones
 * 
 * @author nicu
 * 
 */
public class Index {
	static Logger log = Logger.getLogger(Index.class);

	// analyzer needs to be public because it is needed for index creation
	public static Analyzer analyzer = new NGramAnalyzer();;
	private QueryParser queryParser;
	private IndexSearcher eaternitySearcher;
	private IndexReader eaternityReader;
	private SpellChecker spellChecker;
	private QueryParser idQueryParser;

	public Index() throws IOException {
		// can be used for eaternity and graph index
		// has to use the same analyzer as for index creation
		// search the field "labelLowerCase, but return the uppercase
		queryParser = new QueryParser(Config.LUCENE_VERSION, "labelLowerCase",
				analyzer);
		idQueryParser = new QueryParser(Config.LUCENE_VERSION, "id", analyzer);

		// eaternity index
		File eaternityDirectory = new File(Config.EATERNITY_INDEX_PATH);
		eaternityReader = DirectoryReader.open(FSDirectory
				.open(eaternityDirectory));
		eaternitySearcher = new IndexSearcher(eaternityReader);

		File spellcheckerDirectory = new File(Config.SPELLCHECKER_INDEX_PATH);
		Directory directory = FSDirectory.open(spellcheckerDirectory);
		spellChecker = new SpellChecker(directory);
	}

	/**
	 * suggests similar words, measuring in levenshtein, not n-gramm distance
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	protected List<String> searchSpellCheckerEaternityIndex(String input)
			throws IOException {
		String[] suggestions = spellChecker.suggestSimilar(Lexer.eliminateSpecialChars(input.toLowerCase()),
				Config.NUMBER_OF_RESULTS_TO_RETURN);
		List<String> res = new ArrayList<String>(Arrays.asList(suggestions));
		return res;
	}

	/**
	 * returns the id for a found match from the eaternity index. if it could
	 * not be found, it returns an id with value Integer.MAX_VALUE
	 * 
	 * @param searchString
	 * @return returns -1 if it couldn't find the id
	 * @throws ParseException
	 * @throws IOException
	 */
	protected int getEaternityIndexId(String searchString)
			throws ParseException, IOException {
		log.info("getEaternityIndexId");
		log.debug("Searching EaternityIndex to receive id for <" + searchString
				+ ">");

		Query query = queryParser.parse(Lexer.eliminateSpecialChars(searchString.toLowerCase()));
		TopDocs topDocsResults = eaternitySearcher.search(query, 1);
		ScoreDoc[] hits = topDocsResults.scoreDocs;
		Document doc;
		int id = Config.ID_ERROR_CODE;

		if (hits.length == 1) {
			doc = eaternitySearcher.doc(hits[0].doc);
			id = Integer.parseInt(doc.get("id"));
			log.debug("found id: <" + id + ">");
		} else {
			log.error("couldn't get eaternity id from index, searched for: "
					+ searchString);
		}

		return id;
	}

	/**
	 * may return empty list if no result found. searches in the eaternity index
	 * for possible matches
	 * 
	 * @param string
	 * @return may be empty
	 * @throws IOException
	 * @throws ParseException
	 */
	protected List<String> searchEaternityIndex(String string)
			throws IOException, ParseException {
		log.debug("Search in EATERNITY_INDEX");
		return searchIndex(eaternitySearcher, Lexer.eliminateSpecialChars(string.toLowerCase()));
	}

	private List<String> searchIndex(IndexSearcher searcher, String searchString)
			throws IOException, ParseException {
		log.info("searchIndex");
		List<String> results = new ArrayList<String>();
		log.debug("Searching for <" + searchString + ">");

		// lowerCase the searchInput
		Query query = queryParser.parse("\"" + searchString.toLowerCase()
				+ "\"~" + Config.NUMBER_OF_RESULTS_TO_RETURN);
		TopDocs topDocsResults = searcher.search(query,
				Config.NUMBER_OF_RESULTS_TO_RETURN);
		ScoreDoc[] hits = topDocsResults.scoreDocs;
		Document doc;
		String label;

		int scoreDocSize = hits.length;
		for (int i = 0; i < scoreDocSize; i++) {
			doc = searcher.doc(hits[i].doc);
			label = doc.get("labelUpperCase");
			log.debug("Result: <" + label + ">");
			results.add(label);
		}

		return results;
	}

	/**
	 * 
	 * @param id
	 * @return the name stored in the eaternity-db for a given id
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String getEaternityNameFromId(int id) throws IOException, ParseException {
		log.info("getEaternityNameFromId");

		Query query = idQueryParser.parse(Integer.toString(id));
		TopDocs topDocsResults = eaternitySearcher.search(query, 1);
		ScoreDoc[] hits = topDocsResults.scoreDocs;
		Document doc;
		String name = "";

		if(hits.length > 0){
			doc = eaternitySearcher.doc(hits[0].doc);
			name = doc.get("labelUpperCase");
			log.debug("Result: <" + name + ">");
		}

		return name;

	}
}
