// Matrix Chain Multiplication
// Hard:  https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
// Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i]. We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain. 

// Input: p[] = {40, 20, 30, 10, 30}   
// Output: 26000  
// There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
// Let the input 4 matrices be A, B, C and D.  The minimum number of 
// multiplications are obtained by putting parenthesis in following way
// (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

// Input: p[] = {10, 20, 30, 40, 30} 
// Output: 30000 
// There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30. 
// Let the input 4 matrices be A, B, C and D.  The minimum number of 
// multiplications are obtained by putting parenthesis in following way
// ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

// Input: p[] = {10, 20, 30}  
// Output: 6000  
// There are only two matrices of dimensions 10x20 and 20x30. So there 
// is only one way to multiply the matrices, cost of which is 10*20*30

// Dynamic programming using Memoization
// Intuition: https://www.youtube.com/watch?v=_WncuhSJZyA&t=1079s


public static int matrixChainMemoised(int[] arr, int start, int end) {
    if(arr == null || arr.length == 0) return 0;

    if(start == end) return 0;

    if(dp[start][end]) return dp[start][end];
    dp[start][end] = Integer.MAX_VALUE;
    for(int idx = start; idx < end; idx++) {
        dp[start][end] = Math.min(dp[start][end], 
                                dp[start][idx] + dp[idx + 1][end] + arr[start - 1] * arr[idx] * arr[end]);
    }
    return dp[start][end];
}


public static void main(String args[]) 
{ 
    int arr[] = new int[] { 1, 2, 3, 4 }; 
    public int[][] dp = new int[arr.length][arr.length];
    return matrixChainMemoised(p, dp, 1, arr.length - 1); 

    System.out.println( 
        "Minimum number of multiplications is "
        + MatrixChainOrder(arr)); 
} 