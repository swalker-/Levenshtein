import java.io.PrintWriter;


public class LevenshteinPrinter {
	
	public static void printDetails(Levenshtein l) {
		PrintWriter writer = null;
		String s1 = l.strings()[0];
		String s2 = l.strings()[1];
	
		try {
		    writer = new PrintWriter("Levenshtein.txt", "utf-8");
		    writer.println("Strings received: ( " + s1 + ", " + s2 + " )");
		    writer.println("Edit distance: " + l.editDistance());
		    printDistanceTable(l, writer);
			printBacktraces(l, writer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
		   try {writer.close();} catch (Exception ex) { }
		}	
	}
	
	public static void printDistanceTable(Levenshtein l, PrintWriter writer) throws Exception {
		String s1 = l.strings()[0];
		String s2 = l.strings()[1];
		int length1 = s1.length();
		int length2 = s2.length();
		
		writer.println("\nDistance table:\n");
		for(int i=0; i<=length2+1; i++) {
			for(int j=0; j<=length1+1; j++) {
				if((i==0 && j==0) ||
				   (i==0 && j==1) ||
				   (i==1 && j==0)) {
					writer.write("#  ");
				}
				else if(i==0) {
					writer.printf("%-3s", s1.charAt(j-2));
				}
				else if(j==0) {
					writer.printf("%-3s", s2.charAt(i-2));
				}
				else {
					writer.printf("%-3d", l.distanceTable()[j-1][i-1]);
				}
			}
			writer.println();
		}
	}

	public static void printBacktraces(Levenshtein l, PrintWriter writer) throws Exception {
		String os1 = l.strings()[0];
		String os2 = l.strings()[1];
		int os1c, os2c;
		String s1, s2;
		writer.println("\nBacktraces:\n");
		for(String s : l.backtrace()) {
			s1 = "";
			s2 = "";
			os1c = os1.length()-1;
			os2c = os2.length()-1;
			writer.println(s);
			for(char c : s.toCharArray()) {
				switch(c) {
				case('D'):
					s1 += os1.charAt(os1c);
					s2 += os2.charAt(os2c);
					os1c--;
					os2c--;
					break;
				case('U'):
					s1 += '*';
					s2 += os2.charAt(os2c);
					os2c--;
					break;
				case('L'):
					s1 += os1.charAt(os1c);
					os1c--;
					s2 += ' ';
					break;
				default:
					break;
				}
			}
			writer.println(new StringBuilder(s1).reverse().toString());
			writer.println(new StringBuilder(s2).reverse().toString() + "\n");
		}
	}
}
