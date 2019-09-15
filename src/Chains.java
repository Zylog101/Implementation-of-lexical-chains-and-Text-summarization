package lexprocess;

import java.util.Iterator;
import java.util.Vector;

public class Chains {
	Vector<Vector<ChainElement>> lexicalChains;

	Chains(){
		lexicalChains = new Vector<Vector<ChainElement>>();
	}
	
	public Vector<ChainElement> getChainAt(int index){
		
		if(lexicalChains.isEmpty() || (index > lexicalChains.size()-1) || (index < 0) )
			return null;
		
		return lexicalChains.get(index);
	}
	
	public boolean addANewChain( WordElement word ){
		lexicalChains.add(new Vector<ChainElement>());
		return addToChain(lexicalChains.size()-1,word);
	}
	
	public boolean addToChain(int chainIndex, WordElement word){
		if((chainIndex >= lexicalChains.size()) || (chainIndex <0)){
			return false;
		}
	
		Vector<ChainElement>chain = lexicalChains.get(chainIndex);
		for(Iterator iter = chain.iterator();iter.hasNext();){
			ChainElement chainElement = (ChainElement)iter.next();
			if(chainElement.word.getWord().equals(word.getWord())){
				chainElement.freq++;
				return true;
			}
		}
		chain.add(new ChainElement(word, 1));
		return true;
	}
	
	public int getLexicalChainsSize(){
		return lexicalChains.size();
	}
	
	public ChainElement getLargestChainElement(int chainNumber){
		if(chainNumber>lexicalChains.size()){
			return null;
		}
		Vector<ChainElement> chain = lexicalChains.get(chainNumber);
		
		ChainElement largestChainElement = chain.get(0);
		for(ChainElement chainElement:chain){
			if(chainElement.freq > largestChainElement.freq){
				largestChainElement = chainElement;
			}
		}
		return largestChainElement;
	}
	@Override
	public String toString() {
		int chainCount = 1;
		StringBuilder builder = new StringBuilder();
		Iterator<Vector<ChainElement>> chainsIt = lexicalChains.iterator();
		
		while(chainsIt.hasNext()){
			builder.append(String.format("\nchain %d: ",chainCount++));
			Vector<ChainElement>chain = chainsIt.next();
			Iterator<ChainElement> chainIt = chain.iterator();
			
			while(chainIt.hasNext()){
				builder.append(String.format("%s, ",chainIt.next().toString()));
			}
		}
		return builder.toString(); 
	}
}
