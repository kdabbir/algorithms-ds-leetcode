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

// The point is to visualize that 2-D array is stored as 1-D array, i.e. to "flatten" it out (in fact, that's how 2-D arrays are stored in RAM. Google row-major array storage).

// I have listed flattened out index in parentheses next to the array index. Note that the flattened out index runs consequently from 0 to 11 for total of 12 elements.

// For example for matrix[1][2] = 16, the flattened out index is 6. Now, if you do that math, it is very clear. 6/4 = 1 for row, and 6%4 = 2 for the column!

// matrix = [
//   [1 (0), 3 (1), 5 (2), 7 (3)],
//   [10(4), 11(5), 16(6), 20(7)],
//   [23(8), 30(9), 34(10), 50(11)]
// ]


// Time: O(logn(NM))
// Space: O(1)

// Double binary search.


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Thinking about binary search on first column.
        // Then perform binary search on selected row.
        int rowLen = matrix.length - 1, colLen = matrix[0].length - 1;
        int start = 0, end = rowLen;
        while(start < end) {
            int mid = start + (end - start) / 2;
            int value = matrix[mid][0];
            if(value <= target && matrix[mid][colLen] >= target ) {
                break;
            } else if (value < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int matchedRow = (start + end)/ 2;
        start = 0; end = colLen;
        while(start < end) {
            int mid =  start + (end - start) / 2;
            int value = matrix[matchedRow][mid];
            if(value == target) {
                return true;
            } else if (value < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return matrix[matchedRow][(start + end) / 2] == target;

    }
}

// Time: O(LogN + LogN) = O(LogN)
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