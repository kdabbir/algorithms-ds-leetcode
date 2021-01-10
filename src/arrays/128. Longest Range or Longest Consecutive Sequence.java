// 128. Longest Range or Longest Consecutive Sequence: https://leetcode.com/problems/longest-consecutive-sequence/

// Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

// Follow up: Could you implement the O(n) solution? 
// Example 1:

// Input: nums = [100,4,200,1,3,2]
// Output: 4
// Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
// Example 2:

// Input: nums = [0,3,7,2,5,8,4,6,0,1]
// Output: 9
 

// Constraints:

// 0 <= nums.length <= 104
// -109 <= nums[i] <= 109


// Best Approach: HashSet and Intelligent Sequence Building

// Intuition

// It turns out that our initial brute force solution was on the right track, but missing a few optimizations necessary to reach  O(n) time complexity.

// Algorithm

// This optimized algorithm contains only two changes from the brute force approach: the numbers are stored in a HashSet (or Set, in Python) to allow O(1) lookups, and we only attempt to build sequences from numbers that are not already part of a longer sequence. This is accomplished by first ensuring that the number that would immediately precede the current number in a sequence is not present, as that number would necessarily be part of a longer sequence.

class Solution {
    public int longestConsecutive(int[] nums) {
        int longestLength = 0;
		if(nums == null || nums.length == 0) return longestLength;
		
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for(int num: nums) {
			map.put(num, true);
		}
		
		for(int num: nums) {
			if(!map.get(num)) 
				continue;
			map.put(num, false);
			int left = num - 1;
			int right = num + 1;
			int currLen = 1;
			
			while(map.containsKey(left)) {
				currLen++;
				left--;
			}
			while(map.containsKey(right)){
				currLen++;
				right++;
			}
			if(currLen > longestLength) {
				longestLength = currLen;
			}
		}
		
    return longestLength;
    }
}

// Time: O(N)
// Space: O(1)

// Approach 2: Sorting

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}

// Time: O(NLogN)