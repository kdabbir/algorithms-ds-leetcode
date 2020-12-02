// 53. Maximum Subarray: https://leetcode.com/problems/maximum-subarray/
// Easy

// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

// Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 

// Example 1:

// Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
// Output: 6
// Explanation: [4,-1,2,1] has the largest sum = 6.


// Kadanes Algorithm

class Solution {
    public int maxSubArray(int[] nums) {
        int maxEndHere = nums[0];
        int maxSoFar = nums[0];
        for(int pointer = 0; pointer < nums.length; pointer++) {
            maxEndHere = Math.max(nums[pointer], nums[pointer] + maxEndHere);
            maxSoFar = Math.max(maxSoFar,maxEndHere);
        }
        return maxSoFar;
    }
}