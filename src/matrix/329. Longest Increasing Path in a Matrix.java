// 329. Longest Increasing Path in a Matrix
// Hard: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
// Given an integer matrix, find the length of the longest increasing path.

// From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

// Example 1:

// Input: nums = 
// [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ] 
// Output: 4 
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:

// Input: nums = 
// [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ] 
// Output: 4 
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

// Native DFS approach with memoization:

class Solution {
    private static final int[][] dirs = {{0,1}, {1,0}, {-1, 0}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int result = 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++){
                result = Math.max(result, dfs(matrix, row, col, cache));
            }
        }
        return result;
    }
    
    public int dfs(int[][] matrix, int row, int col, int[][] cache) {
        if(cache[row][col] != 0) return cache[row][col];
        for(int[] dir: dirs) {
            int x = row + dir[0], y = col + dir[1];
            if(x >= 0 && x < matrix.length && y >= 0 && y < matrix[row].length && matrix[x][y] > matrix[row][col]){
                cache[row][col] = Math.max(cache[row][col], dfs(matrix, x, y, cache));
            }
        }
        return ++cache[row][col];
    }
}