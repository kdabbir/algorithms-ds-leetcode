
// Maximum sum path in matrix (Move right and down):
// Hard
// https://www.geeksforgeeks.org/maximum-sum-path-in-a-matrix-from-top-left-to-bottom-right/

// Given a matrix mat[][] of dimensions N * M, the task is to find the path from
// the top-left cell (0, 0) to the bottom-right cell (N – 1, M – 1) of the given
// matrix such that sum of the elements in the path is maximum. The only moves
// allowed from any cell (i, j) of the matrix are (i + 1, j) or (i, j + 1).

// Examples:

// Input: mat[][] = {{3, 7}, {9, 8}}
// Output: 20
// Explanation:
// Path with maximum sum is 3 => 9 => 8 as 20.

// Input: mat[][] = {{1, 2}, {3, 5}}
// Output: 9
// Explanation:
// Path with maximum sum is 1 => 3 => 5 as 9

// Approach 1 - Using dynamic programming.

import java.util.*;

class GFG {

    // Function to find the maximum sum
    // path in the grid
    static int MaximumPath(int[][] grid) {
        // Dimensions of grid[][]
        int N = grid.length;
        int M = grid[0].length;

        // Stores maximum sum at each cell
        // sum[i][j] from cell sum[0][0]
        int[][] sum = new int[N + 1][M + 1];

        // Iterate to compute the maximum
        // sum path in the grid
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // Update the maximum path sum
                sum[i][j] = Math.max(sum[i - 1][j], sum[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        // Return the maximum sum
        return sum[N][M];
    }

    // Driver Code
    public static void main(String[] args) {
        int[][] grid = { { 1, 2 }, { 3, 5 } };
        System.out.print(MaximumPath(grid));
    }
}