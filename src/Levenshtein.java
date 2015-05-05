
public class Levenshtein {
	
	private static void setupArray(int[][] lValues, int length1, int length2)
	{
		for(int i=0; i<=length1; i++) {
			lValues[i][0] = i;
		}
		for(int j=0; j<=length2; j++) {
			lValues[0][j] = j;
		}
	}
	
	public static int[][] values(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[][] lValues = new int[length1+1][length2+1];
		int insertionValue;
		int deletionValue;
		int substitutionValue;
		int smallestValue;
		setupArray(lValues, length1, length2);
		for(int i=1; i<= length1; i++) {
			for(int j=1; j<=length2; j++) {
				insertionValue = lValues[i][j-1] + 1;
				smallestValue = insertionValue;
				deletionValue = lValues[i-1][j] + 1;
				substitutionValue = lValues[i-1][j-1];
				if(s1.charAt(i-1) != s2.charAt(j-1)) {
					substitutionValue += 2;
				}
				if(deletionValue < smallestValue)
					smallestValue = deletionValue;
				if(substitutionValue < smallestValue)
					smallestValue = substitutionValue;
				if(j==1 && i==4)
				{
					System.out.println(s1.charAt(i-1) + "; " + s2.charAt(j-1));
				}
				lValues[i][j] = smallestValue;
			}
		}
		return lValues;
	}
	
	public static int editDistance(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if (s1.equals(s2))
			return 0;
		else
		{		
			if (length1 > length2)
				return length1-length2;
			else if (length1 < length2)
				return length2-length1;
			else
				return 2;
		}
	}
}
