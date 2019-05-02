
// quick-find union-find
// number of array accesses
// initialize- n
// union- n
// find- 1
// O(n^2)
public class QuickFind extends QuickUnionFind {

	// each index represents a node 
	// entries represents a connected component
	QuickFind(int n) {
		super(n);
	}

	// add connection between p and q
	// change all entries with p's id to q's id
	void union(int p, int q) {
		int pID = this.elements[p];
		int qID = this.elements[q];

		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] == pID)
				elements[i] = qID;
		}
	}

	// component identifier for p
	int find(int p) {
		return this.elements[p];
	}
}
