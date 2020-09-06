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


///
// The simplest approach is to try to find all increasing subsequences and then returning the maximum length of longest increasing subsequence. In order to do this, we make use of a recursive function \text{lengthofLIS}lengthofLIS which returns the length of the LIS possible from the current element(corresponding to curposcurpos) onwards(including the current element). Inside each function call, we consider two cases:
// currPosLen: The current element is larger than the previous element included in the LIS. In this case, we can include the current element in the LIS. Thus, we find out the length of the LIS obtained by including it. Further, we also find out the length of LIS possible by not including the current element in the LIS. The value returned by the current function call is, thus, the maximum out of the two lengths.
// prevPosLen: The current element is smaller than the previous element included in the LIS. In this case, we can't include the current element in the LIS. Thus, we find out only the length of the LIS possible by not including the current element in the LIS, which is returned by the current function call.

// Brute Force
// Time: O(2n),  space: O(n2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        return recurseLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int recurseLIS(int[] nums, int prevLen, int currPos) {
        if(currPos == nums.length) return 0;
        int currPosLen = 0, prevPosLen = 0;
        if(nums[currPos] > prevLen) { 
            currPosLen = 1 + recurseLIS(nums, nums[currPos], currPos + 1);
        }
        prevPosLen = recurseLIS(nums, prevLen, currPos + 1);
        return Math.max(currPosLen, prevPosLen);
    }
}