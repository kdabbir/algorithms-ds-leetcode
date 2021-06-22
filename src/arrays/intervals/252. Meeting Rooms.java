// 252. Meeting Rooms: https://leetcode.com/problems/meeting-rooms/

// Easy

// Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
// Example 1:

// Input: intervals = [[0,30],[5,10],[15,20]]
// Output: false
// Example 2:

// Input: intervals = [[7,10],[2,4]]
// Output: true

// Constraints:

// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti < endi <= 106


class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        int currEnd = intervals[0][1];


        for(int idx = 1; idx < intervals.length; idx++) {
            if(intervals[idx][0] < currEnd) {
                return false;
            } else {
                currEnd = intervals[idx][1];
            }
        }
        return true;
    }
}