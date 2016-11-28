package langModel;

import java.util.List;


/**
 * Class MyNaiveLanguageModel: class implementing the interface LanguageModel by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 * 
 * @author ... (2015)
 *
 */
public class MyNaiveLanguageModel implements LanguageModel {
	/**
	 * The NgramCounts corresponding to the language model.
	 */
	protected NgramCounts ngramCounts;
	
	/**
	 * The vocabulary of the language model.
	 */
	protected Vocabulary vocabulary;
	
	
	/**
	 * Constructor.
	 */
	public MyNaiveLanguageModel(){
		//TODO
	}
	

	@Override
	public void setNgramCounts(NgramCounts ngramCounts) {
		this.ngramCounts = ngramCounts;
		
	}

	@Override
	public int getLMOrder() {
		return this.ngramCounts.getMaximalOrder();
	}

	@Override
	public int getVocabularySize() {
		return this.vocabulary.getSize();
	}

	@Override
	public Double getNgramProb(String ngram) {
		if(this.ngramCounts.getNgramCounterSize()<=0){
			return 0.0;
		}
		return (double) (this.ngramCounts.getCounts(ngram)*1.0/this.ngramCounts.getNgramCounterSize())*1.0;
	}

	@Override
	public Double getSentenceProb(String sentence) {
		List<String> ngrams = NgramUtil.decomposeIntoNgrams(sentence, this.getLMOrder());
		Double prob = 1.0;
		for(String ngram : ngrams){
			prob=prob*this.getNgramProb(ngram);
		}
		return prob;
	}

}
