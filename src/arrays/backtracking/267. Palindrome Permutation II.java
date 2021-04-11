// 267. Palindrome Permutation II: https://leetcode.com/problems/palindrome-permutation-ii/
// Medium

// Given a string s, return all the palindromic permutations (without duplicates) of it.

// You may return the answer in any order. If s has no palindromic permutation, return an empty list.



// Example 1:

// Input: s = "aabb"
// Output: ["abba","baab"]
// Example 2:

// Input: s = "abc"
// Output: []


// Constraints:

// 1 <= s.length <= 16
// s consists of only lowercase English letters.



// Backtracking approach - TLE
class Solution {
    public List<String> generatePalindromes(String s) {

        List<String> output = new ArrayList<>();
        backTrackPermutations(s, new boolean[s.length()], 0, new ArrayList<>(), output) ;
        return output;
    }

    public void backTrackPermutations(String s,boolean[] seen, int startIdx, List<Character> tempList, List<String> output)     {
        if(checkIfPalindrome(tempList) && tempList.size() == s.length()){

            StringBuilder builder = new StringBuilder(tempList.size());
            for(Character ch: tempList)
            {
                builder.append(ch);
            }
            if(!output.contains(builder.toString())){
                output.add(builder.toString());
            }
            return;
        }

        for(int currIdx = 0; currIdx < s.length(); currIdx++) {
            if(seen[currIdx]) continue;
            tempList.add(s.charAt(currIdx));
            seen[currIdx] = true;
            backTrackPermutations(s, seen, currIdx + 1, tempList, output);
            tempList.remove(tempList.size()-1);
            seen[currIdx] = false;
        }
    }

    public boolean checkIfPalindrome(List<Character> stringList) {
    int low = 0, high = stringList.size() - 1;
    while(low < high) {
        if(stringList.get(low) != stringList.get(high)){
            return false;
        }
        low++;
        high--;
    }
    return true;
    }
}