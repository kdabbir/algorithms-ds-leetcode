// 131. Palindrome Partitioning:https://leetcode.com/problems/palindrome-partitioning/
// Medium

// Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

// A palindrome string is a string that reads the same backward as forward.
// Example 1:

// Input: s = "aab"
// Output: [["a","a","b"],["aa","b"]]
// Example 2:

// Input: s = "a"
// Output: [["a"]]
 

// Constraints:

// 1 <= s.length <= 16
// s contains only lowercase English letters.


class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        backTrack(partitions, 0, s, new ArrayList<>());
        return partitions;
    }
    
    public void backTrack(List<List<String>> output, int processIndex, String s, List<String> tempList) {
        if(processIndex == s.length()) {
            output.add(new ArrayList<>(tempList)); 
        } else {
            for(int idx = processIndex; idx < s.length(); idx++) {
              if(isPalindrome(s, processIndex, idx)) {
                  tempList.add(s.substring(processIndex, idx + 1));
                backTrack(output, idx + 1, s, tempList);
                  tempList.remove(tempList.size() - 1);
              }  
            }
        }
    }
    
    public boolean isPalindrome(String s, int low, int high) {
        while(low < high) {
            if(s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }
}

// Time complexity: O( N * 2 ^ N)
// Space complexity: O(N)