// 283. Move Zeroes
// Easy
// Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

// Example:

// Input: [0,1,0,3,12]
// Output: [1,3,12,0,0]
// Note:

// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.


//Approach1: Shifting all non-zero to left partition and marking rest as 0.

class Solution {
    public void moveZeroes(int[] nums) {
        int start = 0;
        for(int i = 0; i < nums.length; i++) {
           if(nums[i] != 0) {
               nums[start++] = nums[i];
           } 
        }
        while (start < nums.length) nums[start++] = 0;
    }
}


//Approach 2: Swapping all non-zeros to left partition. Zeros will be left at the end.
class Solution {
    public void moveZeroes(int[] nums) {
        int start = 0;
        for(int i=0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[start];
                nums[start] = nums[i];
                nums[i] = temp;
                start++;
            }
        }
    }
}