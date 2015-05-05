import static org.junit.Assert.*;

import org.junit.Test;


public class LevenshteinTest {

	@Test
	public void getASimpleEditDistance() {
		String s1 = "Test1";
		String s2 = "Test2";
		assertNotNull(Levenshtein.editDistance(s1, s2));
	}
	
	@Test
	public void twoCharactersAreEqaul() {
		String s1 = "a";
		String s2 = "a";
		assertEquals(0, Levenshtein.editDistance(s1, s2));
	}
	
	@Test
	public void twoCharactersAreNotEqual() {
		String s1 = "a";
		String s2 = "b";
		assertNotEquals(0, Levenshtein.editDistance(s1, s2));
	}
	
	@Test
	public void canDetectInsertion() {
		String s1 = "";
		String s2 = "a";
		String s3 = "ab";
		assertEquals(1, Levenshtein.editDistance(s2, s1));
		assertEquals(2, Levenshtein.editDistance(s3, s1));
	}
	
	@Test
	public void canDetectDeletion() {
		String s1 = "";
		String s2 = "a";
		String s3 = "ab";
		assertEquals(1, Levenshtein.editDistance(s1, s2));
		assertEquals(2, Levenshtein.editDistance(s1, s3));
	}
	
	@Test
	public void canDetectSubstitution() {
		String s1 = "a";
		String s2 = "b";
		assertEquals(2, Levenshtein.editDistance(s1, s2));
	}
	
	@Test
	public void canGetSimpleLevenshteinMatrix(){
		String s1 = "a";
		String s2 = "a";
		int[][] lv = new int[][]{{0, 1},
								 {1, 0}};
		assertArrayEquals(lv, Levenshtein.values(s1, s2));
	}
	
	@Test
	public void canGetComplexLevenshteinMatrix() {
		String s1 = "Intention";
		String s2 = "Execution";
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
			
		assertArrayEquals(lv, Levenshtein.values(s1, s2));
	}

}
