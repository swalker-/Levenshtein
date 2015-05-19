import java.util.List;


public final class Entry {
	private final List<Character> steps;
	private final int distance;
	
	public Entry(int distance, List<Character> steps) {
		this.steps = steps;
		this.distance=distance;
	}
	
	public int distance() {
		return distance;
	}
	
	public boolean hasU() {
		return steps.contains('U');
	}
	
	public boolean hasL() {
		return steps.contains('L');
	}
	
	
	public boolean hasD() {
		return steps.contains('D');
	}
}
