// 896. Monotonic Array: https://leetcode.com/problems/monotonic-array/
// Easy

// An array is monotonic if it is either monotone increasing or monotone decreasing.

// An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

// Return true if and only if the given array A is monotonic.
// Example 1:

// Input: [1,2,2,3]
// Output: true
// Example 2:

// Input: [6,5,4,4]
// Output: true
// Example 3:

// Input: [1,3,2]
// Output: false
// Example 4:

// Input: [1,2,4,5]
// Output: true
// Example 5:

// Input: [1,1,1]
// Output: true
 

// Note:

// 1 <= A.length <= 50000
// -100000 <= A[i] <= 100000


class Solution {
    public boolean isMonotonic(int[] A) {
        if(A == null || A.length <= 2) return true;
        boolean isIncreasing = true, isDecreasing = true;
        
        for(int idx = 1; idx < A.length; idx++) {
            if(A[idx] > A[idx - 1]) isDecreasing = false;
           
            else if(A[idx] < A[idx-1])  isIncreasing = false;
            
        }
        return isDecreasing || isIncreasing;
    }

    // Another way
    // 3
    // class Program {
    // 4
    //   // O(n) time | O(1) space - where n is the length of the array
    // 5
    //   public static boolean isMonotonic(int[] array) {
    // 6
    //     if (array.length <= 2) return true;
    // 7
    // ​
    // 8
    //     var direction = array[1] - array[0];
    // 9
    //     for (int i = 2; i < array.length; i++) {
    // 10
    //       if (direction == 0) {
    // 11
    //         direction = array[i] - array[i - 1];
    // 12
    //         continue;
    // 13
    //       }
    // 14
    // ​
    // 15
    //       if (breaksDirection(direction, array[i - 1], array[i])) {
    // 16
    //         return false;
    // 17
    //       }
    // 18
    //     }
    // 19
    //     return true;
    // 20
    //   }
    // 21
    // ​
    // 22
    //   public static boolean breaksDirection(int direction, int previous, int current) {
    // 23
    //     var difference = current - previous;
    // 24
    //     if (direction > 0) return difference < 0;
    // 25
    //     return difference > 0;
    // 26
    //   }
    // 27
    // }
    // 28
}

