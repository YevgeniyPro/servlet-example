package ch.ingredientmatching.lexer;

public class ParentWord {
	//fields
		private String inputString;
		//first position has value 0!
		private int possitionInInputString = 0;
		private boolean afterComma = false;
		private boolean insideParentheses = false;
		
		private boolean wordPart = false;
		//if it contains whitespaces its not a single word
		private boolean singleWord = true;
		
		//getters
		public String getInputString() {
			return inputString;
		}
		
		public int getPossitionInInputString() {
			return possitionInInputString;
		}
		public boolean isAfterComma() {
			return afterComma;
		}
		public boolean isInsideParentheses() {
			return insideParentheses;
		}
		
		public boolean isWordPart() {
			return wordPart;
		}
		public boolean isSingleWord() {
			return singleWord;
		}
		
		//setters
		protected void setInputString(String inputString) {
			this.inputString = inputString;
		}
		protected void setPossitionInInputString(int possitionInInputString) {
			this.possitionInInputString = possitionInInputString;
		}
		protected void setAfterComma(boolean afterComma) {
			this.afterComma = afterComma;
		}
		protected void setInsideParentheses(boolean insideParentheses) {
			this.insideParentheses = insideParentheses;
		}
		
		protected void setWordPart(boolean wordPart) {
			this.wordPart = wordPart;
		}
		protected void setSingleWord(boolean singleWord) {
			this.singleWord = singleWord;
		}

}
