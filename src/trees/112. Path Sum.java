// 112. Path Sum: https://leetcode.com/problems/path-sum/
// Easy
// Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

// Note: A leaf is a node with no children.

// Example:

// Given the below binary tree and sum = 22,

//       5
//      / \
//     4   8
//    /   / \
//   11  13  4
//  /  \      \
// 7    2      1
// return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

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
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return pathSumDFS(root, sum, 0);
    }

    public boolean pathSumDFS(TreeNode curr, int target, int runSum) {
        if(curr == null) {
            return false;
        }

        runSum += curr.val;

        if(curr.left == null && curr.right == null) {
            return target == runSum;
        }

        boolean left = pathSumDFS(curr.left, target, runSum);
        boolean right = pathSumDFS(curr.right, target, runSum);

        return left || right;
    }
}

// Time: O(H)