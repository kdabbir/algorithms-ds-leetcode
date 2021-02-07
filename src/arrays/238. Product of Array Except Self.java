// 238. Product of Array Except Self: https://leetcode.com/problems/product-of-array-except-self/
// Medium

// Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

// Example:

// Input:  [1,2,3,4]
// Output: [24,12,8,6]
// Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

// Note: Please solve it without division and in O(n).

// Follow up:
// Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)


class Solution {
    public int[] productExceptSelf(int[] array) {
        int[] products = new int[array.length];

		// LeftRunningProd is a value containing the left products
        // i.e: left[i] = nums[0] * .... * nums[i-1]  * nums[i]
		int leftRunningProd = 1;
		for(int idx = 0; idx < array.length;idx++) {
			products[idx] = leftRunningProd;
			leftRunningProd *= array[idx];
        }

        // RightRunningprod is a value containing the array products from the right
        //i.e: right[i] = nums[i] * nums[i+1]  * .... * nums[len(nums)]
		int rightRunningProd = 1;
		
		for(int idx = array.length - 1; idx >= 0; idx--) {
			products[idx] *= rightRunningProd;
			rightRunningProd *= array[idx];
		}
		return products;
    }
}

// Approach2:

// For each item in the nums array, we manage to get the product of everything in nums but that item itself. How would we explain this a bit more mathematically? Let's try to define how we calculate results[i]:

// For all i in the middle of nums (i.e., not at either end):
//     results[i] = nums[0] * ...... * nums[i-1] * nums[i + 1] * ...... * nums[nums.length - 1]

// For i = 0:
//     results[i] = nums[i + 1] * ...... * nums[nums.length - 1]
		
// For i = nums.length - 1:
//     results[i] = nums[0] * ...... * nums[i-1]
// Hopefully this spells it out a bit better. Essentially, to get results[i], for any i, we calculate the product everything to the left and to the right of nums[i]. Keep this in mind for later!

// Let's say we have an array of integers: [1, 2, 3, 4, 5, 6, 7, 8]. Let's calculate the product of all items except for the 4 (index = 3)

// 1 * 2 * 3 * 5 * 6 * 7 * 8
// is how we would do that. This product is the product of everything to the left and to the right of 4. This is equivalent to doing:

// (1 * 2 * 3) * (5 * 6 * 7 * 8)
// This is the product of the left product (the product of everything on the left) and the right product (the product of everything on the right)
// Now, for any i-th item in nums we should be able to calculate the product of everything but itself, by multiplying its left and right product!

// Finding the Left Products (and Right Products) can be done in O(n)!
// Let's try to calculate a left product array, such that for left[i] = the product of everything to the left of nums[i], using this example ([1,2,3,4]).

// left[0] = 1 (There is nothing to the left of nums[0], so we set it to 1)
// left[1] = 1 (1 is to the left of nums[0], so we set it to 1)
// left[2] = 1 * 2
// left[3] = 1 * 2 * 3

// Look for the pattern in those products (There's a pattern here!)
// left[1] = 1 = left[0] * 1 = left[0] * nums[0]
// left[2] = 1 * 2 = left[1] * 2 = left[1] * nums[1]
// left[3] = 1 * 2 * 3 = left[2] * 3 = left[2] * nums[2]

// The pattern: left[i] = left[i-1] * nums[i-1] !!!

// We can show a similar situation for the right product array-> right[i] = right[i+1] * nums[i+1]

// right[3] = 1 (There is nothing to the left of nums[3], so we set it to 1)
// right[2] = 4 (4 is to the right of nums[2])
// right[1] = 4 * 3
// right[0] = 4 * 3 * 2

// Look for the pattern in those products (There's a pattern here!)
// right[2] = 4 = right[3] * 4 = right[3] * nums[3]
// right[1] = 4 * 3 = right[2] * 3 = right[2] * nums[2]
// right[0] = 4 * 3 * 2 = right[1] * 2 = right[1] * nums[1]

// Solution
// Now that we know how to calculate the left product array and right product array, we can simply say that results[i] = left[i] * right[i]!!


class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Left is an array containing the left products
        // i.e: left[i] = nums[0] * .... * nums[i-1]  * nums[i]
        int[] left = new int[nums.length];
        
        // Right is an array containing the array products
        //i.e: right[i] = nums[i] * nums[i+1]  * .... * nums[len(nums)]
        int[] right = new int[nums.length];
        
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        
        int[] product = new int[nums.length];
        for (int i = 0; i < product.length; i++) {
            product[i] = left[i] * right[i];
        }
        
        return product;
    }
}