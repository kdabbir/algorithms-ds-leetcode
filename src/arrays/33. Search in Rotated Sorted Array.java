// 33. Search in Rotated Sorted Array: https://leetcode.com/problems/search-in-rotated-sorted-array/

// Medium

// You are given an integer array nums sorted in ascending order, and an integer target.

// Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

// If target is found in the array return its index, otherwise, return -1.


// Example 1:

// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4
// Example 2:

// Input: nums = [4,5,6,7,0,1,2], target = 3
// Output: -1
// Example 3:

// Input: nums = [1], target = 0
// Output: -1
 

class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] >= nums[start]) { // Is left side sorted? i.e Start <= Mid.
                if(target >= nums[start] && target < nums[mid]) end = mid-1; // If element exists between start and mid, check in left sorted side.
                else start = mid+1; 
            } else { // We are in unsorted part of array, we check if right side is sorted.
                if(target > nums[mid] && target <= nums[end]) start = mid + 1; // Right side is sorted. Hence,move start pointer to right side.
                else end = mid-1;
            }
        }
        return -1;
    }
}

// Complexity Analysis

// Time complexity: O(logN).
// Space complexity: O(1).


// Formula: If a sorted array is shifted, if you take the middle, always one side will be sorted. Take the recursion according to that rule.

// 1- take the middle and compare with target, if matches return.
// 2- if middle is bigger than left side, it means left is sorted
// 2a- if [left] < target < [middle] then do recursion with left, middle - 1 (right)
// 2b- left side is sorted, but target not in here, search on right side middle + 1 (left), right
// 3- if middle is less than right side, it means right is sorted
// 3a- if [middle] < target < [right] then do recursion with middle + 1 (left), right
// 3b- right side is sorted, but target not in here, search on left side left, middle -1 (right)

