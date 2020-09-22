// 153. Find Minimum in Rotated Sorted Array : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// Medium

// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

// (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

// Find the minimum element.

// You may assume no duplicate exists in the array.

// Example 1:

// Input: [3,4,5,1,2] 
// Output: 1
// Example 2:

// Input: [4,5,6,7,0,1,2]
// Output: 0


public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int start = 0, end = num.length - 1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if(mid > 0 && num[mid-1] > num[mid]) {
                return num[mid];
            }
            if(num[mid] >= num[start] && num[mid] > num[end]) {
                start = mid +1;
            } else
                end = mid - 1;
        }
        return num[start];
    }
}

// Time Complexity : O(logN)
// Space Complexity : O(1)


// Approach:
// The minimum element must satisfy one of two conditions: 1) If rotate, A[min] < A[min - 1]; 
// 2) If not, A[0]. Therefore, we can use binary search: check the middle element,
// if it is less than previous one, then it is minimum. If not, there are 2 conditions as well: If it is greater than both left and right element, then minimum element should be on its right, otherwise on its left.

