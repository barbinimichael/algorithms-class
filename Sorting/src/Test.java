public class Test {
	public static void main(String[] args) {
		int numTest = 50;
		int count = 10;

		Insertion<Integer> insert = new Insertion<Integer>();
		testSort(insert, numTest, count);

		Merge<Integer> merge = new Merge<Integer>();
		testSort(merge, numTest, count);

		QuickSort<Integer> quick = new QuickSort<Integer>();
		testSort(quick, numTest, count);

		Selection<Integer> select = new Selection<Integer>();
		testSort(select, numTest, count);

		ShellSort<Integer> shell = new ShellSort<Integer>();
		testSort(shell, numTest, count);
	}

	private static void testSort(Sort<Integer> sort, int numTest, int count) {
		long totalInsertionTime = 0;
		for (int i = 0; i < numTest; i++) {
			prepareSort(sort, count);
			long startTime = System.nanoTime();
			sort.sort();
			totalInsertionTime += (System.nanoTime() - startTime);
		}
		System.out.println(totalInsertionTime);
	}

	private static void prepareSort(Sort<Integer> sort, int count) {
		for (int i = 0; i < count; i++)
			sort.addItem((int) Math.random() * 1000);
	}
}
