// 266. Palindrome Permutation: https://leetcode.com/problems/palindrome-permutation/
// Easy

// Given a string s, return true if a permutation of the string could form a palindrome.

// Example 1:

// Input: s = "code"
// Output: false
// Example 2:

// Input: s = "aab"
// Output: true
// Example 3:

// Input: s = "carerac"
// Output: true=

// Constraints:

// 1 <= s.length <= 5000
// s consists of only lowercase English letters.

// Using one-pass map approach.
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for(int idx = 0; idx < s.length(); idx++) {
            map[s.charAt(idx)]++;
            if(map[s.charAt(idx)] % 2 == 0){
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }
}

// Using Set, one-pass approach.
class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for(int idx = 0; idx < s.length(); idx++) {
            if(!set.add(s.charAt(idx))){
                set.remove(s.charAt(idx));
            }
        }
        return set.size() <= 1;
    }
}

// Using hash-map, two pass approach
public class Solution {
    public boolean canPermutePalindrome(String s) {
        HashMap < Character, Integer > map = new HashMap < > ();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (char key: map.keySet()) {
            count += map.get(key) % 2;
        }
        return count <= 1;
    }
}

// Using array of 128 characters bytecode instead of hash-map.
   public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        int count = 0;
        for (int key = 0; key < map.length && count <= 1; key++) {
            count += map[key] % 2;
        }
        return count <= 1;
    }
}
