// 222. Count Complete Tree Nodes: https://leetcode.com/problems/count-complete-tree-nodes/
// Medium

// Given the root of a complete binary tree, return the number of the nodes in the tree.

// According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

// Design an algorithm that runs in less than O(n) time complexity.



// Example 1:


// Input: root = [1,2,3,4,5,6]
// Output: 6
// Example 2:

// Input: root = []
// Output: 0
// Example 3:

// Input: root = [1]
// Output: 1


// Constraints:

// The number of nodes in the tree is in the range [0, 5 * 104].
// 0 <= Node.val <= 5 * 104
// The tree is guaranteed to be complete.

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

// Using DFS and completeness property

class Solution {
    public int countNodes(TreeNode root) {
        int leftDepth = calculatLeftDepth(root);
        int rightDepth = calculatRightDepth(root);

        if(leftDepth == rightDepth){
            return  (int) Math.pow(2, leftDepth) - 1; // Equivalent to (1 << leftDepth) - 1
            // If you know depth, calculating number of nodes in case of complete binary tree is depth ^ 2 - 1
         }
        else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public int calculatLeftDepth(TreeNode node) {
        int depth = 0;
        while(node != null) {
            node = node.left;
            depth++;
        }
        return depth;
    }
    public int calculatRightDepth(TreeNode node) {
        int depth = 0;
        while(node != null) {
            node = node.right;
            depth++;
        }
        return depth;
    }
}

// Time: (LogN * LogN)
// Space: O(1)
