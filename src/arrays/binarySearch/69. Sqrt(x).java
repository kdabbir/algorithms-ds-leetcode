// 69. Sqrt(x): https://leetcode.com/problems/sqrtx/
// Easy
// Given a non-negative integer x, compute and return the square root of x.

// Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.

// Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.



// Example 1:

// Input: x = 4
// Output: 2
// Example 2:

// Input: x = 8
// Output: 2
// Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.


// Constraints:

// 0 <= x <= 231 - 1

// Template -1 Binary Search

class Solution {
    public int mySqrt(int x) {
        if(x < 2) return x;
        int start = 2, end = x;
        while(start <= end) {
            int mid = start + (end - start)/2;
            int midVal = x/mid;
            if(mid == midVal) {
                return mid;
            } else if(mid > midVal) {
                end = mid - 1;
            } else {
                start = mid +1;
            }
        }
        return end;
    }
}

// Time: O(NLogN)
// Space: O(1)

// Approach 2: Pocket Calculator Algorithm

class Solution {
    public int mySqrt(int x) {
      if (x < 2) return x;

      int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
      int right = left + 1;
      return (long)right * right > x ? left : right;
    }
  }

  // Time: O(1)
  // Space: O(1)


// Usually a pocket calculator computes well exponential functions and natural logarithms by having logarithm tables hardcoded or by the other means. Hence the idea is to reduce the square root computation to these two algorithms as well

// sqrt{x} = e^ 1\2 * log x

// That's some sort of cheat because of non-elementary function usage but it's how that actually works in a real life.