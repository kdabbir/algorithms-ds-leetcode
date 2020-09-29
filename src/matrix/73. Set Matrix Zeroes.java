// 73. Set Matrix Zeroes : https://leetcode.com/problems/set-matrix-zeroes/
// Medium

// Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

// Follow up:

// A straight forward solution using O(mn) space is probably a bad idea.
// A simple improvement uses O(m + n) space, but still not the best solution.
// Could you devise a constant space solution?
 

// Example 1:


// Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
// Output: [[1,0,1],[0,0,0],[1,0,1]]
// Example 2:


// Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
// Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

// Approach 1: Additional Memory Approach

class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) { 
                if(matrix[row][col] == 0) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        
        for(int row2 = 0; row2 < matrix.length; row2++) {
            for(int col2 = 0;  col2 < matrix[row2].length; col2++) {
                if(rows.contains(row2) || cols.contains(col2)) {
                    matrix[row2][col2] = 0;
                }
            }
        }
    }
}

// Complexity Analysis

// Time Complexity: O(M×N) where M and N are the number of rows and columns respectively.

// Space Complexity: O(M+N).


