import static org.junit.Assert.*;

import org.junit.Test;


public class LevenshteinTest {

	@Test
	public void getAnEditDistance() {
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

}
