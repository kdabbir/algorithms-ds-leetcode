// 62. Unique Paths : https://leetcode.com/problems/unique-paths/
// Medium

// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

// How many possible unique paths are there?

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] numberOfWays = new int[m + 1][n + 1];

		for(int row = 1; row < numberOfWays.length; row++) {
			for(int col = 1; col < numberOfWays[row].length;col++) {
				if(row == 1 || col == 1) {
					numberOfWays[row][col] = 1;
				} else{ 
					int waysFromLeft =  numberOfWays[row][col - 1];
					int waysFromTop = numberOfWays[row - 1][col];
					numberOfWays[row][col] = waysFromLeft + waysFromTop;
				}
			}
		}
		return numberOfWays[m][n];
     }
}

// Time: O (N * M)
// Space: O(N * M)