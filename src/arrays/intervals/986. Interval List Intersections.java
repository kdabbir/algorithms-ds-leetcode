// 986. Interval List Intersections: https://leetcode.com/problems/interval-list-intersections/

// Medium

// You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

// Return the intersection of these two interval lists.

// A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.

// The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].



// Example 1:


// Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
// Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// Example 2:

// Input: firstList = [[1,3],[5,9]], secondList = []
// Output: []
// Example 3:

// Input: firstList = [], secondList = [[4,8],[10,12]]
// Output: []
// Example 4:

// Input: firstList = [[1,7]], secondList = [[3,10]]
// Output: [[3,7]]


// Constraints:

// 0 <= firstList.length, secondList.length <= 1000
// firstList.length + secondList.length >= 1
// 0 <= starti < endi <= 109
// endi < starti+1
// 0 <= startj < endj <= 109
// endj < startj+1


// Two pointers.Refer to doc for intuition.

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> intersectionList = new ArrayList<>();
        int idx1 = 0, idx2 = 0;

        while(idx1 < firstList.length && idx2 < secondList.length) {
              // Let's check if A[idx1] intersects B[idx2].
              // low - the startpoint of the intersection
              // high - the endpoint of the intersection
            int lowInterval = Math.max(firstList[idx1][0], secondList[idx2][0]);
             int highInterval = Math.min(firstList[idx1][1], secondList[idx2][1]);
            if(lowInterval <= highInterval) {
                intersectionList.add(new int[] {lowInterval, highInterval});
            }

            // Move smallest pointer. Remove the interval with the smallest endpoint

            if(firstList[idx1][1] < secondList[idx2][1]) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return intersectionList.toArray(new int[intersectionList.size()][]);
    }
}

// Time: O(M+N)
// Space: O(M+N)