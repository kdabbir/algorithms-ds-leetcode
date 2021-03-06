// 91. Decode Ways: https://leetcode.com/problems/decode-ways/
// Medium

// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given a non-empty string containing only digits, determine the total number of ways to decode it.

// The answer is guaranteed to fit in a 32-bit integer.

 

// Example 1:

// Input: s = "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// Example 2:

// Input: s = "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
// Example 3:

// Input: s = "0"
// Output: 0
// Explanation: There is no character that is mapped to a number starting with '0'. We cannot ignore a zero when we face it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
// Example 4:

// Input: s = "1"
// Output: 1
 

// Constraints:

// 1 <= s.length <= 100
// s contains only digits and may contain leading zero(s).


// Using DP

class Solution {
    public int numDecodings(String s) {
        if(s.length() == 0) return 0;
        
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // Since Combinations of len(n) is n!/r!(n-r)! (Even if r =0(min)) = 0!/0!*0! = 1 and also, we need this base case for calculating sum for initial 2 digits
        
           // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for(int i = 2; i <= s.length(); i++){
            int oneDigit = Integer.valueOf(s.substring(i-1, i));
            int twoDigit = Integer.valueOf(s.substring(i-2, i));
            // Check if successful single digit decode is possible.
            if(oneDigit > 0) {
                dp[i] += dp[i-1];
            }
            
            // Check if successful two digit decode is possible.
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
        
    }
}

// Time: O(N)
// Space: O(N)