// 300. Longest Increasing Subsequence
// Medium

// Given an unsorted array of integers, find the length of longest increasing subsequence.

// Example:

// Input: [10,9,2,5,3,7,101,18]
// Output: 4 
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
// Note:

// There may be more than one LIS combination, it is only necessary for you to return the length.
// Your algorithm should run in O(n2) complexity.
// Follow up: Could you improve it to O(n log n) time complexity?


// Approach 1: Dynamic Programming with Binary Search
// Time complexity: O(NLogN)
// Space:O(N)
// tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
// For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

// len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
// len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
// len = 3   :      [4, 5, 6]            => tails[2] = 6
// We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.

// For example [10,9,2,5,3,7,101,18], dp array will have values 2 , 3, 7, 18

// Each time we only do one of the two:

// (1) if x is larger than all tails, append it, increase the size by 1
// (2) if tails[i-1] < x <= tails[i], update tails[i]
// Doing so will maintain the tails invariant. The the final answer is just the size.


public int lengthOfLIS(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int[] dp = new int[nums.length];
    int maxLen = 0;
    for(int num : nums) {
             /**
             * search in a bounded array dp[0..len) to find the index of the
             * insert point for the current num
             */
        int index = binarySearch(dp, 0, maxLen, num);

            /**
             * if the element is not in the bounded array dp[0..len), then
             * we get the index of the insert point using a conversion.
             */
        if(index < 0) index = -(index + 1);
        dp[index] = num;

        /**
        * if the updated element is the last element, then the length of
        * the LIS may be updated, it is always equal to idx+1. Also, here we are checking index vs length, hence index == length.
        */
        if(index == maxLen) maxLen++;
    }
    return maxLen;
}

public int binarySearch(int[] arr, int start, int end, int key) {
    while(start != end) {
        int mid = start + (end-start)/2;
        if(arr[mid] < key) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
   return start;
}

// Approach 2: Bottom up DP
// O(n2) time complexity
// O(n) Space complexity
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int maxLen = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int end = 1; end < nums.length; end++) {
            int maxLengthSoFar = 0;
            for(int start = 0; start < end; start++) {
                if(nums[end] > nums[start]) {
                    maxLengthSoFar = Math.max(maxLengthSoFar, dp[start]); 
                }     
            }
            dp[end] = maxLengthSoFar + 1;
            maxLen = Math.max(maxLen, dp[end]);
        }
        return maxLen;
    }
}


// Approach 3: Recursion with Memoization
// Algorithm
// In the previous approach, many recursive calls had to made again and again with the same parameters. This redundancy can be eliminated by storing the results obtained for a particular call in a 2-d memoization array memomemo. memo[i][j]  represents the length of the LIS possible using nums[i] as the previous element considered to be included/not included in the LIS, with nums[j] as the current element considered to be included/not included in the LIS. Here, numsnums represents the given array.
// Time: O(n2) Space: O(n2)

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length + 1];
        for(int[] arr: memo) {
            Arrays.fill(arr, - 1);
        }
        
        return recurseLIS(nums, -1, 0, memo);
    }

    public int recurseLIS(int[] nums, int prevPos, int currPos, int[][] memo) {
        if(currPos == nums.length) return 0;
        
        if(memo[prevPos + 1][currPos] > 0) {
            return memo[prevPos + 1][currPos];
        }
        int currPosLen = 0, prevPosLen = 0;
        if(prevPos < 0 || nums[currPos] > nums[prevPos]) { 
            currPosLen = 1 + recurseLIS(nums, currPos, currPos + 1, memo);
        }
        prevPosLen = recurseLIS(nums, prevPos, currPos + 1, memo);
        memo[prevPos + 1][currPos] = Math.max(currPosLen, prevPosLen);
        return memo[prevPos + 1][currPos];
    }
}



// Approach 4: Brute Force
// The simplest approach is to try to find all increasing subsequences and then returning the maximum length of longest increasing subsequence. In order to do this, we make use of a recursive function \text{lengthofLIS}lengthofLIS which returns the length of the LIS possible from the current element(corresponding to curposcurpos) onwards(including the current element). Inside each function call, we consider two cases:
// currPosLen: The current element is larger than the previous element included in the LIS. In this case, we can include the current element in the LIS. Thus, we find out the length of the LIS obtained by including it. Further, we also find out the length of LIS possible by not including the current element in the LIS. The value returned by the current function call is, thus, the maximum out of the two lengths.
// prevPosLen: The current element is smaller than the previous element included in the LIS. In this case, we can't include the current element in the LIS. Thus, we find out only the length of the LIS possible by not including the current element in the LIS, which is returned by the current function call.

// Time: O(2n),  space: O(n2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        return recurseLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int recurseLIS(int[] nums, int prevLen, int currPos) {
        if(currPos == nums.length) return 0;
        int currPosLen = 0, prevPosLen = 0;
        if(nums[currPos] > prevLen) { 
            currPosLen = 1 + recurseLIS(nums, nums[currPos], currPos + 1);
        }
        prevPosLen = recurseLIS(nums, prevLen, currPos + 1);
        return Math.max(currPosLen, prevPosLen);
    }
}