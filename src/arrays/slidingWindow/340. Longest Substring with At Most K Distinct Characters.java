// 340. Longest Substring with At Most K Distinct Characters: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

// Medium

// Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

// Example 1:

// Input: s = "eceba", k = 2
// Output: 3
// Explanation: The substring is "ece" with length 3.
// Example 2:

// Input: s = "aa", k = 1
// Output: 2
// Explanation: The substring is "aa" with length 2.


// Constraints:

// 1 <= s.length <= 5 * 104
// 0 <= k <= 50


// Sliding window template format.

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLength = Integer.MIN_VALUE;
        int[] charCount = new int[128];
        int startIdx = 0, endIdx = 0, counter = 0;
        while(endIdx < s.length()) {
            char currChar = s.charAt(endIdx);
            if(charCount[currChar] == 0) {
                counter++;
            }
            charCount[currChar]++;
            endIdx++; // Since we are incrementing endIdx before taking maxLength, it is endIdx - startIdx, else it will be endIdx - startIdx + 1 for converting index to length

            while(counter > k) {
                char startChar = s.charAt(startIdx);
                charCount[startChar]--;
                if(charCount[startChar] == 0) counter--;
                startIdx++;
            }
            maxLength = Math.max(maxLength, endIdx - startIdx);
        }
        return maxLength == Integer.MIN_VALUE ? 0: maxLength;
    }
}
// Time: O(2*N)
// Space: O(N)