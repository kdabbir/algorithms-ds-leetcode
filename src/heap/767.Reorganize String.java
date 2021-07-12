// 767. Reorganize String : https://leetcode.com/problems/reorganize-string/
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

// Greedy with heap
// Using waitQueue for storing till 2 characters, using 358.Rearrange String k distance apart logic.
// The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.

// In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.
// S will consist of lowercase letters and have length in range [1, 500].

class Solution {
    public String reorganizeString(String S) {
        if(S == null || S.length() == 0) return "";
        int k = 2;

        HashMap<Character, Integer> charCount = new HashMap<>();
        for(char str: S.toCharArray()){
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
        return res.length() == S.length() ? res.toString(): "";
    }
}


// Approach #2: Greedy with Heap [Accepted]
// Time Complexity:O(NlogA)), where N is the length of S, and A is the size of the alphabet. If A is fixed, this complexity is O(N).
// Space Complexity: O(A) If A is fixed, this complexity is O(1)

class Solution {
    public String reorganizeString(String S) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(int i = 0; i < S.length(); i++){
            charCount.put(S.charAt(i), charCount.getOrDefault(S.charAt(i), 0) + 1);
        }
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


