// Maximum path sum in matrix: (Move down, diagonally left and right)
// https://www.geeksforgeeks.org/maximum-path-sum-matrix/

// // Hard
// Given a matrix of N * M. Find the maximum path sum in matrix. The maximum path is sum of all elements from first row to last row where you are allowed to move only down or diagonally to left or right. You can start from any element in first row.

// Input : mat[][] = 10 10  2  0 20  4
//                    1  0  0 30  2  5
//                    0 10  4  0  2  0
//                    1  0  2 20  0  4
// Output : 74
// The maximum sum path is 20-30-4-20.

// Input : mat[][] = 1 2 3
//                   9 8 7
//                   4 5 6
// Output : 17
// The maximum sum path is 3-8-6.
class MaxSumPath {

// Function to calculate max path in matrix 
public int getMaxSum(int[][] matrix)
{ 
    // To find max val in first row 
    int maxVal = Integer.MIN_VALUE;

    // Getting max value from 1st row
    for(int i = 0; i < matrix[0].length; i++) {
        maxVal = Math.max(maxVal, matrix[0][i]);
    }
    
    for(int row = 1; row < matrix.length; row++) {
        for(int col = 0; col < matrix[row].length; col++) {
            // When all paths are possible 
            if(col > 0 && col < matrix[row].length - 1) {
                matrix[row][col] += Math.max(matrix[row - 1][col],
                                            Math.max(matrix[row - 1][col - 1], matrix[row -1][col + 1]));
                
            }
            
            // When diagonal right is not possible (when col reaches end)
            else if(col > 0) {
                matrix[row][col] += Math.max(matrix[row - 1][col], matrix[row - 1][col - 1]);
            } 
            
            // When diagonal left is not possible (when at 0)
        
            else if(col <  matrix[row].length - 1) {
                                    matrix[row][col] += Math.max(matrix[row - 1][col], matrix[row - 1][col + 1]);
            }
            
            // Store max path sum 
            maxVal = Math.max(maxVal, matrix[row][col]);
        }
    }
    return maxVal;
} 
}

// Question for Move diagonally left and right:
// Maximum sum path in a matrix from top to bottom:
// https://www.geeksforgeeks.org/maximum-sum-path-matrix-top-bottom/

// Consider a n*n matrix. Suppose each cell in the matrix has a value assigned.
// We can go from each cell in row i to a diagonally higher cell in row i+1 only
// [i.e from cell(i, j) to cell(i+1, j-1) and cell(i+1, j+1) only]. Find the
// path from the top row to the bottom row following the aforementioned
// condition such that the maximum sum is obtained.

// Examples:

// Input : mat[][] = { {5, 6, 1, 7},
// {-2, 10, 8, -1},
// {3, -7, -9, 11},
// {12, -4, 2, 6} }
// Output : 28

// {5, 6, 1, 7},
// {-2, 10, 8, -1},
// {3, -7, -9, 11},
// {12, -4, 2, 6} }

// The highlighted numbers from top to bottom
// gives the required maximum sum path.
// (7 + 8 + 11 + 2) = 28

// Approach 1- Using backtracking to find max sum.

class MaxSumPath {
    // int mat[][];

    // function to find the maximum
    // sum path in a matrix
    static int maxSum(final int[][] mat, final int n) {
        // if there is a single element only
        if (n == 1)
            return mat[0][0];

        // dp[][] matrix to store the results
        // of each iteration
        final int dp[][] = new int[n][n];
        int maxSum = Integer.MIN_VALUE, max;

        // base case, copying elements of
        // last row
        for (int j = 0; j < n; j++)
            dp[n - 1][j] = mat[n - 1][j];

        // building up the dp[][] matrix
        // from bottom to the top row
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                max = Integer.MIN_VALUE;

                // finding the maximum diagonal
                // element in the (i+1)th row
                // if that cell exists
                if (((j - 1) >= 0) && (max < dp[i + 1][j - 1]))
                    max = dp[i + 1][j - 1];
                if (((j + 1) < n) && (max < dp[i + 1][j + 1]))
                    max = dp[i + 1][j + 1];

                // adding that 'max' element
                // to the mat[i][j] element
                dp[i][j] = mat[i][j] + max;
            }
        }

        // finding the maximum value from
        // the first row of dp[][]
        for (int j = 0; j < n; j++)
            if (maxSum < dp[0][j])
                maxSum = dp[0][j];

        // required maximum sum
        return maxSum;
    }

    // Driver code
    public static void main(final String[] args) {

        final int mat[][] = { { 5, 6, 1, 7 }, { -2, 10, 8, -1 }, { 3, -7, -9, 11 }, { 12, -4, 2, 6 } };
        final int n = 4;

        System.out.println("Maximum Sum = " + maxSum(mat, n));

    }

    }

    // Approach 2- Using dynamic programming

    // Function to find the maximum sum
    // path in the grid
static int MaximumPath(final int [][]grid) 
{ 
    final int m = matrix.length, n = matrix[0].length;
    final int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
    
    for(int row = 1; row <= m; row++) {
        for(int col = 1; col <= n; col++) {
            if(col != n){
                dp[row][col] = Math.max(dp[row - 1][col -1], dp[row - 1][col + 1]) + matrix[row - 1][col - 1];
             } else {
                 dp[row][col] = Math.max(dp[row - 1][col -1], 0) + matrix[row - 1][col - 1];

            }
        }
    }
    // here we are considering n-1 for col since 
    return dp[m][n - 1];
} 
}

