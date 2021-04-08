// 40. Combination Sum II: https://leetcode.com/problems/combination-sum-ii/
// Medium

// Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

// Each number in candidates may only be used once in the combination.

// Note: The solution set must not contain duplicate combinations.



// Example 1:

// Input: candidates = [10,1,2,7,6,1,5], target = 8
// Output:
// [
// [1,1,6],
// [1,2,5],
// [1,7],
// [2,6]
// ]
// Example 2:

// Input: candidates = [2,5,2,1,2], target = 5
// Output:
// [
// [1,2,2],
// [5]
// ]


// Constraints:

// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backTrack(new ArrayList<>(), output, 0, target,  candidates);
        return output;
    }

    public void backTrack(ArrayList<Integer> curr, List<List<Integer>> output, int processIdx, int currentRem, int[] candidates) {
        if(currentRem < 0) return;
        else if(currentRem == 0) {
            output.add(new ArrayList<>(curr));
        } else {
            for(int idx = processIdx; idx < candidates.length; idx++) {
                if(idx > processIdx && candidates[idx] == candidates[idx - 1]) continue; // Skip duplicates
                curr.add(candidates[idx]);
                backTrack(curr, output, idx + 1, currentRem - candidates[idx], candidates);
                curr.remove(curr.size() - 1);
            }
        }
    }
}

// Time complexity:  O(n x n!)
// Space complexity: O(n!)