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
					if(mid == 0 || array[mid-1] != target){ // If we land at an index where it doesn't match, its the final index.
						indexRange[0] = mid;
						return;
					} else {  // We are going left, hence, reducing end boundary to left of mid.
						end = mid - 1;
					}
				} else {
					if(mid == array.length - 1 || array[mid + 1] != target) {
						indexRange[1] = mid;
						return;
					} else { // We are going right, hence, reducing start boundary to right of mid.
						start = mid + 1;
					}
				}
			}
		}
		return;
	}
}

// Naive solution. Find any target index and expand left, right pointers
class Solution2 {

public int[] searchRange(int[] nums, int target) {
  int[] indexRange = new int[] {-1, -1};
  int targetIdx = binarySearch(nums, target);
  if(targetIdx == -1)
	  return indexRange;
  else {
	  int leftBound = targetIdx, rightBound = targetIdx;
	  while(rightBound < nums.length && nums[rightBound] == target) {
		  rightBound++;
	  }
	  while(leftBound >= 0 && nums[leftBound] == target){
		  leftBound--;
	  }
	  System.out.println(leftBound);
	  System.out.println(rightBound);
		indexRange[0] = leftBound + 1;
		  indexRange[1] = rightBound - 1;
  }
  
return indexRange;
}

public static int binarySearch(int[] array, int target) {
  int start = 0, end = array.length - 1;
  while(start <= end) {
	  int mid = start + (end - start)/2;
	  if(array[mid] == target)
		  return mid;
	  else if(array[mid] > target) {
		  end = mid - 1;
	  } else {
		  start = mid + 1;
	  }
  }
  return -1;
}
}