package ch.ingredientmatching.matcher;

import java.util.ArrayList;
import java.util.List;

import ch.ingredientmatching.lexer.LexWord;
import ch.ingredientmatching.setup.Config;

/**
 * in the end there a match is a single id with an assigned weight. maybe
 * several index searches reveal the same id, therefore they all should have an
 * effect on the weight of that particular id
 * 
 * @author nicu
 * 
 */
public class MatchWord{
	// 0 <= quality <= 1 
	private double quality = 0;
	private int id = Config.ID_ERROR_CODE;
	private List<LexWord> lexWords = new ArrayList<LexWord>();

	public MatchWord(int id, double quality, LexWord lexWord){
		this.id = id;
		setQuality(quality);
		this.lexWords.add(lexWord);
	}
	
	public double getQuality() {
		return quality;
	}
	/**
	 * rounds the quality to second decimal place
	 * @param quality
	 */
	protected void setQuality(double quality) {
		if(quality < 0){
			this.quality = 0;
		}else if(quality > 1){
			this.quality = 1;
		}else{
			this.quality = (Math.round(quality*100.0))/100.0;
		}
	}
	public int getId() {
		return id;
	}

	public List<LexWord> getLexWords() {
		return lexWords;
	}
	protected void addLexWord(LexWord graphWord) {
		
		this.lexWords.add(graphWord);
	}
}
