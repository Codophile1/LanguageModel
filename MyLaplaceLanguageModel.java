package langModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Class MyLaplaceLanguageModel: class inheriting the class MyNaiveLanguageModel by creating 
 * a n-gram language model using a Laplace smoothing.
 * 
 * @author ... (2015)
 *
 */
public class MyLaplaceLanguageModel extends MyNaiveLanguageModel {

	@Override
	public Double getNgramProb(String ngram) {
	
		if(this.ngramCounts.getNgramCounterSize()<=0){
			return 0.0;
		}
		return (double) ((this.ngramCounts.getCounts(ngram)*1.0+1)/(this.ngramCounts.getTotalWordNumber())*1.0+this.ngramCounts.getNgramCounterSize());
	}
}

