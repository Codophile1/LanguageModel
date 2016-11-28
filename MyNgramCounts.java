package langModel;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Class MyNgramCounts: class implementing the interface NgramCounts. 
 * 
 * @author N. Hernandez and S. Quiniou (2015)
 *
 */
public class MyNgramCounts implements NgramCounts {
	/**
	 * The maximal order of the n-gram counts.
	 */
	protected int order;

	/**
	 * The map containing the counts of each n-gram.
	 */
	protected Map<String,Integer> ngramCounts;

	/**
	 * The total number of words in the corpus.
	 * In practice, the total number of words will be increased when parsing a corpus 
	 * or when parsing a NgramCounts file (only if the ngram encountered is a unigram one).
	 */
	protected int nbWordsTotal;
	
	
	/**
	 * Constructor.
	 */
	public MyNgramCounts(){
		this.ngramCounts = new HashMap<String, Integer>();
	}


	/**
	 * Setter of the maximal order of the ngrams considered.
	 * 
	 * In practice, the method will be called when parsing the training corpus, 
	 * or when parsing the NgramCounts file (using the maximal n-gram length encountered).
	 * 
	 * @param order the maximal order of n-grams considered.
	 */
	void setMaximalOrder(int order) {
		this.order = order;
	}

	
	@Override
	public int getMaximalOrder() {
		return this.order;
	}

	
	@Override
	public int getNgramCounterSize() {
		return this.ngramCounts.size();
	}

	
	@Override
	public int getTotalWordNumber(){
		return this.nbWordsTotal;
	}
	
	
	@Override
	public Set<String> getNgrams() {
		return this.ngramCounts.keySet();
	}

	
	@Override
	public int getCounts(String ngram) {
		if(this.ngramCounts.containsKey(ngram)){
			return this.ngramCounts.get(ngram);
		}
		return 0;
	}
	

	@Override
	public void incCounts(String ngram) {
		if(this.ngramCounts.containsKey(ngram)){
			this.ngramCounts.replace(ngram, this.ngramCounts.get(ngram)+1);
		}else{
			this.ngramCounts.put(ngram, 1);
		}
	}

	
	@Override
	public void setCounts(String ngram, int counts) {
		if(this.ngramCounts.containsKey(ngram)){
			this.ngramCounts.replace(ngram, counts);
		}else{
			this.ngramCounts.put(ngram, counts);
		}
	}


	@Override
	public void scanTextString(String text, int maximalOrder) {
		List<String> ngrams = NgramUtil.decomposeIntoNgrams(text, maximalOrder);
		for(String ngram : ngrams){
			this.incCounts(ngram);
		}
	}

	
	@Override
	public void scanTextFile(String filePath, int maximalOrder) {
		List<String> file = MiscUtil.readTextFileAsStringList(filePath);
		for(String ligne : file){
			this.scanTextString(ligne, maximalOrder);
		}
	}

	
	@Override
	public void writeNgramCountFile(String filePath) {
		String fileContent = "";
		for(String ngram : this.ngramCounts.keySet()){
			fileContent+=ngram+"\t"+this.ngramCounts.get(ngram);
		}
		MiscUtil.writeFile(fileContent, filePath, false);
	}

	
	@Override
	public void readNgramCountsFile(String filePath) {
		List<String> lines = MiscUtil.readTextFileAsStringList(filePath);
		for(String line : lines){
			String[] data = line.split("\t");
			this.ngramCounts.put(data[0], Integer.parseInt(data[0]));
		}
	}

}
