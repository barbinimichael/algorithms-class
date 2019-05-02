
// quick-union union-find 
public class QuickUnion extends QuickUnionFind {
	
	// each index represents a node
	// entries represents the parent of node
	// the root of i is id[id[...id[i]...]]
	QuickUnion(int n) {
		super(n);
	}

	// add connection between p and q
	// set the id of p's root to the id of q's root
	void union(int p, int q) {
		this.elements[this.find(p)] = this.find(q); 
	}

	// component identifier for p
	// return root
	int find(int p) {
		int current = this.elements[p];
		int parent = this.elements[current];
		
		if(current == parent) {
			return current;
		} else {
			return this.find(parent);
		}
	}
}
