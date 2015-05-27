import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class LevenshteinTest {
	
	private String s1;
	private String s2;
	private Levenshtein l1;
	private Levenshtein l2;
	
	@Before
	public void setup() {
		s1 = "a";
		s2 = "b";
		l1 = new Levenshtein(s1);
		l2 = new Levenshtein(s1);
		l1.comparisonString(s1);
		l2.comparisonString(s2);
	}

	@Test
	public void getASimpleEditDistance() {
		assertNotNull(l1.editDistance());
	}
	
	@Test
	public void twoCharactersAreEqaul() {
		assertEquals(0, l1.editDistance());
	}
	
	@Test
	public void twoCharactersAreNotEqual() {
		assertNotEquals(0, l2.editDistance());
	}
	
	@Test
	public void canDetectInsertion() {
		String s1 = "";
		String s2 = "a";
		String s3 = "ab";
		Levenshtein l1 = new Levenshtein(s2);
		l1.comparisonString(s1);
		Levenshtein l2 = new Levenshtein(s3);
		l2.comparisonString(s1);
		assertEquals(1, l1.editDistance());
		assertEquals(2, l2.editDistance());
	}
	
	@Test
	public void canDetectDeletion() {
		String s1 = "";
		String s2 = "a";
		String s3 = "ab";
		Levenshtein l1 = new Levenshtein(s1);
		l1.comparisonString(s2);
		Levenshtein l2 = new Levenshtein(s1);
		l2.comparisonString(s3);
		assertEquals(1, l1.editDistance());
		assertEquals(2, l2.editDistance());
	}
	
	@Test
	public void canDetectSubstitution() {
		assertEquals(2, l2.editDistance());
	}
	
	@Test
	public void canGetSimpleLevenshteinMatrix(){
		int[][] lv = new int[][]{{0, 1},
								 {1, 0}};
		assertArrayEquals(lv, l1.distanceTable());
	}
	
	@Test
	public void canGetComplexLevenshteinMatrix() {
		String s1 = "Intention";
		String s2 = "Execution";
		Levenshtein l1 = new Levenshtein(s1);
		l1.comparisonString(s2);
		int[][] lv = new int[][]{{0,1,2,3,4,5,6,7,8,9},
								 {1,2,3,4,5,6,7,6,7,8},
								 {2,3,4,5,6,7,8,7,8,7},
								 {3,4,5,6,7,8,7,8,9,8},
								 {4,3,4,5,6,7,8,9,10,9},
								 {5,4,5,6,7,8,9,10,11,10},
								 {6,5,6,7,8,9,8,9,10,11},
								 {7,6,7,8,9,10,9,8,9,10},
								 {8,7,8,9,10,11,10,9,8,9},
								 {9,8,9,10,11,12,11,10,9,8}};
			
		assertArrayEquals(lv, l1.distanceTable());
	}
	
	@Test
	public void canGetStrings() {
		String[] eStrings = {s1, s2};
		String[] aStrings = l2.strings();
		for(int i=0; i<eStrings.length; i++) {
			assertEquals(eStrings[i], aStrings[i]);
		}
	}
	
	@Test
	public void canGetBackTrace() {
		String s1 = "lucky";
		String s2 = "unlucky";
		Levenshtein l1 = new Levenshtein(s1);
		l1.comparisonString(s2);
		List<String> eBacktrace = new ArrayList<String>(); 
		eBacktrace.add("DDDDDUU");
		List<String> aBacktrace = l1.backtrace();
		
		for(String s : eBacktrace) {
			System.out.println("Trace: " + s);
		}
		
		assertEquals(eBacktrace.size(), aBacktrace.size());
		for(int i=0; i<eBacktrace.size(); i++)
		{
			assertEquals(eBacktrace.get(i), aBacktrace.get(i));
		}
	}
	
}
