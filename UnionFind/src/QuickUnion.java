
// quick-union union-find 
// number of array accesses
// initialize- n
// union- n
// find- n
// O(n^2)
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
		while(p != this.elements[p])
			p = this.elements[p];
		return p;
	}
}
