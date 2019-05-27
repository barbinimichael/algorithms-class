import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// client
public class Client {

	// given a file
	// first line represents size of data structure
	// and type of union-find, where 0 = quick-find, 1 = quick-union, 2 = weighted quick-union
	// after, given pairs, determine if connected
	// if not, connect and print pair
	static void executeFile(File commandFile) {
		Scanner fileScanner = null;
		QuickUnionFind uF = null;
		int n = 0;
		int type = 0;

		// attempt to read file
		try {
			fileScanner = new Scanner(commandFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// retrieve size of data structure
		if(fileScanner.hasNextInt())
			n = fileScanner.nextInt();
		else 
			throw new RuntimeException("Improper file format");
		// retrieve type of quick-find
		if(fileScanner.hasNextInt())
			type = fileScanner.nextInt();
		else 
			throw new RuntimeException("Improper file format");

		if(type == 0) 
			uF = new QuickFind(n);
		else if(type == 1)
			uF = new QuickUnion(n);
		else {
			uF = new WeightedQuickUnion(n);
		}

		// begin quick-find
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
				System.out.println(p + " and " + q + " are now part of component " + uF.find(p) + " and " + uF.find(q));
			} else {
				System.out.println(p + " and " + q + " are already part of component " + uF.find(p));
			}
		}
	}


	public static void main(String[] args) {
		File commandFile = new File("C:/Users/micha/git/algorithsm-class/UnionFind/union_find_commands");
		
		executeFile(commandFile);
	}

}
