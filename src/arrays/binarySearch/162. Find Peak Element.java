// 162. Find Peak Element: https://leetcode.com/problems/find-peak-element/
// Medium

// A peak element is an element that is strictly greater than its neighbors.

// Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

// You may imagine that nums[-1] = nums[n] = -∞.

// You must write an algorithm that runs in O(log n) time.



// Example 1:

// Input: nums = [1,2,3,1]
// Output: 2
// Explanation: 3 is a peak element and your function should return the index number 2.
// Example 2:

// Input: nums = [1,2,1,3,5,6,4]
// Output: 5
// Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


// Constraints:

// 1 <= nums.length <= 1000
// -231 <= nums[i] <= 231 - 1
// nums[i] != nums[i + 1] for all valid i.

// Linear search
class Solution {
    public int findPeakElement(int[] nums) {
        for(int idx = 0; idx < nums.length - 1; idx++) {
            if(nums[idx] > nums[idx + 1]){
                return idx;
            }
        }
        return nums.length - 1;
    }
}

// here, we just need to find ANY peak,hence, the first instance that we find we will return.

// Time: O(N)
// Space: O(1)

// Recursive Binary search.

class Solution {
    public int findPeakElement(int[] nums) {
        return findPeakBinarySearch(nums, 0, nums.length - 1);
    }

    public int findPeakBinarySearch(int[] nums, int start, int end) {
        if(start == end) return start;
        int mid = start + (end - start)/ 2;
        if(nums[mid] > nums[mid + 1]) {
            return findPeakBinarySearch(nums, start, mid);
        }
        return findPeakBinarySearch(nums, mid + 1, end);
    }
}
// Time: O(LogN)
// Space: O(1)

// In this problem, it feels binary search would go wrong since we are not sure if either side could have increasing or decreasing numbes.

// However, the key point is that a peak would defiitely be present and the answer from either side of the array after creating the half in binary search is fine and so binary search works.

// If we were asked to only find one peak then binary search would probably fail since we would not be sure of which direction to go in as the array is not sorted.
// Binary search works since :

// either peak can be answer[sorted property which is strictly required for BS gets countered]
// peak definition can be checked with 2 indices adjacent to the mid value.

// Binary search works here because we need to return any local peak, not necessarily the global peak.
// Therefore, say we take the middle value. If the number to its left is smaller than it, then if the number to its right is also smaller than it, the middle value is a local peak. If the number to its right is higher than the middle value, then somewhere on the right there must be a peak - either the numbers ascend and then descend, in which case there would be a peak where the change from ascent to descent happens, or the numbers continue to ascend until the end of the array, in which case the last value in the array would be a local peak.
// The same with the other way. If the value on the left on the middle value is bigger than the middle value, then it must be that either the middle value itself is a peak or that there is definitely a peak on the left side of the middle value. This is because if the number on the left is bigger than the middle value, there are two options: either the numbers continue ascending in the left direction until the end, in which case the first value of the array would be a peak, or the values increase to the left until a point at which they start decreasing, and that point would be a peak.

// Iterative binary search.

class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

// Time: O(LogN)
// Space: O(1)