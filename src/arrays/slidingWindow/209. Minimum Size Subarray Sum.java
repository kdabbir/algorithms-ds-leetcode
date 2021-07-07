// 209. Minimum Size Subarray Sum: https://leetcode.com/problems/minimum-size-subarray-sum/

// Medium
// Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.



// Example 1:

// Input: target = 7, nums = [2,3,1,2,4,3]
// Output: 2
// Explanation: The subarray [4,3] has the minimal length under the problem constraint.
// Example 2:

// Input: target = 4, nums = [1,4,4]
// Output: 1
// Example 3:

// Input: target = 11, nums = [1,1,1,1,1,1,1,1]
// Output: 0


// Constraints:

// 1 <= target <= 109
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105


// Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).


// Two pointers solution.

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE, sum = 0, start = 0;
        for(int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while(sum >= target) {
                minLength = Math.min(minLength, end + 1 - start );
                sum -= nums[start];
                start++;
            }
        }

        return minLength == Integer.MAX_VALUE ?  0 : minLength;
   }
}
//Time: O(N)
//Space: O(1)

// Bruteforce O(N ^2) solution.
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        for(int start = 0; start < nums.length; start++) {
            int currSum = 0;
            for(int end = start; end < nums.length; end++) {
                currSum += nums[end];
                if(currSum >= target) {
                    minLength = Math.min(minLength, end - start + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ?  0 : minLength;
   }
}