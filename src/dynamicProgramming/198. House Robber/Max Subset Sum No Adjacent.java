// 198. House Robber: https://leetcode.com/problems/house-robber/
// Medium

// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

// Example 1:

// Input: nums = [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//              Total amount you can rob = 1 + 3 = 4.
// Example 2:

// Input: nums = [2,7,9,3,1]
// Output: 12
// Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//              Total amount you can rob = 2 + 9 + 1 = 12.

// Constraints:

// 0 <= nums.length <= 100
// 0 <= nums[i] <= 400

// More optimized approach
// We can notice that in the previous step we use only memo[i] and memo[i-1], so going just 2 steps back. We can hold them in 2 variables instead. This optimization is met in Fibonacci sequence creation and some other problems [to paste links].

// Alternate way of framing the question:
//Max Subset Sum No Adjacent

// Write a function that takes in an array of positive integers and returns the
// maximum sum of non-adjacent elements in the array.

// If the input array is empty, the function should return 0.

// Sample input:  = [75, 105, 120, 75, 90, 135]
// Sample output: 330 // 75 + 120 + 135

class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int last2ndNum = 0, last1stNum = 0;
        for(int num: nums) {
            int temp = last1stNum;
            last1stNum = Math.max(last2ndNum + num, last1stNum);
            last2ndNum = temp;
        }
        return last1stNum;
    }
}

// Dynamic programming bottom up.

class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length - 1];
    }
}
// Time : O(N)
// Space: O(N

// Recurse+Memoization top-down approach.

class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return robRec(nums, nums.length - 1, memo);
    }
    
    public int robRec(int[] nums, int idx, int[] memo) {
        if(idx < 0) return 0;
        
        if(memo[idx] >= 0) return memo[idx];
        
        int amount = Math.max(robRec(nums, idx -2, memo) + nums[idx], robRec(nums, idx-1, memo));
        memo[idx] = amount;
        return amount;
    }
}
// Time : O(N)
// Space: O(N
