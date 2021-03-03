// 543. Diameter of Binary Tree : https://leetcode.com/problems/diameter-of-binary-tree/
// Easy

// Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

// Example:
// Given a binary tree
//           1
//          / \
//         2   3
//        / \     
//       4   5    
// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

// Note: The length of path between two nodes is represented by the number of edges between them.

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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        max[0] = 1;
        maxDepth(root, max);
        return max[0] - 1;
    }
    
    public int maxDepth(TreeNode node, int[] max) {
        if(node == null)
            return 0;
        int leftDepth = maxDepth(node.left, max);
        int rightDepth = maxDepth(node.right, max);
        max[0] = Math.max(max[0], leftDepth + rightDepth + 1);
        
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

// Time: O(N), space: O(N)
// explaination for +1, -1 in above question like this:
// just simply change name of "max" to "number_of_nodes", then read the code, you will understand it easily why the code is doing this.
// It simply count the number of nodes and return diameter, which is number of nodes -1. So everything reads simplier after this name exchange.
