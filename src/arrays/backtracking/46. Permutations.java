// 46. Permutations: https://leetcode.com/problems/permutations/

// Medium

// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

// Example 1:

// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// Example 2:

// Input: nums = [0,1]
// Output: [[0,1],[1,0]]
// Example 3:

// Input: nums = [1]
// Output: [[1]]
 

// Constraints:

// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// All the integers of nums are unique.

// Using backtracking, trying to generate all possible permutations

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        backTrack(nums, new ArrayList<>(), output);
        return output;
    }
    
    public void backTrack(int[] nums, ArrayList<Integer> tempList, List<List<Integer>> output) {
        if(tempList.size() == nums.length){
            output.add(new ArrayList<>(tempList));
        } else {
            for(int idx = 0; idx < nums.length; idx++) {
                if(tempList.contains(nums[idx])) continue;
                tempList.add(nums[idx]);
                backTrack(nums, tempList, output);
                tempList.remove(tempList.size() - 1);
            }
        }
        
    }   
}

// Stack trace output would be 
// [[1,2,3], [1,3,2], [2,3,1], [2,1,3], [3,1,2], [3,2,1]] for the above example.

// Time complexity:  O(n x n!)
// Space complexity: O(n!)