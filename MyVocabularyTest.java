package langModel;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class MyVocabularyTest {

	@Test
	public void test() {
		Vocabulary voc = new MyVocabulary();
		voc.addWord("Hello");
		voc.removeWord("Hello");
		assertEquals("hello2", false, voc.contains("Hello"));
	}
	@Test
	public void test2(){
		Vocabulary voc = new MyVocabulary();
		voc.addWord("Hello");
		voc.removeWord("Hello");
		assertEquals("hello2", false, voc.contains("Hello"));
	}
	@Test
	public void test3(){
		Vocabulary voc = new MyVocabulary();
		HashSet test = new HashSet<String>();
		test.add("Hello");
		test.add("my");
		test.add("name");
		test.add("is");
		voc.scanNgramSet(test);
		assertEquals(true, voc.contains("is"));
	}
	@Test
	public void test4(){
		Vocabulary voc = new MyVocabulary();;
		voc.readVocabularyFile("data/train/sample-en.txt");
		assertEquals(true, voc.contains("tree"));
	}
	@Test
	public void test5(){
		Vocabulary voc = new MyVocabulary();;
		voc.readVocabularyFile("data/train/sample-en.txt");
		voc.writeVocabularyFile("data/test/test1.txt");
	}

}
