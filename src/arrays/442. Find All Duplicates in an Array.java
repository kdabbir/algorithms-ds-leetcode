// 442. Find All Duplicates in an Array
// Medium

// Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

// Find all the elements that appear twice in this array.

// Could you do it without extra space and in O(n) runtime?

// Example:
// Input:
// [4,3,2,7,8,2,3,1]

// Output:
// [2,3]


// Single pass O(N) solution
// NO EXTRA memory

// The concept here is to negate each number's index as the input is all positive integers i.e 1 <= a[i] <= n (n = size of array). Once a value is negated, if it requires to be negated again then it is a duplicate.
// Here we are using Math.abs since we may come across an negated value in an index and nums[negativeValue] will throw an exception.
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int num : nums) {
            if(nums[Math.abs(num) - 1] < 0) {
                res.add(Math.abs(num));
            }
            nums[Math.abs(num) - 1] *= -1;
        }
        return res;
    }
}

//Using Hashset

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for(int num: nums) {
            if(seen.contains(num)) {
                res.add(num);
            } else {
                seen.add(num);
            }
        }
        return res;
    }
}
