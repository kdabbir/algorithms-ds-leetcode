// 654. Maximum Binary Tree: https://leetcode.com/problems/maximum-binary-tree/

// Medium

// You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

// Create a root node whose value is the maximum value in nums.
// Recursively build the left subtree on the subarray prefix to the left of the maximum value.
// Recursively build the right subtree on the subarray suffix to the right of the maximum value.
// Return the maximum binary tree built from nums.
// Example 1:


// Input: nums = [3,2,1,6,0,5]
// Output: [6,3,5,null,2,0,null,null,1]
// Explanation: The recursive calls are as follow:
// - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
//     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
//         - Empty array, so no child.
//         - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
//             - Empty array, so no child.
//             - Only one element, so child is a node with value 1.
//     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
//         - Only one element, so child is a node with value 0.
//         - Empty array, so no child.
// Example 2:


// Input: nums = [3,2,1]
// Output: [3,null,2,null,1]


// Constraints:

// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 1000
// All integers in nums are unique.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// Recursively build left and right trees.

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructTreeRecursive(nums, 0, nums.length - 1);
    }

    public TreeNode constructTreeRecursive(int[] nums, int left, int right) {
        if(left > right) return null;
        int maxIndex = getMaxIndex(nums, left, right);
        TreeNode currNode = new TreeNode(nums[maxIndex]);
        currNode.left = constructTreeRecursive(nums, left, maxIndex - 1);
        currNode.right = constructTreeRecursive(nums, maxIndex + 1, right);
        return currNode;
    }

    public int getMaxIndex(int[] nums, int left, int right) {
        int maxIndex = left;
        for(int currIdx = left; currIdx <= right; currIdx++) {
            if(nums[currIdx] > nums[maxIndex]) {
                maxIndex = currIdx;
            }
        }
        return maxIndex;
    }
}

// Time: O(N ^ 2)
// Space: O(N)


// Iterative solution.

public class SubTreeObj {
    TreeNode node;
    int left;
    int right;
    SubTreeObj(TreeNode node, int left, int right) {
        this.node = node;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<SubTreeObj> stack = new Stack<>();
        TreeNode root = new TreeNode(0);
        SubTreeObj obj = new SubTreeObj(root, 0, nums.length - 1);
        stack.push(obj);
        while(!stack.isEmpty()) {
            SubTreeObj curr = stack.pop();
            TreeNode node = curr.node;
            int left = curr.left, right = curr.right;
            int maxIdx = getMaxIndex(nums, left, right);
            node.val = nums[maxIdx];

            if(left <= maxIdx - 1) {
              node.left = new TreeNode(0);
              SubTreeObj leftObj = new SubTreeObj(node.left, left, maxIdx - 1);
              stack.push(leftObj);
            }
            if(right >= maxIdx + 1) {
              node.right = new TreeNode(0);
              SubTreeObj rightObj = new SubTreeObj(node.right , maxIdx + 1, right);
              stack.push(rightObj);
            }

        }

        return root;
    }

    public int getMaxIndex(int[] nums, int left, int right) {
        int maxIndex = left;
        for(int currIdx = left; currIdx <= right; currIdx++) {
            if(nums[currIdx] > nums[maxIndex]) {
                maxIndex = currIdx;
            }
        }
        return maxIndex;
    }
}
// Time: O(N ^ 2)
// Space: O(N)
