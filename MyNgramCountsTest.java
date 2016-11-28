package langModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyNgramCountsTest {

	@Test
	public void test() {
		NgramCounts nC = new MyNgramCounts();
		nC.incCounts("bonjour la pluie");
		nC.incCounts("bonjour la pluie");
		nC.incCounts("bonjour la pluie");
		nC.incCounts("bonjour la pluie");
		assertEquals(4,nC.getCounts("bonjour la pluie"));
	}
	@Test
	public void test2() {
		NgramCounts nC = new MyNgramCounts();
		assertEquals(0,nC.getCounts("Hello"));
	}
	@Test
	public void test3() {
		MyNgramCounts nC = new MyNgramCounts();
		nC.setMaximalOrder(5);
		assertEquals(5,nC.getMaximalOrder());
	}
	@Test
	public void test4() {
		MyNgramCounts nC = new MyNgramCounts();
		nC.setCounts("la pluie",112);
		assertEquals(112, nC.getCounts("la pluie"));
	}
	@Test
	public void test5() {
		MyNgramCounts nC = new MyNgramCounts();
		nC.scanTextFile("data/train/sample-fr.txt",2);
		assertEquals(1, nC.getCounts("la plaine"));
	}
	@Test
	public void test6() {
		MyNgramCounts nC = new MyNgramCounts();
		nC.scanTextFile("data/train/sample-fr.txt",2);
		nC.writeNgramCountFile("data/test/bigramm-en.txt");
	}

}
