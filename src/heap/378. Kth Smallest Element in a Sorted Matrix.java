// 378. Kth Smallest Element in a Sorted Matrix: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

// Medium

// Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.



// Example 1:

// Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
// Output: 13
// Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
// Example 2:

// Input: matrix = [[-5]], k = 1
// Output: -5


// Constraints:

// n == matrix.length
// n == matrix[i].length
// 1 <= n <= 300
// -109 <= matrix[i][j] <= 109
// All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
// 1 <= k <= n2

// Using minHeap.
public class HeapNode {
    public int value;
    public int column;
    public int row;

    public HeapNode(int value,int row, int column) {
        this.value = value;
        this.column = column;
        this.row = row;
    }
}

class HeapComparator implements Comparator<HeapNode> {
    public int compare(HeapNode n1, HeapNode n2) {
        return n1.value - n2.value;
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int matLen = matrix.length;
        int colLen = matrix[0].length;
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<HeapNode>(Math.min(matLen, k), new HeapComparator());
        for(int row = 0; row < matLen; row++) {
            minHeap.offer(new HeapNode(matrix[row][0], row, 0));
        }
        HeapNode currElement = minHeap.peek();
        while(k > 0) {
            currElement = minHeap.poll();
            if(currElement.column + 1 < colLen) {
                minHeap.offer(new HeapNode(matrix[currElement.row][currElement.column + 1], currElement.row, currElement.column + 1));
            }

            k--;
        }
        return currElement.value;
    }
}

// Using binary search

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
       int matLen = matrix.length;
       int start = matrix[0][0], end = matrix[matLen - 1][matLen - 1];
        while(start < end) {
            int mid = start + (end - start)/2;
            // first number is the smallest and the second number is the largest
            int[] startEndPair = {matrix[0][0],matrix[matLen - 1][matLen - 1]};
            int countOfValuesLessThanMid = countLessThanMid(matrix, mid, startEndPair);
            if(countOfValuesLessThanMid == k ) {
                return startEndPair[0];
            } else if(countOfValuesLessThanMid < k) { // search higher since we need a bigger left half.
                start = startEndPair[1];
            } else {
                end = startEndPair[0]; // search lower
            }
        }
        return start;

    }

    public int countLessThanMid(int[][] matrix, int mid, int[] startEndPair) {
        int matLen = matrix.length,  row = matLen - 1,  col = 0;
        int count = 0;
        while(row >= 0 && col < matLen) {
            if(matrix[row][col] > mid) {
                 // as matrix[row][col] is bigger than the mid, let's keep track of the
                 // smallest number greater than the mid
                startEndPair[1] = Math.min(startEndPair[1], matrix[row][col]);
                row--;
            } else {
                 // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                 // biggest number less than or equal to the mid
                startEndPair[0] = Math.max(startEndPair[0], matrix[row][col]);
                count+= row + 1;
                col++;
            }
        }
        return count;
    }
}

// Time: O(N X Log(Max-Min))
// Space: O(1)