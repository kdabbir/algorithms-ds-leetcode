// 358. Rearrange String k Distance Apart
// Hard

// Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

// All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

// Example 1:

// Input: s = "aabbcc", k = 3
// Output: "abcabc" 
// Explanation: The same letters are at least distance 3 from each other.
// Example 2:

// Input: s = "aaabc", k = 3
// Output: "" 
// Explanation: It is not possible to rearrange the string.
// Example 3:

// Input: s = "aaadbbcc", k = 2
// Output: "abacabcd"
// Explanation: The same letters are at least distance 2 from each other.


//Logic explanation:

// For those who are familiar with EDF (earilest deadline first) algorithm in scheduling, this problem is actually (I think) a different statement or a variation of the original scheduling problem.

// Before filling in the first position, think about this: what is the deadline for the first occurance of every character? e.g. "aaabbc" and k=2.

// In this case, deadline for "a" is index 1, that is, if we do not fill the first "a" at index 0 or index 1, we cannot finish the job. Deadline for b is 3 and deadline for c is 5. Thus we choose the one with most urgent deadline.

// Therefore, the logic behind choosing maxiumum remaining count is that the char with larger remaining count has a more urgent deadline.

// The second "a" will not "appear" until 2 steps after where we put the first "a". It will come with a different deadline than when there are 3 remaining "a"s.


class Solution {
    public String rearrangeString(String s, int k) {
        // Input validations.
        if(s == null || s.length() == 0) return "";
        if(k == 0) return s;
        
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(char str: s.toCharArray()){
            charCount.put(str, charCount.getOrDefault(str, 0) + 1);
        }
        
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> charCount.get(b) - charCount.get(a));
        maxHeap.addAll(charCount.keySet());
                
        Queue<Character> waitQueue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        while(!maxHeap.isEmpty()){
            char curr = maxHeap.remove();
            res.append(curr);
            charCount.put(curr, charCount.get(curr) - 1);
            waitQueue.add(curr);
            
            if(waitQueue.size() < k){
                continue;
            }
            char front = waitQueue.remove();
            if(charCount.get(front) > 0){
                maxHeap.add(front);
            }
        }
        return res.length() == s.length() ? res.toString(): "";
    }
}