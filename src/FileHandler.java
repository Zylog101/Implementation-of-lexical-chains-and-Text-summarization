package lexprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
	private static FileHandler fh;
	private Scanner reader;
	private String currentLine;
	private String [] currentLineWords;
	private int currentLineWordsIndex;
	private int lineCounter;
	private FileHandler(){
		
	}
	
	public static FileHandler getInstance(){
		if(fh==null){
			fh = new FileHandler();
		}
		return fh;
	}
	
	public boolean initialize(String fileName){
		
		if(setupReader(fileName) == false){
			return false;
		}
		if(getNextLineWords()==false){
			return false;
		}
		
		return true;
	}
	
	private boolean setupReader(String fileName){
		if (reader == null){
			try {
				reader = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} 
		}
		return true;
	}
	
	private boolean getNextLineWords(){
		if(!reader.hasNext()){
			currentLine = null;
			currentLineWords = null;
			return false;
		}
		
		currentLine = reader.nextLine();
		lineCounter++;
		currentLineWords = currentLine.split("\\s+");
		for (int i = 0; i < currentLineWords.length; i++) {
			currentLineWords[i] = currentLineWords[i].replaceAll("[^\\w]", "");
		}
		currentLineWordsIndex = 0;
		return true;
	}
	
	public WordElement getNextWord(){
		if(currentLineWordsIndex < currentLineWords.length){
			return new WordElement(currentLineWords[currentLineWordsIndex++],currentLine,lineCounter);
		}else{
			if(getNextLineWords()==false){
				return null;
			}
			return new WordElement(currentLineWords[currentLineWordsIndex++],currentLine,lineCounter);
		}
		
//		if( stringReader.hasNext()){
//			return reader.next();
//		}
//		return null;
	}
}
