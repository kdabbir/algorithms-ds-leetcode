// 287. Find the Duplicate Number
// Medium

// Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

// There is only one duplicate number in nums, return this duplicate number.

// Follow-ups:

// How can we prove that at least one duplicate number must exist in nums? 
// Can you solve the problem without modifying the array nums?
// Can you solve the problem using only constant, O(1) extra space?
// Can you solve the problem with runtime complexity less than O(n2)?
 

// Example 1:

// Input: nums = [1,3,4,2,2]
// Output: 2
// Example 2:

// Input: nums = [3,1,3,4,2]
// Output: 3
// Example 3:

// Input: nums = [1,1]
// Output: 1
// Example 4:

// Input: nums = [1,1,2]
// Output: 1
 

// Constraints:

// 2 <= n <= 3 * 104
// nums.length == n + 1
// 1 <= nums[i] <= n
// All the integers in nums appear only once except for precisely one integer which appears two or more times.

// Approach 1: Using fast and slow pointer
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        
        while(slow != fast) {
            if(slow == nums[slow]) return slow;
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        slow = 0;
        while(slow != fast) {
            if(slow == nums[slow]) return slow;
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

// Time complexity : O(n)
// Space complexity: O(1)

// Approach 2: Set

class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
}

// Time complexity : O(n)
// Space complexity: O(n)

// Approach 3: Sorting

class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }
}


// Time complexity : O(n)
// Space complexity: O(n)