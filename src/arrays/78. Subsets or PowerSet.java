// 78. Subsets: https://leetcode.com/problems/subsets/
// Medium

// Given an integer array nums of unique elements, return all possible subsets (the power set).

// The solution set must not contain duplicate subsets. Return the solution in any order

// Example 1:

// Input: nums = [1,2,3]
// Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// Example 2:

// Input: nums = [0]
// Output: [[],[0]]
 

// Constraints:

// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// All the numbers of nums are unique.

// Cascading
// Intuition(Check doc for visual)

// Let's start from empty subset in output list. At each step one takes new integer into consideration and generates new subsets from the existing ones.

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> outputSet = new ArrayList<List<Integer>>();
		outputSet.add(new ArrayList<Integer>());
		for(int value: nums) {
			int len = outputSet.size();
			for(int idx = 0; idx < len; idx++) {
				List<Integer> currSet = new ArrayList<Integer>(outputSet.get(idx));
				currSet.add(value);
				outputSet.add(currSet);
			}
		}
		return outputSet;
    }
}

// Time: O(N * 2 ^ N)
// Space: O(N * 2 ^ N)