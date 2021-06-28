// 56. Merge Intervals: https://leetcode.com/problems/merge-intervals/

// Medium

// Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

// Example 1:

// Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
// Output: [[1,6],[8,10],[15,18]]
// Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// Example 2:

// Input: intervals = [[1,4],[4,5]]
// Output: [[1,5]]
// Explanation: Intervals [1,4] and [4,5] are considered overlapping.

// Constraints:

// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return null;
        Arrays.sort(intervals,(i1, i2) -> Integer.compare(i1[0], i2[0]));
        LinkedList<int[]> intervalList = new LinkedList<>();
        for(int[] currInterval: intervals) {
            if(intervalList.isEmpty() || currInterval[0] > intervalList.getLast()[1]){
                intervalList.add(currInterval);
            } else{
                intervalList.getLast()[1] = Math.max(intervalList.getLast()[1], currInterval[1]);
            }
        }
        return intervalList.toArray(new int[intervalList.size()][]);
    }

// Time: O(NLogN)
// Space: O(N)

// Alternate approach

public List<Interval> merge(List<Interval> intervals) {
    if(intervals == null || intervals.size() == 0) return Collections.emptyList();
    Collections.sort(intervals,new Comparator<Interval>(){
        public int compare (Interval i1, Interval i2){
            return i1.start - i2.start;
        }
    });
    List<Interval> list = new LinkedList<>();
    Interval pre = new Interval(intervals.get(0).start,intervals.get(0).end);
    for(Interval curr: intervals){
        if(pre.end < curr.start) {
            list.add(pre);
            pre = curr;
        }else {
            pre.start = Math.min(pre.start, curr.start);
            pre.end = Math.max(pre.end, curr.end);
        }
    }
    list.add(pre);
    return list;
}

}
