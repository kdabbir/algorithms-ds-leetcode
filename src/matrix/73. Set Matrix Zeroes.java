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

// If any cell of the matrix has a zero we can record its row and column number. All the cells of this recorded row and column can be marked zero in the next iteration.

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
        
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0;  col < matrix[row].length; col++) {
                if(rows.contains(row) || cols.contains(col)) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
}

// Complexity Analysis

// Time Complexity: O(M×N) where M and N are the number of rows and columns respectively.

// Space Complexity: O(M+N).

// Approach 2: O(1) Space, Efficient Solution


// The idea is that we can use the first cell of every row and column as a flag. This flag would determine whether a row or column has been set to zero. This means for every cell instead of going to M+N cells and setting it to zero we just set the flag in two cells.

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean isFirstRow = false, isFirstCol = false;
        
        for (int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) { 
                if(matrix[row][col] == 0) {
                    if(row == 0) isFirstRow = true;
                    if(col == 0) isFirstCol = true;
                    matrix[row][0]  = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        
        for(int row = 1; row < matrix.length; row++) {
            for(int col = 1; col < matrix[row].length; col++) {
                if(matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        
        if(isFirstRow) {
            for(int col = 0; col < matrix[0].length; col++) { 
                matrix[0][col] = 0;
            }
        }
         if(isFirstCol) {
            for(int row = 0; row < matrix.length; row++) { 
                matrix[row][0] = 0;
            }
        }
        
      
    }
}