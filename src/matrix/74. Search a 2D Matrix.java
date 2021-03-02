// 74. Search a 2D Matrix: https://leetcode.com/problems/search-a-2d-matrix/
// Medium
// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
 

// Example 1:


// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
// Output: true
// Example 2:


// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
// Output: false
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
// -104 <= matrix[i][j], target <= 104


// Using binary search

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if(rows == 0) 
            return false;
        int cols = matrix[0].length;
        int startIdx = 0, endIdx = rows * cols - 1;
        int midIdx, midElement;
        
        while(startIdx <= endIdx) {
            midIdx = startIdx + (endIdx - startIdx)/2;
            midElement = matrix[midIdx / cols][midIdx % cols];
            // If you treat matrix elements as an array of indexes, rows and cols can be obtained
            // index / numberOfCols => which row
            // index % numberOfCols => which col
            if(midElement == target){
                return true;
            } else if(midElement > target) {
                endIdx = midIdx - 1;  
            } else {
                startIdx = midIdx + 1;
            }
        }
        return false;
    }
}


// Time: O(logn(NM))
// Space: O(1)


// Naive solution

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[row].length - 1;
        while(row < matrix.length && col >= 0) {
            if(matrix[row][col] > target) {
                col--;
            } else if(matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}

// Time: O(N+M)
// Space: O(1)