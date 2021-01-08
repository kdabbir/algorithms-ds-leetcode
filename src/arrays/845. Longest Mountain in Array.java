// 845. Longest Mountain in Array: https://leetcode.com/problems/longest-mountain-in-array/
// Medium

// You may recall that an array arr is a mountain array if and only if:

// arr.length >= 3
// There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
// Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

// Example 1:

// Input: arr = [2,1,4,7,3,2,5]
// Output: 5
// Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
// Example 2:

// Input: arr = [2,2,2]
// Output: 0
// Explanation: There is no mountain.
 

// Constraints:

// 1 <= arr.length <= 104
// 0 <= arr[i] <= 104

class Solution {
    public int longestMountain(int[] arr) {
        if(arr.length == 1) return 0;
        int maxPeakLen = 0;
        int idx = 1;
        while(idx < arr.length -1) {
            boolean isPeak = arr[idx] > arr[idx -1] && arr[idx] > arr[idx + 1];
            if(!isPeak) {
                idx++;
                continue;
            }
            
            int leftIdx = idx - 2;
            while(leftIdx >= 0 && arr[leftIdx] < arr[leftIdx + 1]) {
                leftIdx --;
            }
            
            int rightIdx = idx + 2;
            while(rightIdx < arr.length && arr[rightIdx] < arr[rightIdx - 1] ){
                rightIdx++;
            }   
        
            maxPeakLen = Math.max(maxPeakLen, rightIdx - leftIdx - 1);
            idx = rightIdx;
        }
        return maxPeakLen;      
    }
}

// Time: O(N)