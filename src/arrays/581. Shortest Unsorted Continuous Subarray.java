// 581. Shortest Unsorted Continuous Subarray : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

// Medium

// Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

// Return the shortest such subarray and output its length.
// Example 1:

// Input: nums = [2,6,4,8,10,9,15]
// Output: 5
// Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
// Example 2:

// Input: nums = [1,2,3,4]
// Output: 0
// Example 3:

// Input: nums = [1]
// Output: 0
 

// Constraints:

// 1 <= nums.length <= 104
// -105 <= nums[i] <= 105


// Find start and end where condition of sorted arrays is violated.
// Find min and max between this start,end, expand start, end
// end - start + 1 will give length of unsorted array



class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int start, end;
        
        for(start = 0; start < nums.length - 1; start++) {
            if(nums[start + 1] < nums[start])
                break;
        }
        if(start == nums.length - 1) return 0;
        
        for(end = nums.length - 1 ; end > 0; end--) {
            if(nums[end - 1] > nums[end])
                break;
        }
        
        while(start < nums.length -1 && nums[start + 1] > nums[start]){
            start++;
        }
        if(start == nums.length - 1) return 0;
        
        while(end >= 1 && nums[end] > nums[end - 1]) {
            end--;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int idx = start; idx <= end; idx++) {
            min = Math.min(min, nums[idx]);
            max = Math.max(max, nums[idx]);
        }
        
        while(start >= 1 && nums[start - 1] > min ) {
            start--;
        }
        
        while(end < nums.length - 1 && nums[end + 1] < max ) {
            end++;
        }
        return end - start + 1;
        
    }
}

// Explanation:

// To develop intuition for this problem, think of your array as a line chart. For e.g, In the chart above, there's only two numbers out of order - 4 and 3. This is the smallest possiblesubarray that when sorted, leads to the entire array being sorted.

//How do we spot such a subarray? A good starting point is to look for a dip in value. If yougo through the sorted array one by one, the first dip indicates that the array is unsorted startingfrom this point.

// Similarly, from the end, we can find a bump. When we go from end to start, if we find a bump,that indicates that the array is unsorted from there.

//So, we can make an initial estimate of the unsorted portion, by traversing from both ends and finding a subarray between the first dip and bump. This subarray can be the answer for some inputs, forexample

// Above, we found the dip and bump, and the subarray between them is good enough to sort the entirearray. The result is the subarray [6,4,7,4].However, it will not work in certain cases.

// For example, look at the following:Just finding the dip and bump doesn't work above. Just by finding the dip and bump, our subarray is[5,3,7,5]. However, look at the numbers ​4​ and ​6​ - they need to be in the result as well.Look at the green lines along 3 and 7. Everything between those lines should be in the result. Onlythen will the array be sorted.


// So 4 should be included in our result - so that 3 can appear on it’s left after sorting the subarray.Similarly, 7 is greater than 6, so 6 should be included - so that 7 can appear on it’s right after sorting.Now the question becomes: How do we add the leftover numbers that are between the greenlines?Here’s how we fix this - we need to expand the subarray so that it covers all the numbers less than theminimum. The minimum of our subarray [5,3,7,5] is 3. So, we know that we should expand left if weencounter numbers greater than 3. In this case, 4.Similarly, the maximum of our subarray [5,3,7,5] is 7. So, we should expand right while we encounternumbers less than 7. In this case, 6.This will bring all the numbers within the green lines into our result.Here is the overall algorithm that will do the job:Start from 0. Find the first dip in value, call that ​start​.For example: [1,3,5,2,6,4,7,8,9] --> First dip starts at index 2.Next, start from the end of the array. Find the first bump in value, call that ​end​.For example: [1,3,5,2,6,4,7,8,9] --> First bump starts at index 5.We now know that the subarray [​start​..​end​] is causing the array to be unsorted. But simply sortingthis subarray will not sort the array. After all, sorting [5,2,6,4] will not do the trick, because there is a 3before it. We need to expand this subarray. Let's find the min of subarray [​start​..​end​].In this case, the ​min​ is 2. In order for the entire array to be sorted, we will have to expandsubarray [​start​..​end​] to include all numbers greater than 2. So we expand from {5,2,6,4} to {​3​,5,2,6,4}.We do the same for ​max​. In this case, we don't need to expand ​max​, so that is the answer.

//https://interviewcamp.io/courses/101687/lectures/2629119