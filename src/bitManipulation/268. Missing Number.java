// 268. Missing Number
// Easy

// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

// Example 1:

// Input: [3,0,1]
// Output: 2
// Example 2:

// Input: [9,6,4,2,3,5,7,0,1]
// Output: 8
// Note:
// Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


// XOR Solution:

// formula: the length of arr XOR individual array index XOR value of the index will give the missing value.

// XOR Rules:
// Distinct elements xor will give 1. i.e 0 ^ 1 or 1 ^ 0
// Same elements result in 0
// 0 ^ x will give x value.
// Based on above rules, x will be left out after XOR.

// EG:
// =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
// =(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
// =0∧0∧0∧0∧2
// =2

class Solution {
    public int missingNumber(int[] nums) {
        int missingVal = nums.length;
        for(int i = 0; i < nums.length; i++) {
            missingVal ^= i ^ nums[i]; 
        }
        return missingVal;
    }
}
// Time complexity : O(n)

// Assuming that XOR is a constant-time operation, this algorithm does constant work on nn iterations, so the runtime is overall linear.

// Space complexity : O(1)


// Gauss' Formula:

// We can compute the sum of nums in linear time, and by Gauss' formula, we can compute the sum of the first nn natural numbers in constant time. Therefore, the number that is missing is simply the result of Gauss' formula minus the sum of nums, as nums consists of the first n natural numbers minus some number.

class Solution {
    public int missingNumber(int[] nums) {
        int actualSum = (nums.length * (nums.length + 1)) / 2;
        int currSum = 0;
        for(int num: nums) { 
            currSum += num;
        }
        return actualSum - currSum;
    }
}

// Time complexity : O(n)

// Although Gauss' formula can be computed in O(1) time, summing nums costs us O(n) time, so the algorithm is overall linear. Because we have no information about which number is missing, an adversary could always design an input for which any algorithm that examines fewer than nn numbers fails. Therefore, this solution is asymptotically optimal.

// Space complexity : O(1)

// This approach only pushes a few integers around, so it has constant memory usage.

// Hashset

// This algorithm is almost identical to the brute force approach, except we first insert each element of nums into a set, allowing us to later query for containment in \mathcal{O}(1)O(1) time.

class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
}

// Sorting

// First, we sort nums. Then, we check the two special cases that can be handled in constant time - ensuring that 0 is at the beginning and that nn is at the end. Given that those assumptions hold, the missing number must somewhere between (but not including) 0 and nn. To find it, we ensure that the number we expect to be at each index is indeed there. Because we handled the edge cases, this is simply the previous number plus 1. Thus, as soon as we find an unexpected number, we can simply return the expected number.

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        // Ensure that n is at the last index
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        // Ensure that 0 is at the first index
        else if (nums[0] != 0) {
            return 0;
        }

        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i-1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // Array was not missing any numbers
        return -1;
    }
}

// Time complexity : O(nlgn)

// The only elements of the algorithm that have asymptotically nonconstant time complexity are the main for loop (which runs in \mathcal{O}(n)O(n) time), and the sort invocation (which runs in \mathcal{O}(nlgn)O(nlgn) time for Python and Java). Therefore, the runtime is dominated by sort, and the entire runtime is \mathcal{O}(nlgn)O(nlgn).

// Space complexity : O(1) or O(n)