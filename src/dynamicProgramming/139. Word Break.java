
// Approach 1: Recursion with memoization
// TLE
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        return recurseSearch(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    
    public boolean recurseSearch(String s, HashSet<String> set, int start, Boolean[] memo) {
        if(start == s.length()) {
            return true;
        }
        
        if(memo[start] != null) return memo[start];
        
        for(int end = start + 1; end <= s.length(); end++) {
            if(set.contains(s.substring(start, end)) && recurseSearch(s, set, end, memo)) {
                return memo[start] = true;
            }
        }
        
        return false;
    }
}


// Approach 1: Recursion with memoization
// TLE

// With hashset instead of boolean[]
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return recurseSearch(s, 0, new HashSet<Integer>(), new HashSet(wordDict));
    }
    
    public boolean recurseSearch(String s, int start, HashSet<Integer> seen, HashSet<String> wordDict) {
        if(start == s.length()) {
            return true;    
        }
        
        if(seen.contains(start)) {
            return true;
        }
           
        for(int i = start + 1; i <= s.length(); i++) {
            if(wordDict.contains(s.substring(start, i)) && recurseSearch(s, i, seen, wordDict)) {
                 seen.add(i);
                 return true;
            }
        }
        return false;
    }
}