// Three number sum
// Write a function that takes in a non-empty array of distinct integers and an
// integer representing a target sum. The function should find all triplets in
// the array that sum up to the target sum and return a two-dimensional array of
// all these triplets. The numbers in each triplet should be ordered in ascending
// order, and the triplets themselves should be ordered in ascending order with
// respect to the numbers they hold.

// Sample Input= [12, 3, 1, 2, -6, 5, -8, 6]
// Sample output = [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]

import java.util.*;

class Program {
  public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		Arrays.sort(array);
		List<Integer[]> indices = new ArrayList<Integer[]>();
		
		for(int idx = 0; idx < array.length - 2; idx++) {
			int left = idx + 1;
			int right = array.length - 1;
			while(left < right) {
				int currSum = array[idx] + array[left] + array[right];
				if(currSum == targetSum) {
					Integer[] match = new Integer[]{array[idx], array[left], array[right]};
					indices.add(match);
					left++;
					right--;
				} else if(currSum < targetSum) {
					left++;
				} else {
					right--;
				}
			}
		}
		
		return indices ;
  }
}
