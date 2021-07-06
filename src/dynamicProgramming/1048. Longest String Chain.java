// 1048. Longest String Chain: https://leetcode.com/problems/longest-string-chain/

// Medium

// You are given an array of words where each word consists of lowercase English letters.

// wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

// For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
// A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

// Return the length of the longest possible word chain with words chosen from the given list of words.



// Example 1:

// Input: words = ["a","b","ba","bca","bda","bdca"]
// Output: 4
// Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
// Example 2:

// Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
// Output: 5
// Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
// Example 3:

// Input: words = ["abcd","dbqca"]
// Output: 1
// Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
// ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.


// Constraints:

// 1 <= words.length <= 1000
// 1 <= words[i].length <= 16
// words[i] only consists of lowercase English letters.

// Top-Down Dynamic Programming (Recursion + Memoization)

class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> wordsLen = new HashMap<>();
        Set<String> wordsPresent = new HashSet<>();
        Collections.addAll(wordsPresent, words);
        int longestLen = 0;

        for(String word: words) {
            longestLen = Math.max(longestLen, dfs(word, wordsLen, wordsPresent));
        }
        return longestLen;
    }

    public int dfsGetWordLen(String word, Map<String, Integer> wordsLen, Set<String> wordsSet){
        if(wordsLen.containsKey(word)) {
            return wordsLen.get(word);
        }
        int maxLen = 1;
        StringBuilder sb = new StringBuilder(word);

        for(int idx = 0; idx < word.length(); idx++) {
            sb.deleteCharAt(idx);
            String newWord = sb.toString();
            if(wordsSet.contains(newWord)){
                int wordLen = 1 + dfs(newWord, wordsLen, wordsSet);
                maxLen = Math.max(maxLen, wordLen);
            }
            sb.insert(idx, word.charAt(idx));

        }
        wordsLen.put(word, maxLen);

        return maxLen;
    }
}

// Time complexity: O (L2 * N) - L- max length of word.
// Space: O(N)

// Bottom up dp

class Solution {
    public int longestStrChain(String[] words) {
        int longestStrLen = 0;
        Arrays.sort(words, (a,b) -> a.length() - b.length());
        Map<String, Integer> wordsLen = new HashMap<>();
        for(String word: words) {
            int currLen = 1;
            StringBuilder sb = new StringBuilder(word);
            for(int idx = 0; idx < word.length(); idx++) {
                sb.deleteCharAt(idx);
                String newWord = sb.toString();
                int previousLen = wordsLen.getOrDefault(newWord, 0);
                currLen = Math.max(currLen, previousLen + 1);
                sb.insert(idx, word.charAt(idx));
            }
            wordsLen.put(word, currLen);
            longestStrLen = Math.max(longestStrLen, currLen);
        }
        return longestStrLen;
    }
}

// Time: O(NlogN + N * L ^ 2) L - Length of maximum word.
// Space: O(N)