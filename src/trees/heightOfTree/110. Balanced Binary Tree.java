// 110. Balanced Binary Tree : https://leetcode.com/problems/balanced-binary-tree/
// Easy
// Given a binary tree, determine if it is height-balanced.

// For this problem, a height-balanced binary tree is defined as:

// a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 

// Example 1:


// Input: root = [3,9,20,null,null,15,7]
// Output: true
// Example 2:


// Input: root = [1,2,2,3,3,null,null,4,4]
// Output: false
// Example 3:

// Input: root = []
// Output: true
 

// Constraints:

// The number of nodes in the tree is in the range [0, 5000].
// -104 <= Node.val <= 104

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
    public boolean isBalanced(TreeNode root) {
      return isHeightBalanced(root) != -1;
    }
	
	public int isHeightBalanced(TreeNode tree){
		if(tree == null) {
			return 0;
		}
		int leftCount = isHeightBalanced(tree.left);
		int rightCount = isHeightBalanced(tree.right);
		if(leftCount == -1 || rightCount == -1 || Math.abs(leftCount - rightCount) > 1) return -1;
		return 1 + Math.max(leftCount, rightCount);
	}
}

// Time complexity : O(nlogn) the height h of a balanced tree is bounded by O(logn)
// Space complexity: O(n)
// Other versionn

class Solution {
    public boolean isBalanced(TreeNode root) {
	    // corner case
        if(root == null) return true;
        
        int[] res = getHeight(root);
        return res[1] == 1;
    }
    
    // return [height, balanced]
    public int[] getHeight(TreeNode root){
        // base case
        if(root == null) return new int[]{0, 1};
        
        int[] cur = new int[2];
        
        int[] left = getHeight(root.left);
        if(left[1] == -1){
            cur[1] = -1; // unbalanced, do not care about height anymore
            return cur;
        }
        
        int[] right = getHeight(root.right);
        if(right[1] == -1){
            cur[1] = -1; // unbalanced, do not care about height anymore
            return cur;
        }
        
        if(Math.abs(left[0] - right[0]) > 1){
            cur[1] = -1; // unbalanced, do not care about height anymore
            return cur;
        }
        
		// set [height, balanced]
        cur[0] = Math.max(left[0], right[0]) + 1; // set height
        cur[1] = 1; // set balanced
        return cur;
    }
    
}