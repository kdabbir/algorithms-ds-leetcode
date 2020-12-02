// 152. Maximum Product Subarray:  https://leetcode.com/problems/maximum-product-subarray/
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

// Time: O(N)
// Space: O(1)

// Approach:

// max_so_far is updated by taking the maximum value among:

// Current number.
// This value will be picked if the accumulated product has been really bad (even compared to the current number). This can happen when the current number has a preceding zero (e.g. [0,4]) or is preceded by a single negative number (e.g. [-3,5]).
// Product of last max_so_far and current number.
// This value will be picked if the accumulated product has been steadily increasing (all positive numbers).
// Product of last min_so_far and current number.
// This value will be picked if the current number is a negative number and the combo chain has been disrupted by a single negative number before (In a sense, this value is like an antidote to an already poisoned combo chain).
// min_so_far is updated in using the same three numbers except that we are taking minimum among the above three numbers.


// Bruteforce solution

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int result = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int accu = 1;
            for (int j = i; j < nums.length; j++) {
                accu *= nums[j];
                result = Math.max(result, accu);
            }
        }

        return result;
    }
}

// Time: O(N ^ 2)
// Space: O(1)