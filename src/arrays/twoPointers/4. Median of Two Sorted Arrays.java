// 4. Median of Two Sorted Arrays: https://leetcode.com/problems/median-of-two-sorted-arrays/
// HARD 

// Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

// Follow up: The overall run time complexity should be O(log (m+n)).
// Example 1:

// Input: nums1 = [1,3], nums2 = [2]
// Output: 2.00000
// Explanation: merged array = [1,2,3] and median is 2.
// Example 2:

// Input: nums1 = [1,2], nums2 = [3,4]
// Output: 2.50000
// Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
// Example 3:

// Input: nums1 = [0,0], nums2 = [0,0]
// Output: 0.00000
// Example 4:

// Input: nums1 = [], nums2 = [1]
// Output: 1.00000
// Example 5:

// Input: nums1 = [2], nums2 = []
// Output: 2.00000

// Constraints:

// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        
        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;
        while(low <= high) {
            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int partX = (low + high)/2;
            int partY = (x + y + 1)/2 - partX;
            
            int maxLeftX = partX == 0 ? Integer.MIN_VALUE : nums1[partX - 1];
            int minRightX = partX == x ? Integer.MAX_VALUE : nums1[partX];
            
            int maxLeftY = partY == 0 ? Integer.MIN_VALUE : nums2[partY - 1];
            int minRightY = partY == y ? Integer.MAX_VALUE : nums2[partY];
            
            if(maxLeftY > minRightX ) {
                //we are too far on left side for partitionX. Go on right side.
                low = partX + 1;
             }
            else if(maxLeftX > minRightY) {
                //we are too far on right side for partitionX. Go on left side.
                high = partX - 1;
            }
            else {
                // (maxLeftX <= minRightY && maxLeftY <= minRightX) 
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if((x + y) % 2 == 0) {
                    return (double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)(Math.max(maxLeftX, maxLeftY));
                }
            } 
        } 
    //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        return -1;
    }
}

