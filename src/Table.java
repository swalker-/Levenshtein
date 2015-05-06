
public class Table {
	private Entry[][] table;
	
	public Table(int rows, int cols) {

		table = new Entry[rows][cols];
	}
	
	public Entry getEntry(int row, int col) {
		return table[row][col];
	}
	
	public void setEntry(int row, int col, Entry entry) {
		table[row][col] = entry;
	}
}
