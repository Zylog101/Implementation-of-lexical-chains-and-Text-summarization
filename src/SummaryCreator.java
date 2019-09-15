package lexprocess;

import java.util.Collections;
import java.util.Vector;

public class SummaryCreator {
	
	public static String getSummaryForChain(Chains chains){
		Vector<ChainElement> summaryChain = new Vector<ChainElement>();
		
		for(int i=0; i < chains.getLexicalChainsSize(); i++){
			summaryChain.addElement(chains.getLargestChainElement(i));
		}
		
		StringBuilder builder = new StringBuilder();
		//limiting summary length to 5 lines max
		int maxSummaryLines = 5;
		
		//sort based on frequency
		Collections.sort(summaryChain);
		Vector<WordElement> dataChain = new Vector<WordElement>();
		
		for(ChainElement chainElement : summaryChain){
			dataChain.add(chainElement.word);

			if(maxSummaryLines-- == 0){
				break;
			}

		}
		
		//sorting based on line number
		Collections.sort(dataChain);
		int repeatedLineNumber = 0;
		for(WordElement wordElement : dataChain){
			if(wordElement.getLineNumber() == repeatedLineNumber){
				continue;
			}
			repeatedLineNumber = wordElement.getLineNumber();
			builder.append(wordElement.getSentence()+"\n"+wordElement.getLineNumber()+"\n");
		}
		return builder.toString();
	}

}
