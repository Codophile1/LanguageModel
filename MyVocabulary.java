package langModel;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class MyVocabulary: class implementing the interface Vocabulary.
 * 
 * @author ... (2015)
 *
 */
public class MyVocabulary implements Vocabulary {
	/**
	 * The set of words corresponding to the vocabulary.
	 */
	protected Set<String> vocabulary;

	
	/**
	 * Constructor.
	 */
	public MyVocabulary(){
		this.vocabulary = new TreeSet<String>();
	}
	
	
	@Override
	public int getSize() {
		return this.vocabulary.size();
	}

	@Override
	public Set<String> getWords() {
		return this.vocabulary;
	}

	@Override
	public boolean contains(String word) {
		return this.vocabulary.contains(word);
	}

	@Override
	public void addWord(String word) {
		if(!this.contains(word)){
			this.vocabulary.add(word);
		}
		
	}

	@Override
	public void removeWord(String word) {
		if(this.contains(word)){
			this.vocabulary.remove(word);
		}
		
	}

	@Override
	public void scanNgramSet(Set<String> ngramSet) {
		for(String word : ngramSet){
			this.addWord(word);
		}
	}

	@Override
	public void readVocabularyFile(String filePath) {
		List<String> lignes = MiscUtil.readTextFileAsStringList(filePath);
		for(String ngram : lignes){
			String[] words = ngram.trim().split(" ");
			for(String word : words){
				this.addWord(word);
			}
		}
		
	}

	@Override
	public void writeVocabularyFile(String filePath) {
		String fileContent = "";
		for(String word : this.vocabulary){
			fileContent+=word+"\n";
		}
		MiscUtil.writeFile(fileContent, filePath, false);
	}

}
