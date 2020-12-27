// 270. Closest Binary Search Tree Value: https://leetcode.com/problems/closest-binary-search-tree-value/
// Easy
// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

// Note:

// Given target value is a floating point.
// You are guaranteed to have only one unique value in the BST that is closest to the target.
// Example:

// Input: root = [4,2,5,1,3], target = 3.714286

//     4
//    / \
//   2   5
//  / \
// 1   3

// Output: 4


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
    public int closestValue(TreeNode root, double target) {
        if(root.val == 0 && root.left == null && root.right == null) return 0;
        return closestValueDFS(root, target, Double.MAX_VALUE);
    }
    
    public int closestValueDFS(TreeNode currNode, double target, double closest) {
        // Case where absolute value of current element is lesser than last closest.
        if(Math.abs(target - closest) > Math.abs(currNode.val - target)) {
            closest = currNode.val;
        }
        // If target is lesser than current node val.
        
        if(currNode.left != null && target < currNode.val) {
            return closestValueDFS(currNode.left, target, closest);
        } 
        // If target is greater than current node val.
        
        else if(currNode.right != null && target > currNode.val) {
            return closestValueDFS(currNode.right, target, closest);
        } else {
            return (int) closest;
        }  
    }
}