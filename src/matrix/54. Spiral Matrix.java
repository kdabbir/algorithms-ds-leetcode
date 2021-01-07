// 54. Spiral Matrix: https://leetcode.com/problems/spiral-matrix/

// Given an m x n matrix, return all elements of the matrix in spiral order.

// Example 1:


// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,3,6,9,8,7,4,5]
// Example 2:


// Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100
// Strategy:
// Assume 4 boundaries and move as follows:
// 1) L -> R 2) Top -> Bottom 3) Right -> Left 4) Bottom -> Top


import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<Integer>();
        if(matrix.length == 0) return output;
        int top = 0, left = 0, bottom = matrix.length - 1, right = matrix[0].length - 1;
        while(top <= bottom && left <= right){
            for(int col = left; col <= right; col++){
                output.add(matrix[top][col]);
            }
            top++;
            
            for(int row = top; row <= bottom; row++){
                output.add(matrix[row][right]);
            }
            right--;
            
            if(top <= bottom){
                for(int col = right; col >= left; col--){
                    output.add(matrix[bottom][col]);
                }
                bottom--;
            }
            
            if(left <= right){
                for(int row = bottom; row >= top; row--){
                    output.add(matrix[row][left]);
                }
                left++;
            }
        }
        return output;
    }
}