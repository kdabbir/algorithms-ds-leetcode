// 226. Invert Binary Tree: https://leetcode.com/problems/invert-binary-tree/
// Easy

// Invert a binary tree.

// Example:

// Input:

//      4
//    /   \
//   2     7
//  / \   / \
// 1   3 6   9
// Output:

//      4
//    /   \
//   7     2
//  / \   / \
// 9   6 3   1

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

 // DFS
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
           return null;
        swapLeftRightNodes(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    
    public void swapLeftRightNodes(TreeNode node) {
        TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
    }
    
}

//BFS


class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
           return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){ 
            TreeNode curr = queue.poll();
            swapLeftRightNodes(curr);
            if(curr.left != null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);
        }       
        return root;
    }
    
    public void swapLeftRightNodes(TreeNode node) {
        TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
    }
    
}