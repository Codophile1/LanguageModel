package langModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLaplaceLanguageModelTest {

	@Test
	public void test1() {
		LanguageModel lm = new MyLaplaceLanguageModel();
		NgramCounts ngramCounts = new MyNgramCounts();
		ngramCounts.scanTextFile("data/train/sample-fr.txt",2);
		lm.setNgramCounts(ngramCounts);
		System.out.println(lm.getNgramProb("la plaine"));
	}

}
