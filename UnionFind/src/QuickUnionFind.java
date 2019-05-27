import java.util.ArrayList;

// quick union-find
public abstract class QuickUnionFind {

	// data structure
	int[] elements;

	QuickUnionFind(int n) {
		this.elements = new int[n];
		this.initializeElements();
	}

	// initialize data structure to have unique id's
	void initializeElements() {
		for(int i = 0; i < this.elements.length; i++) {
			this.elements[i] = i;
		}
	}

	// add connection between p and q
	abstract void union(int p, int q);

	// determine if p and q are connected
	// are part of the same connected component
	boolean connected(int p, int q) {
		return this.find(p) == this.find(q);
	}

	// component identifier for p
	abstract int find(int p);

	// determine the number of connected components
	int count() {
		int count = 0;
		ArrayList<Integer> identified = new ArrayList<Integer>();

		for(int i = 0; i < elements.length; i++) {
			int current = this.find(i);

			if(!identified.contains(current)) {
				identified.add(current);
				count++;
			}
		}
		return count;
	}
}
