package lexprocess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.data.relationship.Relationship;
import net.didion.jwnl.data.relationship.RelationshipFinder;
import net.didion.jwnl.data.relationship.RelationshipList;
import net.didion.jwnl.dictionary.Dictionary;

public class SimilarityFinder {

	private Dictionary dictionary;
	Chains chains;

	public void initialize(){
		try {
			JWNL.initialize(new FileInputStream("Properties.xml"));
			dictionary = Dictionary.getInstance();

		} catch (JWNLException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		chains = new Chains();
	}

	public boolean isNoun(String word){
		//System.out.println(String.format("checking if \" %s \" is a noun", word));
		IndexWord indexWord;
		try {
			indexWord = dictionary.lookupIndexWord(POS.NOUN, word);


			if(indexWord == null){
				return false; 
			}

//			Synset senses[] = indexWord.getSenses();

//			for (Synset set : senses) {
//				System.out.println(indexWord + ": " + set.getGloss());
//			}

		} catch (JWNLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isSimilar(String word1, String word2){
		//System.out.println(String.format("checking if %s is similar to %s",word1,word2));
		//need to check on the basis of synonym
		IndexWord indexWord;
		try {
				//need to check on the basis of synonym
				indexWord = dictionary.getIndexWord(POS.NOUN, word1);
				Synset[] word1Synset = indexWord.getSenses();
				
				indexWord = dictionary.getIndexWord(POS.NOUN, word2);
				Synset[] word2Synset = indexWord.getSenses();
				
				if(isSynonym(word1Synset,word2Synset)){
					//System.out.println("Synonym relation found");
					return true;
				}
				
				if(isAntonym(word1Synset,word2Synset)){
					//System.out.println("Antonym relation found");
					return true;
				}	
				
				//is Hypernym
				if(isOfType(word1Synset,word2Synset,PointerType.HYPERNYM)){
					//System.out.println("Hypernym relation found");
					return true;
				}
				
				// is Hyponym
				if(isOfType(word1Synset,word2Synset,PointerType.HYPONYM)){
					//System.out.println("Hyponym relation found");
					return true;
				}

			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	private boolean isAntonym(Synset[] word1Synsets, Synset[] word2Synsets) {
		//System.out.println("Checking antonym relation");
		try {
			for(Synset word1Synset : word1Synsets ){
				for(Synset word2Synset : word2Synsets){
					RelationshipList list = RelationshipFinder.getInstance().findRelationships(word1Synset,word2Synset, PointerType.ANTONYM);

					if(!list.isEmpty()){
						return true;
					}
				}
			}
			
		} catch (JWNLException e) {
			e.printStackTrace();
		}
//		Set<String>words1 = new HashSet<String>();
//		for(Synset word1Synset : word1Synsets){
//			for(Word word : word1Synset.getWords()){
//				words1.add(word.getLemma());	
//				System.out.println(word.getLemma());
//			}
//		}
//		System.out.println("\nSecond set\n");
//		Set<String>words2 = new HashSet<String>();
//		for(Synset word2Synset : word2Synsets){
//			for(Word word : word2Synset.getWords()){
//				words2.add(word.getLemma());
//				System.out.println(word.getLemma());
//			}
//		}
//		
//		return !Collections.disjoint(words1,words2);
		return false;
	}

	private boolean isOfType(Synset[] word1Synsets, Synset[] word2Synsets, PointerType type) {
		//System.out.println(String.format("Checking relation is of type %s",type.getLabel()));
		try {
			for(Synset word1Synset : word1Synsets ){
				for(Synset word2Synset : word2Synsets){
					RelationshipList list = RelationshipFinder.getInstance().findRelationships(word1Synset,word2Synset, type);

					if(!list.isEmpty()){
						if(((Relationship) list.get(0)).getDepth() == 1){
							return true;
						}
					}
				}
			}
			
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isSynonym(Synset[] word1Synsets, Synset[] word2Synsets) {
		//System.out.println("Checking synonym relation");
		
		Set<String>words1 = new HashSet<String>();
		for(Synset word1Synset : word1Synsets){
			for(Word word : word1Synset.getWords()){
				words1.add(word.getLemma());	
				//System.out.println(word.getLemma());
			}
		}
		//System.out.println("\nSecond set\n");
		Set<String>words2 = new HashSet<String>();
		for(Synset word2Synset : word2Synsets){
			for(Word word : word2Synset.getWords()){
				words2.add(word.getLemma());
				//System.out.println(word.getLemma());
			}
		}
		
		return !Collections.disjoint(words1,words2);
	}

}
