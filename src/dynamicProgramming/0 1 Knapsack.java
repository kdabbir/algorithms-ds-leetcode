// 0 - 1 Knapsack Problem : https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
// Hard

// You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.
// In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

// Example 1:

// Input:
// N = 3
// W = 4
// values[] = {1,2,3}
// weight[] = {4,5,1}
// Output: 3
// Example 2:

// Input:
// N = 3
// W = 3
// values[] = {1,2,3}
// weight[] = {4,5,6}
// Output: 0

// Explanation: https://www.youtube.com/watch?v=8LusJS5-AGo&t=1s
// Refer to docs screenshot
    class Knapsack
    {
        // Returns the maximum value that can be put in a knapsack of capacity W
        static int knapSack(int W, int wt[], int val[], int n)
        {
            int[][] dpMemo = new int[n + 1][W + 1];

            for (int row = 0; row < dpMemo.length; row++) {
                for (int col = 0; col < dpMemo[row].length; col++) {
                    if(row == 0 || col == 0) dpMemo[row][col] = 0;
                    // gotcha: Taking from arrays then reduce 1, variables don't need to get reduced. (wt[row-1] vs col)
                    else if(wt[row - 1] <= col) {
                        // Here, first check is taking current value(val[row -1]) and remaining value from dp using dp[row-1][col - wt[row -1]]
                        // Second check is if we don't pick this value and take from computed dp

                        dpMemo[row][col] = Math.max(val[row - 1] + dpMemo[row - 1][col - wt[row-1]],
                                                    dpMemo[row - 1][col] );
                    } else {
                        // If current weight array value i.e wt[row - 1] cannot be picked for current capacity weight, pick computed value/already present value.

                        dpMemo[row][col] = dpMemo[row - 1][col];
                    }
                }
            }
            return dpMemo[wt.length][W];
        }
    }


