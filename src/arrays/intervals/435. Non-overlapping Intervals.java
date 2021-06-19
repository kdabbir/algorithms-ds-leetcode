// 435. Non-overlapping Intervals: https://leetcode.com/problems/non-overlapping-intervals/

// Medium

// Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



// Example 1:

// Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
// Output: 1
// Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
// Example 2:

// Input: intervals = [[1,2],[1,2],[1,2]]
// Output: 2
// Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
// Example 3:

// Input: intervals = [[1,2],[2,3]]
// Output: 0
// Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


// Constraints:

// 1 <= intervals.length <= 2 * 104
// intervals[i].length == 2
// -2 * 104 <= starti < endi <= 2 * 104


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        int intervalRemoveCount = 0;
        Arrays.sort(intervals, (a1, a2) -> Integer.compare(a1[0], a2[0]));

        int endInterval = intervals[0][1];

        for(int idx = 1; idx < intervals.length; idx++) {
            if(intervals[idx][0]  < endInterval) {
                endInterval = Math.min(endInterval, intervals[idx][1]); // Since, we are sorting by startInterval, we cant be sure if endInterval will be smallest. We need smallest so we remove minimal no of intervals.
                intervalRemoveCount++;
            } else{
                endInterval = intervals[idx][1];
            }
        }
        return intervalRemoveCount;
    }
}

// Time: O(NLogN)
// Space: O(1)