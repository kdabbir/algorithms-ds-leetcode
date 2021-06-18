// 309. Best Time to Buy and Sell Stock with Cooldown: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

// // Medium

// You are given an array prices where prices[i] is the price of a given stock on the ith day.

// Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

// After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



// Example 1:

// Input: prices = [1,2,3,0,2]
// Output: 3
// Explanation: transactions = [buy, sell, cooldown, buy, sell]
// Example 2:

// Input: prices = [1]
// Output: 0


// Constraints:

// 1 <= prices.length <= 5000
// 0 <= prices[i] <= 1000

// Sliding window

// Refer to doc for explanation
// Basically we consider state transitions as per actions we can do. We can held a stock (held) and sell it at later point(sold), reset being cooldown.
// sold[i]=hold[i−1]+price[i]
// held[i]=max(held[i−1],reset[i−1]−price[i])
// reset[i]=max(reset[i−1],sold[i−1])
// max(sold[i], reset[i]) will give max profit value. Since only sold and reset are when we can consider profit. Held is treated as loss.

class Solution {
    public int maxProfit(int[] prices) {
        int held = Integer.MIN_VALUE, sold = Integer.MIN_VALUE, reset = 0;
        for(int currPrice : prices) {
            int preSold = sold;
            sold = held + currPrice;
            held = Math.max(held, reset - currPrice);
            reset = Math.max(reset, preSold);
        }
        return Math.max(sold, reset);
    }
}

// Space: O(1)
// Time: O(N)

