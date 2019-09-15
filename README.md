# Implementation-of-lexical-chains-and-Text-summarization
Implemented lexical chains and Text summarization to extract concise meaningful summary of the original text 

**Overview:**
* Forming Lexical Chains
  * Scan the text word by word
  * Identify the relation among already scanned words and the new word being scanned ( By comparing synsets of words ( Antonym, Hypernym, Hyponym, and Synonym ))
  * Represent the relation of words by means of lexical chains
  * Lexical chains represent the discourse structures present in the text under consideration
  * Wordnet is used to identify different senses of the word and to identify the relation among words
  * JWNL acts as an interface to Wordnet
* From lexical chain construction of summary
  * Each element in a chain has a frequency associated with it
  * Among the created chains, highest frequency element from each chain is extracted
  * This list of highest frequency elements are sorted in descending order such that highest is at the top of the list
  * Top 5 elements are chosen for creating summary
  * These 5 elements are sorted in ascending order based on the line number associated with the word
  * Among these 5 elements from the first element, a summary is constructed such that no sentence is repeated

**Example**
**Input**
![image]()

**Lexical Chains**
![image]()

**Created Summary**
![image]()
