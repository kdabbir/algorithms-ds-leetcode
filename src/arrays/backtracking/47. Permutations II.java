// 47. Permutations II: https://leetcode.com/problems/permutations-ii/

// Medium
// Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

// Example 1:

// Input: nums = [1,1,2]
// Output:
// [[1,1,2],
//  [1,2,1],
//  [2,1,1]]
// Example 2:

// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

// Constraints:

// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
                List<List<Integer>> output = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backTrack(new ArrayList<>(), new boolean[nums.length], nums, output);
        return output;
    }
    
    public void backTrack(List<Integer> currPath, boolean[] used, int[] nums, List<List<Integer>> output) {
        if(currPath.size() == nums.length) {
            output.add(new ArrayList<>(currPath));
        } else {
            for(int idx = 0; idx < nums.length; idx++) {
                if(used[idx] || idx > 0 && nums[idx] == nums[idx - 1] && !used[idx - 1]) continue;
                currPath.add(nums[idx]);
                used[idx] = true;
                backTrack(currPath, used, nums, output);
                currPath.remove(currPath.size() - 1);
                used[idx] = false;
            }
        }
    }
}

// Time complexity:  O(n x n!)
// Space complexity: O(n!)