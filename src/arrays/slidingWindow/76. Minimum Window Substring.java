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

// Using int array

public String minWindow(String s, String t) {
    int [] map = new int[128];
    for (char c : t.toCharArray()) {
      map[c]++;
    }
    int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
    while (end < s.length()) {
      final char c1 = s.charAt(end);
      if (map[c1] > 0) counter--;
      map[c1]--;
      end++;
      while (counter == 0) {
        if (minLen > end - start) {
          minLen = end - start;
          minStart = start;
        }
        final char c2 = s.charAt(start);
        map[c2]++;
        if (map[c2] > 0) counter++;
        start++;
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
  }


// Template for substring problems



class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCount = new HashMap(); // Initialize map
        for(char sourceStr: s.toCharArray()) {
            charCount.put(sourceStr, 0);   // Populate hashmap
        }
        for(char targetStr: t.toCharArray()) {
            if(charCount.containsKey(targetStr)) {
                charCount.put(targetStr, charCount.get(targetStr) + 1);
            } else {
                return "";
            }
        }
        int minStartIdx = 0, smallestLength = Integer.MAX_VALUE, startIdx = 0, counter = t.length(); // Initialize two pointers
        while(endIdx < s.length()) {
            char endChar = s.charAt(endIdx);
            if(charCount.get(endChar) > 0) {
                counter--;
            }
            charCount.put(endChar, charCount.get(endChar) - 1);
            endIdx++;
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

// Template for substring problems

// The idea is a general solution for substring (longest/shortest/non-duplicated, etc)

// 1. Create a hashmap tracking occurences for any specific chars in the target substring
// 2. Create two pointers, faster = 0 and slower = 0, to track current width/distance/range of the two pointers. (read below for its usage)
// 3. Create a counter tracking if current width/distance/range is a valid substring range
// 4. When counter indicates it's a valid range, shrink/expand it's range until range's validity changed(by altering counter accordingly), record down the valid width and let it compare with the last value (using min()/max() accordingly)

int findSubstring(string s){
    Map<Character, Integer> charCount = new HashMap();  // Initialize map
    int counter; // Initialize counter to check whether the substring is valid
    int begin=0, end=0; //initialize two pointers, one point to tail and one  head
    int d; //the min/max length of substring

    for() { /* Initialize the hash map here */ }

    while(end<s.size()){ // Move end pointer first
        char endChar = s.charAt(endIdx);
        if(charCount.get(endChar) > 0) {
            counter--;   /* modify counter here to validate valid values getting added */
        }
        charCount.put(endChar, charCount.get(endChar) - 1); /* modify state after adding/removed */
        endIdx++;

        while(/* counter condition */){
            if(smallestLength > endIdx - startIdx) {
                smallestLength = endIdx - startIdx;
                minStartIdx = startIdx;
            }  /* update minLength here if finding minimum*/

            char startChar = s.charAt(startIdx);
            charCount.put(startChar, charCount.get(startChar) + 1);
            if(charCount.get(startChar) > 0) { // Modify counter here if current startChar makes a valid entry
                counter++;
            }
            startIdx++;             //increase start pointer window begin to make it invalid/valid again

        }
        /* update d here if finding maximum*/
    }
    return d;
}

// Similar problems with same template:
// 3. Longest Substring Without Repeating Characters:
// 340. Longest Substring with At Most K Distinct Characters
// 159. Longest Substring with At Most Two Distinct Characters
