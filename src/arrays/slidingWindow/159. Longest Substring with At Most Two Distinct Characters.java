// 159. Longest Substring with At Most Two Distinct Characters: https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

// Medium

// Given a string s, return the length of the longest substring that contains at most two distinct characters.

// Example 1:

// Input: s = "eceba"
// Output: 3
// Explanation: The substring is "ece" which its length is 3.
// Example 2:

// Input: s = "ccaabbb"
// Output: 5
// Explanation: The substring is "aabbb" which its length is 5.


// Constraints:

// 1 <= s.length <= 104
// s consists of English letters.

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxLength = Integer.MIN_VALUE;
        int[] charCount = new int[128];
        int startIdx = 0, endIdx = 0, counter = 0;
        while(endIdx < s.length()) {
            char currChar = s.charAt(endIdx);
            if(charCount[currChar] == 0) {
                counter++;
            }
            charCount[currChar]++;
            endIdx++;
            while(counter > 2) {
                char startChar = s.charAt(startIdx);
                if(charCount[startChar] == 1) counter--;
                charCount[startChar]--;
                startIdx++;
            }
            maxLength = Math.max(maxLength, endIdx - startIdx);
        }
        return maxLength == Integer.MIN_VALUE ? 0: maxLength;
    }
}

// Time: O()