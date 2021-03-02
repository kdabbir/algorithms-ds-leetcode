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

// Approach 1: Using boolean[] instead of List.contains()
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        backTrack(new ArrayList<>(), new boolean[nums.length], nums, output);
        return output;
    }
    
    public void backTrack(List<Integer> currPath, boolean[] used, int[] nums, List<List<Integer>> output) {
        if(currPath.size() == nums.length) {
            // make a deep copy since otherwise we'd be append the same list over and over
            output.add(new ArrayList<>(currPath));
        } else {
            for(int idx = 0; idx < nums.length; idx++) {
                // skip used letters
                if(used[idx]) continue;
                // add letter to permutation, mark letter as used
                currPath.add(nums[idx]);
                used[idx] = true;
                backTrack(currPath, used, nums, output);
                // remove letter from permutation, mark letter as unused
                currPath.remove(currPath.size() - 1);
                used[idx] = false;
            }
        }
    }
}


// Stack trace output would be 
// [[1,2,3], [1,3,2], [2,3,1], [2,1,3], [3,1,2], [3,2,1]] for the above example.

// Time complexity:  O(n x n!)
// Space complexity: O(n!)


// App2 :Using backtracking, trying to generate all possible permutations

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
                tempList.remove(tempList.size() - 1); // Reason explained below.
            }
        }
        
    }   
}

// Using new list instead of re-using the existing list and backtracking.

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
                ArrayList<Integer> current = new ArrayList<>(tempList); //here we create a new List instance.we reuse the tempList for all the backtrack function calls. We reuse the tempList for performance.
                // We can delete the "remove" line and create a new List instance every time. The result is the same.

               current.add(nums[idx]);
               backTrack(nums, current, output);
           }
       }
       
   }   
}

// Stack trace output would be 
// [[1,2,3], [1,3,2], [2,3,1], [2,1,3], [3,1,2], [3,2,1]] for the above example.

// Time complexity:  O(n x n!)
// Space complexity: O(n!)

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> permutations = new ArrayList<>();
         if (nums.length == 0) {
             return permutations;
         }
 
         collectPermutations(nums, 0, new ArrayList<>(), permutations);
         return permutations;
     }
 
     private void collectPermutations(int[] nums, int start, List<Integer> permutation,
              List<List<Integer>>  permutations) {
         
         if (permutation.size() == nums.length) {
             permutations.add(permutation);
             return;
         }
 
         for (int i = 0; i <= permutation.size(); i++) {
             List<Integer> newPermutation = new ArrayList<>(permutation);
             newPermutation.add(i, nums[start]);
             collectPermutations(nums, start + 1, newPermutation, permutations);
         }
     }
 }


 // Understanding backtracking
//  Code flow

// nums = 1,2,3

// start = 0, permutation = []
// i = 0, newPermutation = [1]
// 	start = 1, permutation = [1]
// 	i = 0, newPermutation = [2, 1]
// 		start = 2, permutation = [2, 1]
// 		i = 0, newPermutation = [3, 2, 1]
// 		i = 1, newPermutation = [2, 3, 1]
// 		i = 2, newPermutation = [2, 1, 3]
// 	i = 1, newPermutation = [1, 2]
// 		start = 2, permutation = [1, 2]
// 		i = 0, newPermutation = [3, 1, 2]
// 		i = 1, newPermutation = [1, 3, 2]
// 		i = 2, newPermutation = [1, 2, 3]