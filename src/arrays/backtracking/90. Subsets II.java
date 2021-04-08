// 90. Subsets II: https://leetcode.com/problems/subsets-ii/

// Medium


// Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

// The solution set must not contain duplicate subsets. Return the solution in any order.



// Example 1:

// Input: nums = [1,2,2]
// Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
// Example 2:

// Input: nums = [0]
// Output: [[],[0]]


// Constraints:

// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> outputList = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrackBuild(nums, outputList, new ArrayList<Integer>(), 0);
        return outputList;
    }

    public void backtrackBuild(int[] nums, List<List<Integer>> output, List<Integer> tempList, int start) {
        output.add(new ArrayList<>(tempList));
        for(int idx = start; idx < nums.length; idx++) {
            if(idx > start && nums[idx] == nums[idx - 1]) continue;
            tempList.add(nums[idx]);
            backtrackBuild(nums, output, tempList, idx + 1);
            tempList.remove(tempList.size() - 1);
        }
    }}
    // Time complexity:  O(n x n!)
// Space complexity: O(n!)