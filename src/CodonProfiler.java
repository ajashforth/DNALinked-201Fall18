import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. 
	 * 
	 * Use a hashmap during the while loop so that I can more efficiently find 
	 * how many times each codon was in the strand.
	 * 
	 * Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>(); //holds appearance #'s temporarily
		int[] ret = new int[codons.length]; //value to be returned
				
			Iterator<Character> iter = strand.iterator();
			while (iter.hasNext()) {
				char a = iter.next();
				char b = 'z';  // not part of any real codon
				char c = 'z';
				if (iter.hasNext()) {
					b = iter.next();
				}
				if (iter.hasNext()) {
					c = iter.next();
				}
				String cod = ""+a+b+c;
				if (map.containsKey(cod)) {
					//if the map already has the codon, then we add one to the value
					//that is tracking the number of codons
					map.put(cod, map.get(cod) + 1);
				}
				else {
					//if the map does not contain the codon, put the codon in the map
					map.put(cod, 1);
				}
			}
			for (int i = 0; i < codons.length; i++) {
				if(map.containsKey(codons[i])) {
					//for every codon that was in the strand, put the number of
					//times the codon appeared into the int array
					ret[i] = map.get(codons[i]);
				}
				else {
					//if the codon did not appear, then it showed up zero times
					//put 0 into the int array
					ret[i] = 0;
				}
			}
		return ret;
	}
}

