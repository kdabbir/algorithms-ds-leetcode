// 57. Insert Interval: https://leetcode.com/problems/insert-interval/

// Medium

// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

// You may assume that the intervals were initially sorted according to their start times.



// Example 1:

// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]
// Example 2:

// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
// Example 3:

// Input: intervals = [], newInterval = [5,7]
// Output: [[5,7]]
// Example 4:

// Input: intervals = [[1,5]], newInterval = [2,3]
// Output: [[1,5]]
// Example 5:

// Input: intervals = [[1,5]], newInterval = [2,7]
// Output: [[1,7]]


// Constraints:

// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= intervals[i][0] <= intervals[i][1] <= 105
// intervals is sorted by intervals[i][0] in ascending order.
// newInterval.length == 2
// 0 <= newInterval[0] <= newInterval[1] <= 105


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> finalIntervals = new ArrayList<>();
        for(int[] currInterval: intervals) {
            if(newInterval == null || currInterval[1] < newInterval[0]) {
                finalIntervals.add(currInterval);
            } else if(currInterval[0] > newInterval[1]) {
                finalIntervals.add(newInterval);
                finalIntervals.add(currInterval);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(newInterval[0], currInterval[0]);
                newInterval[1] = Math.max(newInterval[1], currInterval[1]);
            }
        }
        if(newInterval != null)
            finalIntervals.add(newInterval);

        return finalIntervals.toArray(new int[finalIntervals.size()][]);
    }
}
// Time: O(N)
// Space: O(N)


// Explanation:
// ___: current interval(i); _ _ _: newInterval

// 1) i.end < newInterval.start，then we can safely add i to result;
// 	newInterval still needs to be compared with latter intervals

// 	|________|
// 			       |_ _ _ _ _|

// 2) i.start > newInterval.end，then we can safely add both to result，
// 	and mark newInterval as null

// 			       |________|
// 	|_ _ _ _ _|

// 3) There is overlap between i and newInterval. We can merge i into newInterval,
// then use the updated newInterval to compare with latter intervals.


// 	|________|
// 		|_ _ _ _ _|

// 		|________|
// 	|_ _ _ _ _|

// Another approach.

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> finalIntervals = new ArrayList<>();
        int[] toAdd = newInterval;
        for(int[] currInterval: intervals) {
            if(currInterval[0] > toAdd[1]) {  //// curr interval comes after newInterval  |_ _ _ _ _|	|________|
                finalIntervals.add(toAdd);
                toAdd = currInterval;
            } else if(currInterval[1] >= toAdd[0]) { //// CurrInterval End comes after newIntervalStart and cuz of above check,it has to be intersecting  |_ _ _ _ _|
                                                                    ///       |________|
                toAdd[0] = Math.min(toAdd[0], currInterval[0]);
                toAdd[1] = Math.max(toAdd[1], currInterval[1]);
            } else { // if(currInterval[1] < toAdd[0]) Case when currInterval is less than newInterval.
                finalIntervals.add(currInterval);
            }
        }
        finalIntervals.add(toAdd);

        return finalIntervals.toArray(new int[finalIntervals.size()][]);
    }
}

// Time: O(N)
// Space: O(N)