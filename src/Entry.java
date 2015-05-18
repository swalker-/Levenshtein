import java.util.List;


public final class Entry {
	private final boolean u, l, d;
	private final int distance;
	
	public Entry(int distance, List<Character> steps) {
		u = steps.contains('u');
		l = steps.contains('l');
		d = steps.contains('d');
		this.distance=distance;
	}
	
	public int distance() {
		return distance;
	}
	
	public boolean hasU() {
		return u;
	}
	
	public boolean hasL() {
		return l;
	}
	
	
	public boolean hasD() {
		return d;
	}
}
