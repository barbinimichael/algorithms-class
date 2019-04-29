import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// union find 
class UnionFind {

	int[] elements;
	
	// the constructor
	UnionFind(int n) {
		this.elements = new int[n];
	}
	
	// initialize data structure to have unique id's
	void initializeElements() {
		for(int i : this.elements) {
			this.elements[i] = i;
		}
	}
	
	// add connection between p and q
	void union(int p, int q) {
		
	}
	
	// determine if p and q are connected
	boolean connected(int p, int q) {
		return false;
	}
	
	// component identifier for p
	int find(int p) {
		return 0;
	}
	
	// determine the number of components
	int count() {
		return 0;
	}
	
	// client
	// given a file
	// first line represents size of data structure
	// after, given pairs, determine if connected
	// if not, connect and print pair
	public static void main(String[] args) {
		File commandFile = new File("C:/Users/micha/git/algorithsm-class/UnionFind/union_find_commands");
		Scanner fileScanner = null;
		UnionFind uF = null;
		
		// attempt to read file
		try {
			fileScanner = new Scanner(commandFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// retrieve size of data structure
		if(fileScanner.hasNextInt()) {
			int n = fileScanner.nextInt();
			uF = new UnionFind(n);
		}
		
		while(fileScanner.hasNextLine()) {
			int p, q;
			
			if(fileScanner.hasNextInt()) 
				p = fileScanner.nextInt();
			else
				throw new RuntimeException("Improper file format");
			if(fileScanner.hasNextInt()) 
				q = fileScanner.nextInt();
			else
				throw new RuntimeException("Improper file format");
			
			if(!uF.connected(p, q)) {
				uF.union(p, q);
				System.out.println("Connected " + p + " " + q);
			}
		}
	}
}
