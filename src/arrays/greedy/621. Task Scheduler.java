// 621. Task Scheduler: https://leetcode.com/problems/task-scheduler/
// Medium

// Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

// However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

// Return the least number of units of times that the CPU will take to finish all the given tasks.



// Example 1:

// Input: tasks = ["A","A","A","B","B","B"], n = 2
// Output: 8
// Explanation:
// A -> B -> idle -> A -> B -> idle -> A -> B
// There is at least 2 units of time between any two same tasks.
// Example 2:

// Input: tasks = ["A","A","A","B","B","B"], n = 0
// Output: 6
// Explanation: On this case any permutation of size 6 would work since n = 0.
// ["A","A","A","B","B","B"]
// ["A","B","A","B","A","B"]
// ["B","B","B","A","A","A"]
// ...
// And so on.
// Example 3:

// Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
// Output: 16
// Explanation:
// One possible solution is
// A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A


// Constraints:

// 1 <= task.length <= 104
// tasks[i] is upper-case English letter.
// The integer n is in the range [0, 100].

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for(char task: tasks) {
            frequencies[task - 'A']++;
        }
        Arrays.sort(frequencies);
        int maxFreqTask = frequencies[25];
        int idleTime = (maxFreqTask - 1) * n; // Max idle time is 1 minus since we dont consider adding a task at end.

        for(int currTask = frequencies.length - 2; currTask >=0 && idleTime > 0; currTask--) {
            if(frequencies[currTask] == maxFreqTask) {  // If there are duplicate maxFreqTask then idleTime should be reduced by 1 since we dont consider adding a task at end.
                idleTime -= frequencies[currTask] - 1;
            } else {
                idleTime -= frequencies[currTask];
            }
            //idleTime -= Math.min(maxTask - 1, frequencies[currTask]); // Alternate way to code this out.
        }
        idleTime = Math.max(0, idleTime);
        return idleTime + tasks.length;
    }
}

// Time Complexity: O(N)
// Space: O(N)