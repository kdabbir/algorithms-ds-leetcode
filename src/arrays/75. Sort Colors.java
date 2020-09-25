// 75. Sort Colors : https://leetcode.com/problems/sort-colors/
// Medium

// Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

// Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

// Follow up:

// Could you solve this problem without using the library's sort function?
// Could you come up with a one-pass algorithm using only O(1) constant space?
 

// Example 1:

// Input: nums = [2,0,2,1,1,0]
// Output: [0,0,1,1,2,2]
// Example 2:

// Input: nums = [2,0,1]
// Output: [0,1,2]
// Example 3:

// Input: nums = [0]
// Output: [0]
// Example 4:

// Input: nums = [1]
// Output: [1]
 

// Constraints:

// n == nums.length
// 1 <= n <= 300
// nums[i] is 0, 1, or 2.

// Dutch national flag problem:

class Solution {
    public void sortColors(int[] nums) {
        int start = 0, end = nums.length - 1;
        int pointer = 0;
        while (pointer <= end) {
            if(nums[pointer] == 2) {
                swap(nums, pointer, end);
                end--;
             } else if(nums[pointer] == 0) {
                swap(nums, pointer, start);
                start++;
                pointer++;
            } else {
                pointer++;
            }
        }
    }
    
    public void swap(int[] nums, int source, int target) {
        int temp = nums[source];
        nums[source] = nums[target];
        nums[target] = temp;
    }
}

// Time complexity : O(N) since it's one pass along the array of length N.

// Space complexity : O(1) since it's a constant space solution.