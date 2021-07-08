// 76. Minimum Window Substring: https://leetcode.com/problems/minimum-window-substring/

// Hard

// Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

// The testcases will be generated such that the answer is unique.

// A substring is a contiguous sequence of characters within the string.



// Example 1:

// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
// Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
// Example 2:

// Input: s = "a", t = "a"
// Output: "a"
// Explanation: The entire string s is the minimum window.
// Example 3:

// Input: s = "a", t = "aa"
// Output: ""
// Explanation: Both 'a's from t must be included in the window.
// Since the largest window of s only has one 'a', return empty string.


// Constraints:

// m == s.length
// n == t.length
// 1 <= m, n <= 105
// s and t consist of uppercase and lowercase English letters.


// Follow up: Could you find an algorithm that runs in O(m + n) time?

// Using sliding window.

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCount = new HashMap();
        for(char sourceStr: s.toCharArray()) {
            charCount.put(sourceStr, 0);
        }
        for(char targetStr: t.toCharArray()) {
            if(charCount.containsKey(targetStr)) {
                charCount.put(targetStr, charCount.get(targetStr) + 1);
            } else {
                return "";
            }
        }
        int minStartIdx = 0, smallestLength = Integer.MAX_VALUE, startIdx = 0, counter = t.length();
        for(int endIdx = 0; endIdx < s.length(); endIdx++) {
            char endChar = s.charAt(endIdx);
            if(charCount.get(endChar) > 0) {
                counter--;
            }
            charCount.put(endChar, charCount.get(endChar) - 1);

            while(counter == 0) {
                if(smallestLength > endIdx - startIdx) {
                    smallestLength = endIdx - startIdx;
                    minStartIdx = startIdx;
                }
                char startChar = s.charAt(startIdx);
                charCount.put(startChar, charCount.get(startChar) + 1);
                if(charCount.get(startChar) > 0) {
                    counter++;
                }
                startIdx++;
            }
        }
        return smallestLength == Integer.MAX_VALUE ? "" : s.substring(minStartIdx, minStartIdx + smallestLength + 1 );
    }
}

// Time: O(M+N)
// Space: O(M+N)