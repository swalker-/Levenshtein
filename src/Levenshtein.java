
public class Levenshtein {
	public static int editDistance(String s1, String s2)
	{
		if (s1.equals(s2))
			return 0;
		return 1;
	}
}
