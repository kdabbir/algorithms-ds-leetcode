// 34. Find First and Last Position of Element in Sorted Array
// Medium: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

// If target is not found in the array, return [-1, -1].

// Follow up: Could you write an algorithm with O(log n) runtime complexity?
// Example 1:

// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:

// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]
// Example 3:

// Input: nums = [], target = 0
// Output: [-1,-1]
 

// Constraints:

// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums is a non-decreasing array.
// -109 <= target <= 109

// O(LogN) time and O(LogN) space
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] indexRange = new int[] {-1, -1};
		binarySearchAtDirection(nums, target, indexRange, true);
		binarySearchAtDirection(nums, target, indexRange, false);
		return indexRange;
  }
	public void binarySearchAtDirection(int[] array, int target, int[] indexRange, boolean goLeft) {
		int start = 0, end = array.length - 1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(array[mid] > target) {
				end = mid - 1;
			} else if (array[mid] < target) {
				start = mid + 1;
			} else {
				if(goLeft) {
					if(mid == 0 || array[mid-1] != target){
						indexRange[0] = mid;
						return;
					} else {
						end = mid - 1;
					}
				} else {
					if(mid == array.length - 1 || array[mid + 1] != target) {
						indexRange[1] = mid;
						return;
					} else {
						start = mid + 1;
					}
				}
			}
		}
		return;
	}
}