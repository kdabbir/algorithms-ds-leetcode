// 77. Combinations: https://leetcode.com/problems/combinations/
// Medium

// Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

// You may return the answer in any order.

// Example 1:

// Input: n = 4, k = 2
// Output:
// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]
// Example 2:

// Input: n = 1, k = 1
// Output: [[1]]


// Constraints:

// 1 <= n <= 20
// 1 <= k <= n


class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> output = new ArrayList<>();
        backtTrackGenerateSubset(n, k, 1, new ArrayList<>(), output);
        return output;
    }

    public void backtTrackGenerateSubset(int nums, int target, int startIdx, List<Integer> tempList, List<List<Integer>> output) {
        if(tempList.size() == target) {
            output.add(new ArrayList(tempList));
        } else {
        for(int currIdx = startIdx; currIdx <= nums; currIdx++) {
            if(tempList.contains(currIdx)) continue;
            tempList.add(currIdx);
            backtTrackGenerateSubset(nums, target, currIdx + 1, tempList, output);
            tempList.remove(tempList.size() -1);
        }
       }
    }
}

// Time complexity:  O(n x n!)
// Space complexity: O(n!)
