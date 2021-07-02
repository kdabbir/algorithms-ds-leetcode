// 240. Search a 2D Matrix II: https://leetcode.com/problems/search-a-2d-matrix-ii/
// Medium

// Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:

// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.


// Example 1:


// Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
// Output: true
// Example 2:


// Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
// Output: false


// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= n, m <= 300
// -109 <= matix[i][j] <= 109
// All the integers in each row are sorted in ascending order.
// All the integers in each column are sorted in ascending order.
// -109 <= target <= 109

// Binary search from bottom left corner.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length - 1, colLen = matrix[0].length - 1;
        int row = rowLen, col = 0;
        while(row >= 0  && col <= colLen) {
            if(matrix[row][col] > target) {
                row--;
            } else if(matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }
}
// Time: O(m + n)
// Space: O(1)

// Intuition behind the problem:

// It is given that rows and columns are sorted. So the optimal way should involved binary search / complexity can be optimized to logarithmic.
// In any search problem, the basic motive is to reduce the decision space progressively. The more aggressively the search space is reduced, the more efficient the algorithm.
// To reduce decision space means to eliminate certain portion completely from search in future. Here, due to the property of rows & columns being sorted, we can verify that we can utilise the given properties to reduce search space only if we begin at bottom left corner or top right corner. This is because at only those two starting positions, we would be able to exercise decisions to reduce our decision/search space in both directions i.e.
// i. if we start at bottom left corner => we can go right to get elements in increasing order & top to get elements in decreasing order.
// ii. if we start at top right corner => we can go left to get elements in decreasing order & bottom to get elements in increasing order.
// We can't have both choices if we start at top left or bottom right indices.

// Binary search from top right corner.
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
// Time: O(m + n)
// Space: O(1)


// Double binary search

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean found = false;
        for(int[] rows: matrix) {
             if(binarySearchRow(rows, target)) {
                 found = true;
                 break;
             }
        }
        return found;
    }

    public boolean binarySearchRow(int[] row, int target) {
        int start = 0, end = row.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(row[mid] == target) {
                return true;
            } else if(row[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}

// For every row do the binary search

// Time: O(Log(N) + Log(M))
// Space: O(1)
