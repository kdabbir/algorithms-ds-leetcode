// 392. Is Subsequence : https://leetcode.com/problems/is-subsequence/
// Easy

// Given a string s and a string t, check if s is subsequence of t.

// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

// Follow up:
// If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

// Credits:
// Special thanks to @pbrother for adding this problem and creating all test cases.

// Example 1:

// Input: s = "abc", t = "ahbgdc"
// Output: true
// Example 2:

// Input: s = "axc", t = "ahbgdc"
// Output: false
 
// Constraints:

// 0 <= s.length <= 100
// 0 <= t.length <= 10^4
// Both strings consists only of lowercase characters.


class Solution {
    public boolean isSubsequence(String s, String t) {
        int s1Bound = s.length(), s2Bound = t.length();
        int s1Pointer = 0, s2Pointer = 0;
        
        // Have 2 pointers, expand both pointers if character is the same. Else, move the bigger string. 
        // At the end if smaller string pointer is at last element of the array, we have confirmed the subsequence.

        while(s1Pointer < s1Bound && s2Pointer < s2Bound){
            if(t.charAt(s2Pointer) == s.charAt(s1Pointer)) {
                s1Pointer++;
                s2Pointer++;
            } else {
                s2Pointer++;
            }
        }
        
        return s1Pointer == s1Bound;
    }
}

// Time: O(N)
// Space: O(1)

// Answer to follow-up