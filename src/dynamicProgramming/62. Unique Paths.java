// 62. Unique Paths : https://leetcode.com/problems/unique-paths/
// Medium

// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

// How many possible unique paths are there?


class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dpMemo = new int[m][n];
        for(int[] arr: dpMemo) {
            Arrays.fill(arr, 1);
        }
        for(int row = 1; row < m; row++) {
            for(int col = 1; col < n; col ++) {
                dpMemo[row][col] = dpMemo[row-1][col] + dpMemo[row][col-1];
            }
        }
        return dpMemo[m-1][n-1];
     }
}

// Time: O (N * M)
// Space: O(N * M)