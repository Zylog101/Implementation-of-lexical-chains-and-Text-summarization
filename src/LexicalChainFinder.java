package lexprocess;

import java.util.Vector;

public class LexicalChainFinder {
	SimilarityFinder similarityFinder;
	FileHandler fileHandler;
	Chains chains;
	private static final String EMPTY_STRING = "";

	public LexicalChainFinder() {

		similarityFinder = null;
		fileHandler = null;
		chains = null;
	}

	public boolean initialize() {
		similarityFinder = new SimilarityFinder();
		fileHandler = FileHandler.getInstance();
		chains = new Chains();

		similarityFinder.initialize();
		if (fileHandler.initialize("sentences") == false) {
			return false;
		}

		/*
		 * bunch of checks ensuring chains object works fine
		 * 
		 * if( chains.addToChain(0,word)== false) System.out.println("correct");
		 * System.out.println(chains.getLexicalChainsSize()); if(
		 * chains.getChainAt(0)== null) System.out.println("correct"); if(
		 * chains.addANewChain(word)== true) System.out.println("correct");
		 * System.out.println(chains.getLexicalChainsSize());
		 * System.out.println(chains.getChainAt(0).size()+"\t"+chains.getChainAt
		 * (0).get(0).toString()); System.out.println(chains.toString());
		 * 
		 * if(chains.addToChain(0, word)==true) System.out.println("correct");
		 * System.out.println(chains.toString());
		 * 
		 */

		return true;
	}

	public Chains computeChains() {

		WordElement word = getNextNoun();

		while (word != null) {
			if (word.getWord().equals(EMPTY_STRING)) {
				word = getNextNoun();
				continue;
			}

			if (addWordToChain(word) == false) {
				System.out.println("failed to add word");
				return chains;
			}
			word = getNextNoun();

		}
		return chains;
	}

	private boolean addWordToChain(WordElement word) {
		int chainSize = chains.getLexicalChainsSize();

		if (chainSize == 0) {
			chains.addANewChain(word);
			return true;
		}

		for (int i = 0; i < chainSize; i++) {
			if (isWordBelongingToChain(chains.getChainAt(i), word.getWord())) {
				if (chains.addToChain(i, word) == false) {
					System.out.println(String.format("unable to add to chain %d", i));
					return false;
				} else {
					return true;
				}
			}
		}
		
		return chains.addANewChain(word);
	}

	private boolean isWordBelongingToChain(Vector<ChainElement> chain, String word) {
		for (ChainElement chainElement : chain) {
			if (similarityFinder.isSimilar(chainElement.word.getWord(), word)) {
				return true;
			}
		}
		return false;
	}

	private WordElement getNextNoun() {
		WordElement word = fileHandler.getNextWord();
		if (word == null) {
			return null;
		}
		
		if (!similarityFinder.isNoun(word.getWord())) {
			return new WordElement(EMPTY_STRING,null,-1);
		}
		if (word.getWord().length()<3){
			return new WordElement(EMPTY_STRING,null,-1);
		}

		return word;
	}

}
