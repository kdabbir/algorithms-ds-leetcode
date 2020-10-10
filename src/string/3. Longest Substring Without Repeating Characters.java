// 3. Longest Substring Without Repeating Characters: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Medium
// Given a string s, find the length of the longest substring without repeating characters.



// Example 1:

// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
// Example 2:

// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:

// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
// Example 4:

// Input: s = ""
// Output: 0
 

// Constraints:

// 0 <= s.length <= 5 * 104
// s consists of English letters, digits, symbols and spaces.


// Sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0, maxLength = 0;
        Map<Character, Integer> seen = new HashMap<>();
        while(start < s.length() && end < s.length()) {
            char curr = s.charAt(end);
            if(!seen.containsKey(curr)) {
                seen.put(curr, end);
                end++;
                maxLength = Math.max(maxLength, end - start);
            } else {
                seen.remove(curr);
                start++;
            }
        }
        return maxLength;
    }
}

// Time complexity: 2 * O(N)
// Space: O(min(N,RES))

// Optimized sliding window with O(N) time complexity

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Map<Character, Integer> seen = new HashMap<>();
        for(int start = 0, end = 0; end < s.length(); end++) {
            char curr = s.charAt(end);
            if(seen.containsKey(curr)) {
                start = Math.max(seen.get(curr), start);
            }
            maxLength = Math.max(maxLength, end - start + 1);
            seen.put(curr, end + 1);
        }
        return maxLength;
    }
}


// Time complexity: O(N)
// Space: O(min(N,RES))



