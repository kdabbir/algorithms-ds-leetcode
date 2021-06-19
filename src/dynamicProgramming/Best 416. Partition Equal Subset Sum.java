// 416. Partition Equal Subset Sum: https://leetcode.com/problems/partition-equal-subset-sum/solution/

// Medium

// Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

// Example 1:

// Input: nums = [1,5,11,5]
// Output: true
// Explanation: The array can be partitioned as [1, 5, 5] and [11].
// Example 2:

// Input: nums = [1,2,3,5]
// Output: false
// Explanation: The array cannot be partitioned into equal sum subsets.

// Constraints:

// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100

// Bottom up dynamic programming. Refer doc for explanation with nice explanation for converting recursion with memo code to dp.

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num: nums) {
            totalSum += num;
        }
        if(totalSum %2!= 0) return false;

        int subsetSum = totalSum/2;
        boolean[][] canPartitionDp = new boolean[nums.length + 1][subsetSum + 1];
        canPartitionDp[0][0] = true;

        for(int idx = 1; idx <= nums.length; idx++) {
            int currVal = nums[idx - 1];
            for(int currSum = 0; currSum <= subsetSum; currSum++) {
                if(currSum < currVal) {
                    canPartitionDp[idx][currSum] = canPartitionDp[idx - 1][currSum];
                } else {
                    canPartitionDp[idx][currSum] = canPartitionDp[idx - 1][currSum] || canPartitionDp[idx - 1][currSum - currVal];
                }
            }
        }
        return canPartitionDp[nums.length][subsetSum];
    }
}

// Recursion with Memoization

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num: nums) {
            totalSum += num;
        }
        if(totalSum %2!= 0) return false;

        return partitionDFSHelper(totalSum/2, nums.length - 1, new Boolean[nums.length][totalSum/2 + 1], nums);
    }

    public boolean partitionDFSHelper(int currSum, int currIdx, Boolean[][] memo, int[] nums) {
        if(currSum == 0) return true; // We are able to find halfSum with a combination, hence returning true.
        if(currSum < 0 || currIdx == 0) return false; // If we reach first index.
        if(memo[currIdx][currSum] != null) return memo[currIdx][currSum];

        boolean result = partitionDFSHelper(currSum - nums[currIdx], currIdx - 1, memo, nums) || partitionDFSHelper(currSum , currIdx - 1, memo, nums);  // Simulating taking currIdx sum into consideration or not.
         // store the result in memo
        memo[currIdx][currSum] = result;

        return result;
    }
}

// Time: O(M * N) M- subsetSum and N- number of elements
// Space: O(M * N)


