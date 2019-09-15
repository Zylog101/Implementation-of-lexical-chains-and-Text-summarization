package lexprocess;

public class ChainElement implements Comparable<ChainElement>{
	WordElement word;
	int freq;
	public ChainElement() {}
	public ChainElement(WordElement word, int freq){
		this.word = word; this.freq = freq;
	} 
	
	public String toString(){
		return String.format("%s(%d)",word.getWord(),freq);
		//return String.format("%s",word);
	}
	@Override
	public int compareTo(ChainElement chainElement) {
		if(this.freq>chainElement.freq){
			return -1;
		}else if(this.freq<chainElement.freq){
			return 1;
		}
		return 0;
	}
}