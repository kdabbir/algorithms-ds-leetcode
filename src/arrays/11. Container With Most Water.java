// 11. Container With Most Water: https://leetcode.com/problems/container-with-most-water/
// Medium

// Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

// Note: You may not slant the container and n is at least 2.


// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 

// Example:

// Input: [1,8,6,2,5,4,8,3,7]
// Output: 49

// Brute force solution

class Solution {
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < height.length; i++){
            for(int j = i+1; j < height.length; j++){
            int min = Math.min(height[i], height[j]);
            max = Math.max(max, min * (j-i));
            }
        }
        return max;
    }
}

// One pass O(N) solution
class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = Integer.MIN_VALUE;
        while(i < j){
            int min = Math.min(height[i], height[j]);
            max = Math.max(max, min * (j-i));
            
            // Move which ever height is the least of both, since we need higher values for max area.
            if(height[i] < height[j]){
                i++;
            } else {
                j--;
            }
            
        }
        return max;
    }
}