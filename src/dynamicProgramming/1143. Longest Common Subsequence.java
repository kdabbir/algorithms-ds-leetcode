// 1143. Longest Common Subsequence
// Medium

// Given two strings text1 and text2, return the length of their longest common subsequence.

// A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

// If there is no common subsequence, return 0.

 

// Example 1:

// Input: text1 = "abcde", text2 = "ace" 
// Output: 3  
// Explanation: The longest common subsequence is "ace" and its length is 3.
// Example 2:

// Input: text1 = "abc", text2 = "abc"
// Output: 3
// Explanation: The longest common subsequence is "abc" and its length is 3.
// Example 3:

// Input: text1 = "abc", text2 = "def"
// Output: 0
// Explanation: There is no such common subsequence, so the result is 0.
 

// Constraints:

// 1 <= text1.length <= 1000
// 1 <= text2.length <= 1000
// The input strings consist of lowercase English characters only.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
    	int dp[] = new int[text1.length() + 1];
        char[] text1Arr = text1.toCharArray();
        char[] text2Arr = text2.toCharArray(); 
		for (int i = 1; i <= text2.length(); i++) {
			int maxLen = 0;
			for (int j = 1; j <= text1.length(); j++) {
				int lenSoFar = dp[j];
				if (text2Arr[i - 1] == text1Arr[j - 1]) {
					dp[j] =  maxLen + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
				maxLen = lenSoFar;
			}
		}
		return dp[text1.length()];
        
    }
}