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


// Greedy approach. We consider max number of placeable slots and create empty slots as per cooldown. Then figure out idle slots

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for(char task: tasks) {
            frequencies[task - 'A']++;
        }
        Arrays.sort(frequencies);
        int maxFreqTask = frequencies[25];
        int idleTime = (maxFreqTask - 1) * n; // Max idle time is 1 minus since we dont consider adding a task at end. Here we consider that maxTask is already added.

        for(int currTask = frequencies.length - 2; currTask >=0 && idleTime > 0; currTask--) {
            if(frequencies[currTask] == maxFreqTask) {  // If there are duplicate maxFreqTask then idleTime should be reduced by 1 since we dont consider adding a task at end i.e we are grouping with max task value.
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

// Another optimized greedy algorithm with O(N) space, no sorting, O(1) space.

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        int maxTask = 0, maxTaskCount = 0;
        for(char task: tasks) {
            frequencies[task - 'A']++;
            if(frequencies[task - 'A'] > maxTask) {
                maxTask = frequencies[task - 'A'] ;
                maxTaskCount = 1;
            } else if(frequencies[task - 'A'] == maxTask){
                maxTaskCount++;
            }
        }

        int totalNoOfParts = maxTask - 1;
        int partLength = n - (maxTaskCount - 1);
        int totalEmptySlots = totalNoOfParts * partLength;
        int slotsToFill = tasks.length - (maxTask * maxTaskCount);
        int idleSlots = Math.max(0, totalEmptySlots - slotsToFill);
        return tasks.length + idleSlots;
    }
}
// Explanation
// maxTask = How many times did the max element occur; In AAABBC, max is 3;
// maxTaskCount = AAABBBCCD, maxTaskCount is 2 because A and B both are max occuring thrice;
// totalNoOfParts = In AAABBC and n=2, we arrange like - AAA, so ** and **, so 2 parts;
// partLength or NewN = In AAABBBC and n=2, we arrange like - ABABAB, which is, XXX, so n for us will decrease
// totalEmptySlots = Empty Slots, which is part length * no of parts
// *slotsToFill/AvailableTasks = all tasks - (the max parts that we've considered so far);
// *idleSlots = If empty slots are less, we don't care; if they are more, we just want, how many more?
// Finally, combination of idles and total number of tasks will give answer for intervals needed.

// Time: O(N)
// Space: O(1)