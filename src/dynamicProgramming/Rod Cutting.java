// Rod Cutting: https://practice.geeksforgeeks.org/problems/rod-cutting0840/1#
// Medium

// Given a rod of length N inches and an array of prices that contains prices of all pieces of size smaller than N. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

// Example 1:

// Input:
// N = 8
// Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
// Output:
// 22
// Explanation:
// The maximum obtainable value is 22 by
// cutting in two pieces of lengths 2 and
// 6, i.e., 5+17=22.
// Example 2:

// Input:
// N=8
// Price []={3,   5,   8,   9,  10,  17,  17,  20}
// Output: 24
// Explanation:
// The maximum obtainable value is
// 24 by cutting the rod into 8 pieces
// of length 1, i.e, 8*3=24.

// Expected Time Complexity: O(N2)
// Expected Auxiliary Space: O(N)

// Constraints:
// 1 <= N <= 1000
// 1 <= Ai <= 105

// Dp with 2D matrix. Practice 0-1 knapsack before doing this.

class GFG {
    public int cutRod(int prices[], int rodLength) {
        int[][] dpTable = new int[prices.length + 1][rodLength + 1];
        // Here row is each element of price array and col is increasing rodLength
        for (int row = 1; row <= prices.length; row++) {
			for (int col = 1; col <= rodLength; col++) {
				if (row <= col) {
                    // gotcha: Taking from arrays then reduce 1, variables don't need to get reduced. (prices[row-1] vs row)

					dpTable[row][col] = Math.max(dpTable[row - 1][col], prices[row - 1] + dpTable[row][col - row]);
				} else {
                    dpTable[row][col] = dpTable[row - 1][col];
				}
			}
		}
		return dpTable[prices.length][rodLength];
    }
}
// Explanation: https://www.youtube.com/watch?v=fJ4E-waSwZk

// Bruteforce code
class GFG {
    public int cutRod(int price[], int len) {
        if(len <= 0){
            return 0;
        }
        int maxValue = 0;
        for(int i=0; i < len;i++){
            int val = price[i] + cutRod(price, len-i-1);
            if(maxValue < val){
                maxValue = val;
            }
        }
        return maxValue;
    }
}