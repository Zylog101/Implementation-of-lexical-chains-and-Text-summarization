package lexprocess;

public class WordElement  implements Comparable<WordElement>{
	private String word; 
	private String sentence;
	private int lineNumber;
	WordElement(String word,String sentence, int lineNumber){
		this.word = word;
		this.sentence = sentence;
		this.lineNumber = lineNumber;
	}
	
	public String getWord(){
		return word;
	}
	public String getSentence(){
		return sentence;
	}
	public int getLineNumber(){
		return lineNumber;
	}

	@Override
	public int compareTo(WordElement wordElement) {
		if(this.lineNumber>wordElement.lineNumber){
			return 1;
		}else if(this.lineNumber<wordElement.lineNumber){
			return -1;
		}
		return 0;
	}
	
}
