package langModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyNaiveLanguageModelTest {

	@Test
	public void test1() {
		LanguageModel lm = new MyNaiveLanguageModel();
		NgramCounts ngramCounts = new MyNgramCounts();
		ngramCounts.scanTextFile("data/train/sample-fr.txt",2);
		lm.setNgramCounts(ngramCounts);
		System.out.println(ngramCounts.getTotalWordNumber());
		System.out.println(ngramCounts.getNgramCounterSize());
		System.out.println(lm.getNgramProb("la plaine"));
	}

}
