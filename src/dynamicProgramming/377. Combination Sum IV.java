// 377. Combination Sum IV: https://leetcode.com/problems/combination-sum-iv/

// Medium

// Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

// The answer is guaranteed to fit in a 32-bit integer.

// Example 1:

// Input: nums = [1,2,3], target = 4
// Output: 7
// Explanation:
// The possible combination ways are:
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)
// Note that different sequences are counted as different combinations.
// Example 2:

// Input: nums = [9], target = 3
// Output: 0

// Constraints:

// 1 <= nums.length <= 200
// 1 <= nums[i] <= 1000
// All the elements of nums are unique.
// 1 <= target <= 1000\


// Back tracking solution- Memory exceeded.
class Solution {
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        backTrack(nums, new ArrayList<>(), output, 0,  target);
        return output.size();
    }

    public void backTrack(int[] nums, ArrayList<Integer> curr, List<List<Integer>> output, int processIdx, int currSum) {
        if (currSum < 0) {
            return;
        }
        else if(currSum == 0) {
            output.add(new ArrayList<>(curr));
        } else {
            for(int idx = 0; idx < nums.length; idx++) {
                curr.add(nums[idx]);
                backTrack(nums, curr, output, idx, currSum - nums[idx]);
                curr.remove(curr.size() - 1);
            }
        }
    }
}