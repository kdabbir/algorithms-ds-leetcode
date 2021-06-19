// 698. Partition to K Equal Sum Subsets: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

// Medium

// Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
// Example 1:

// Input: nums = [4,3,2,3,5,2,1], k = 4
// Output: true
// Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
// Example 2:

// Input: nums = [1,2,3,4], k = 3
// Output: false

// Constraints:

// 1 <= k <= nums.length <= 16
// 1 <= nums[i] <= 104
// The frequency of each element is in the range [1, 4].


// Backtracking solution.

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(k > nums.length) return false;
        int totalSum = 0;
        for(int currSum: nums) {
            totalSum += currSum;
        }
        if(totalSum % k != 0) return false;
        Arrays.sort(nums);
        return canPartitionDFS(nums, 0, totalSum/k, nums.length - 1, new boolean[nums.length], k);
    }

    public boolean canPartitionDFS(int[] nums, int currSum, int targetSum, int startIdx, boolean[] visited, int round) {
        if(round == 1) return true; // You can return true, when k == 1, which means you already found k - 1 subsets with target sum. The remaining last group surely contains target sum.
        // Alternative:
        // if(round == 0) return true;

        if(currSum == targetSum) {
            return canPartitionDFS(nums, 0, targetSum, nums.length - 1, visited, round - 1);
        }

        for(int currIdx = startIdx; currIdx >= 0; currIdx --) {
            if(!visited[currIdx] && currSum + nums[currIdx] <= targetSum) {
                visited[currIdx] = true;

                if(canPartitionDFS(nums, currSum + nums[currIdx], targetSum, currIdx - 1, visited, round)) {
                 return true;
                }

                visited[currIdx] = false;
            }
        }
        return false;
    }
}

// Time Complexity: What basically we are doing is, we are traversing the entire nums array for each subset (once we are done with one subset, for the next subset we are starting again with index 0). So for each subset, we are choosing the suitable elements from the nums array (basically iterate over nums and for each element either use it or drop it, which is O(2^n) operation where n is the size of nums). We are doing the same for each subset. Total subsets are k. So Time Complexity becomes O(k*(2^n)).

// Space Complexity: Even though we are traversing the entire array for each subset, the height of the recursion tree would still remain O(n) because we won't be calling the recursive function for already visited elements. Also, O(n) for the visited array. So the Space Complexity becomes stack size + visited array = O(n)+O(n) = O(n).

//Alternate solution:
// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews

// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/1275419/My-99-Java-solution-check-that-we-can-make-each-bucket-(sum-k)-times