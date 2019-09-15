# Implementation-of-lexical-chains-and-Text-summarization
The relation between different parts of a text plays a vital role in understanding the information present in the text. Nouns and Pronouns significantly help in understanding the topic of a text and summary of the text depends on the word usage and its relations in the text. Thus forming lexical chains enable us to extract words which are having similar sense and creates the necessary data to form a summary of the text.

Thus the implementation aims at extracting lexical chains from the original text and perform text summarization to extract concise meaningful summary of the original text.

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

![image](https://github.com/Zylog101/Implementation-of-lexical-chains-and-Text-summarization/blob/master/Image/NLPInp.JPG)

**Lexical Chains**

![image](https://github.com/Zylog101/Implementation-of-lexical-chains-and-Text-summarization/blob/master/Image/NLPChain.JPG)

**Created Summary**

![image](https://github.com/Zylog101/Implementation-of-lexical-chains-and-Text-summarization/blob/master/Image/NLPSum.JPG)
