// 767. Reorganize String
// Medium

// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

// If possible, output any possible result.  If not possible, return the empty string.

// Example 1:

// Input: S = "aab"
// Output: "aba"
// Example 2:

// Input: S = "aaab"
// Output: ""
// Note:

// S will consist of lowercase letters and have length in range [1, 500].

class Solution {
    public String reorganizeString(String S) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(int i = 0; i < S.length(); i++){
            charCount.put(S.charAt(i), charCount.getOrDefault(S.charAt(i), 0) + 1);
        }
        //System.out.print(charCount);
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> charCount.get(b) - charCount.get(a));
        maxHeap.addAll(charCount.keySet());
        StringBuilder outputStr = new StringBuilder();
        while(maxHeap.size() > 1){
            char curr = maxHeap.remove();
            char next = maxHeap.remove();
            outputStr.append(curr);
            outputStr.append(next);
            charCount.put(curr, charCount.get(curr) - 1);
            charCount.put(next, charCount.get(next) - 1);
            if(charCount.get(curr) > 0)
                maxHeap.add(curr);
            if(charCount.get(next) > 0)
                maxHeap.add(next);
        }
        
        if(!maxHeap.isEmpty()){
            char last = maxHeap.remove();
            if(charCount.get(last) > 1){ 
                return "";
            }
            else {
                outputStr.append(last);   
            }
        }
        
        return outputStr.toString();
    }
}