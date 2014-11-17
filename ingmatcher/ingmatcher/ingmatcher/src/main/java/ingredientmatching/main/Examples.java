package ch.ingredientmatching.main;



import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.search.suggest.analyzing.FuzzySuggester;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ch.ingredientmatching.lexer.Index;
import ch.ingredientmatching.lexer.InputBlackList;
import ch.ingredientmatching.setup.Config;

public class Examples {
	static Logger log = Logger.getLogger(Examples.class);

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * TEST whole folder
		 * 
		 */
//		Test test = new Test();
//		test.runTestFolder(Config.TEST_INPUT_FOLDER);
		
		
		/*
		 * TEST single benchmark test from file
		 * 
		 */
//		Test test = new Test();
//		test.runTestFile("/Users/nicu/git/files/benchmark_tests/custom.txt");
//		test.closePrintWriter();
		
		
		
		
		/*
		 * recreate the index (delete the contents (only the contents), of the indices folders first)
		 * 
		 */
//		CreateLuceneIndex index = new CreateLuceneIndex();
//		index.createEaternityIndex();
//		index.createGraphIndex();
//		index.eaternityToFile();
//		index.createEaternitySpellcheckerIndex();
		
		
		
		/*
		 * mark gurke in graph, from eaternity
		 * 
		 */
//		Label labelGurken = new Label("\"Gurke\"@de");
//		MarkGraphFromEaternity.markInGraph("Gurken", labelGurken, 150);
		
		/*
		 * delete eaternity's gurke in graph
		 * 
		 */
//		Resource resource = new Resource("<http://de.dbpedia.org/resource/Gurke>");
//		MarkGraphFromEaternity.deleteEaternityEntryFromResource(resource);
		
		
		/*
		 * delete eaternity mark from graph by id
		 * 
		 */
//		MarkGraphFromEaternity.deleteIdFromGraphAndLogFile(111);
		
		/*
		 * try to mark everything from eaternity automatically in the graph
		 * 
		 */
//		MarkGraphFromEaternity.initialyMarkGraph();
//		MarkGraphFromEaternity.initialyAddEaternityLinks();
		


		

		/*
		 *very important! always close the dataset before terminating the application terminates
		 */
	}

}
