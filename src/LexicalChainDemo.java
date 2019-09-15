package lexprocess;

public class LexicalChainDemo {
	
	
	public static void main(String args[]){
		LexicalChainFinder chainFinder;
		chainFinder = new LexicalChainFinder();
		if (chainFinder.initialize() == false){
			System.out.println("failed to initialize chain finder");
			return;
		}
		Chains chains= chainFinder.computeChains();
		System.out.println(chains.toString());
		String summary = SummaryCreator.getSummaryForChain(chains);
		System.out.println(summary);
		
	}
}
