
// weighted quick-union
// number of array accesses
// initialize- n
// union- log(n)
// find- log(n)
// 
public class WeightedQuickUnion extends QuickUnion {

	// each index represents node
	// entry represents size of sub tree
	int[] size;

	// quick-union based on size of sub trees
	WeightedQuickUnion(int n) {
		super(n);
		this.size = new int[n];
	}

	// initialize data structure to have unique id's
	void initializeElements() {
		for(int i = 0; i < this.elements.length; i++) {
			this.elements[i] = i;
			this.size[i] = 1;
		}
	}

	// add connection between p and q
	// determine which root to change based on which tree smaller
	void union(int p, int q) {
		int pRoot = this.find(p);
		int qRoot = this.find(q);

		if(pRoot == qRoot) {
			return;
		} else if (this.size[pRoot] < size[qRoot]) {
			this.elements[pRoot] = qRoot;
			this.size[qRoot] += this.size[pRoot];
		} else {
			this.elements[qRoot] = pRoot;
			this.size[pRoot] += this.size[qRoot];
		}
	}

	// component identifier for p
	// return root with path compression
	int find(int p) {
		while(p != this.elements[p])
			p = this.elements[this.elements[p]];
		return p;
	}	
}
