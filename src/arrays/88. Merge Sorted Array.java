// 88. Merge Sorted Array : https://leetcode.com/problems/merge-sorted-array/
// Easy

// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

// Note:

// The number of elements initialized in nums1 and nums2 are m and n respectively.
// You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
// Example:

// Input:
// nums1 = [1,2,3,0,0,0], m = 3
// nums2 = [2,5,6],       n = 3

// Output: [1,2,2,3,5,6]
 

// Constraints:

// -10^9 <= nums1[i], nums2[i] <= 10^9
// nums1.length == m + n
// nums2.length == n

// Approach with in-place sorting without any extra space
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = nums2.length - 1;
        int pointer = nums1.length - 1;
        
        while(len1 >= 0 && len2 >= 0) {
            if(nums1[len1] > nums2[len2]) {
                nums1[pointer--] = nums1[len1--];
            } else {
                nums1[pointer--] = nums2[len2--];
            }
        }
   
        while(len2 >= 0) { 
            nums1[pointer--] = nums2[len2--];
        }
    
    }
}
// Time complexity : O(n+m).
// Space complexity : O(1).

// Just an FYI you don't need the while(i >= 0) loop. Since we are merging into num1 that means those values are already in the correct position.


