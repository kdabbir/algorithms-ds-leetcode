// 300. Longest Increasing Subsequence
// Medium

// Given an unsorted array of integers, find the length of longest increasing subsequence.

// Example:

// Input: [10,9,2,5,3,7,101,18]
// Output: 4 
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
// Note:

// There may be more than one LIS combination, it is only necessary for you to return the length.
// Your algorithm should run in O(n2) complexity.
// Follow up: Could you improve it to O(n log n) time complexity?


// Bottom up DP
// O(n2) time complexity
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int maxLen = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int end = 1; end < nums.length; end++) {
            int maxLengthSoFar = 0;
            for(int start = 0; start < end; start++) {
                if(nums[end] > nums[start]) {
                    maxLengthSoFar = Math.max(maxLengthSoFar, dp[start]); 
                }     
            }
            dp[end] = maxLengthSoFar + 1;
            maxLen = Math.max(maxLen, dp[end]);
        }
        return maxLen;
    }
}
