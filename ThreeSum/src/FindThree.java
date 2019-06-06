import java.util.Arrays;
import java.util.Random;

class FindThree {
	int[] nums;

	FindThree(int[] nums) {
		this.nums = nums;
	}

	// Given array of integers, find three that sume to zero 
	int bruteForcer() {
		int numSolution = 0;
		for(int i = 0; i < this.nums.length; i++) {
			for(int j = i + 1; j < this.nums.length; j++) {
				for(int k = j + 1; k < this.nums.length; k++) {
					if((this.nums[i] + this.nums[j] + this.nums[k]) == 0) {
						numSolution++;
					}
				}
			}
		}
		return numSolution;
	}
	
	int improved() {
		int numSolution = 0;
		Arrays.sort(this.nums);
		for(int i = 0; i < this.nums.length; i++) {
			for(int j = i + 1; j < this.nums.length; j++) {
				int other = -1 * (this.nums[i] + this.nums[j]);
				if(Arrays.binarySearch(this.nums, j + 1, this.nums.length, other) >= 0) {
					numSolution++;
				}
				
			}
		}
		return numSolution;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		int numSize = 1000;
		int[] nums = new int[numSize];
		
		// n distinct integers
		for(int i = 0; i < numSize; i++) {
			int value = rand.nextInt(10000);
			while(!checkUnique(nums, value)) {
				value = rand.nextInt(10000);
			}
			boolean negative = rand.nextBoolean();
			if(negative)
				value *= -1;
			nums[i] = value;
		}
		
		FindThree findThree = new FindThree(nums);
		System.out.println(findThree.bruteForcer());
		System.out.println(findThree.improved());
		
	}
	
	static boolean checkUnique(int[] nums, int n) {
		for(int num : nums) {
			if(n == num) {
				return false;
			}
		}
		return true;
	}
}
