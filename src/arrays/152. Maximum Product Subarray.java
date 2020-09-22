// 152. Maximum Product Subarray
// Medium
// Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

// Example 1:

// Input: [2,3,-2,4]
// Output: 6
// Explanation: [2,3] has the largest product 6.
// Example 2:

// Input: [-2,0,-1]
// Output: 0
// Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int minSumSoFar = nums[0], maxSumSoFar = nums[0], maxProd = nums[0];
        for(int i = 1; i < nums.length; i++) {
           int currMax = Math.max(nums[i], Math.max(maxSumSoFar * nums[i], minSumSoFar * nums[i]));
            minSumSoFar = Math.min(nums[i], Math.min(maxSumSoFar * nums[i], minSumSoFar * nums[i]));
            maxSumSoFar = currMax;
            maxProd = Math.max(maxProd, maxSumSoFar);   
        }
        return maxProd;
    }
}   