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

// Initially we consider the area constituting the exterior most lines. Now, to maximize the area, we need to consider the area between the lines of larger lengths. If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line. But moving the shorter line's pointer could turn out to be beneficial, as per the same argument, despite the reduction in the width. This is done since a relatively longer line obtained by moving the shorter line's pointer might overcome the reduction in area caused by the width reduction.

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
            } else {x
                j--;
            }
            
        }
        return max;
    }
}