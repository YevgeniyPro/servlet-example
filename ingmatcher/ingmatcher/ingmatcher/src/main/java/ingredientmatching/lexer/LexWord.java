package ch.ingredientmatching.lexer;

import ch.ingredientmatching.setup.Config;


/**
 * is filled by the lexer. lexer decompositions input into several of these
 * InputWord-objects and searches them in the graph and eaternity index. it later is
 * read by the Matcher to decide which InputWords are good hits and which not
 * 
 * @author nicu
 * 
 */
public class LexWord extends ParentWord{
	
	public LexWord(String string){
		setInputString(string);
	}
	
	//fields
	private String eaternityIndex = "";
	//-1 means that no id was found, has to be checked before used!
	private int eaternityIndexId = Config.ID_ERROR_CODE;
	private int levenshteinEaternity = Integer.MAX_VALUE;

	//getters
	public String getEaternityIndex() {
		return eaternityIndex;
	}
	public int getEaternityIndexId() {
		return eaternityIndexId;
	}
	public int getLevenshteinEaternity() {
		return levenshteinEaternity;
	}

	//setters
	protected void setEaternityIndex(String eaternityIndex) {
		this.eaternityIndex = eaternityIndex;
	}
	protected void setEaternityIndexId(int eaternityIndexId) {
		this.eaternityIndexId = eaternityIndexId;
	}
	protected void setLevenshteinEaternity(int levenshteinEaternity) {
		this.levenshteinEaternity = levenshteinEaternity;
	}

}
