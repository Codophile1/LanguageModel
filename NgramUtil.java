package langModel;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Class NgramUtil: class containing useful functions to deal with n-grams.
 * 
 * @author N. Hernandez and S. Quiniou (2015)
 *
 */
public class NgramUtil {

	/**
	 * Method counting the number of words in a given sequence 
	 * (the sequence can be a n-gram or a sentence).
	 * 
	 * @param sequence the sequence to consider.
	 * @return the number of words of the given sequence.
	 */
	public static int getSequenceSize (String sequence) {
		String[] tab=sequence.trim().split(" ");
		return tab.length;
	}

	
	/**
	 * Method parsing a n-gram and returning its history, i.e. the n-1 preceding words.
	 * 
	 * Example: 
	 *   let the ngram be "l' historique de cette phrase";
	 *   the history will be given for the last word of the ngram, here "phrase":
	 *   if the order is 2 then the history will be "cette"; 
	 *   if the order is 3 then it will be "de cette".
	 * 
	 * @param ngram the n-gram to consider.
	 * @param order the order to consider for the n-gram.
	 * @return history of the given n-gram (the length of the history is order-1).  
	 */
	public static String getHistory (String ngram, int order) {
	
		String[] tab=ngram.trim().split(" ");
		String resultat="";
		
		for(int i=getSequenceSize(ngram)-2;i>=getSequenceSize(ngram)-order;i--){
			if(i>=0){
			resultat+=tab[i]+" ";
			}
		}
		String[] tabInverse=resultat.trim().split(" ");
		List<String> listTmpInverse = Arrays.asList(tabInverse);
		Collections.reverse(listTmpInverse);
		resultat="";
		for(String s :listTmpInverse){
			resultat+=s+" ";
		}
		return resultat;
		
		
	}


	/**
	 * Method decomposing the given sentence into n-grams of the given order.
	 * 
	 * This method will be used in the LanguageModel class for computing 
	 * the probability of a sentence as the product of the probabilities of its n-grams. 
	 * 
	 * Example
	 * given the sentence "a b c d e f g", with order=3,
	 * it will result in the following list:
	 * [a, a b, a b c, b c d, c d e, d e f, e f g] 
	 * 
	 * @param sentence the sentence to consider.
	 * @param order the maximal order for the n-grams to create from the sentence.
	 * @return the list of n-grams constructed from the sentence.
	 */
	public static List<String> decomposeIntoNgrams (String sentence, int order) {
		try{
		String[] tab=sentence.trim().split(" ");
		String tmpStr="";
		List<String> list = new LinkedList<String>();

		for(int i=0;i<tab.length;i++){//parcours de la sentence mot par mot
			tmpStr+=tab[i]+" ";//on ajoute le mot à la phrase
			list.add(getHistory(tmpStr,order)+tab[i]);//on prend les getHistory(phrase,order) 
			//et on ajoute le n eme mot de la phrase (car sinon on a que son historique)
			
		}
		return list;
			
			}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
//		}	
		return null;
	}
	
	
	/**
	 * Method parsing the given sentence and generate all the combinations of ngrams,
	 * by varying the order n between the given minOrder and maxOrder.
	 * 
	 * This method will be used in the NgramCount class for counting the ngrams 
	 * occurring in a corpus.
	 * 
	 * Algorithm (one possible algo...)
	 * initialize list of ngrams
	 * for n = minOrder to maxOrder (for each order)
	 * 	 for i = 0 to sentence.length-n (parse the whole sentence)
	 *     initialize ngram string parsedSentence
	 *     for j = i to i+n-1 (create a ngram made of the following sequence of words starting from i to i + the order size)
	 *       ngram = ngram + " " + sentence[j]
	 *     add ngramm to list ngrams 
	 * return list ngrams
	 * 
	 * Example
	 * given the sentence "a b c d e f g", with minOrder=1 and maxOrder=3, it will result in the following list:
	 * [a, b, c, d, e, f, g, a b, b c, c d, d e, e f, f g, a b c, b c d, c d e, d e f, e f g]
	 * 
	 * @param sentence the sentence from which to generate n-grams.
	 * @param minOrder the minimal order of the n-grams to create.
	 * @param maxOrder the maximal order of the n-grams to create.
	 * @return a list of generated n-grams from the sentence.
	 */
	public static List<String> generateNgrams (String sentence, int minOrder, int maxOrder) {
		List<String> result=new LinkedList<String>();
		List<String> list=new LinkedList<String>();
		for(int i=minOrder;i<=maxOrder;i++){
			list.addAll(decomposeIntoNgrams(sentence, i));//on ajoute le decomposeIntoNgrams pour chaque order
			for(int j=0;j<i-1;j++){//on enleve les éléments non complets (le début des listes d'order >1)
				list.remove(0);
				
			}
			result.addAll(list);
			 while (!list.isEmpty()) {//on vide la liste list pour recommencer la boucle sans créer une nouvelle liste à chaque boucle
			        ((LinkedList<String>) list).removeFirst();
			    }
		}
		return result;
	}

}
