// 95. Unique Binary Search Trees II: https://leetcode.com/problems/unique-binary-search-trees-ii/
// Medium

// Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
// Example 1:

// Input: n = 3
// Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// Example 2:

// Input: n = 1
// Output: [[1]]

// Constraints:

// 1 <= n <= 8
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
    public List<TreeNode> generateTrees(int n) {
        return generateTreeFromNode (1, n);  
    }
    
    public List<TreeNode> generateTreeFromNode(int start, int end) {
        List<TreeNode> output = new ArrayList<>();
        if(start > end) {
            output.add(null);
            return output;
        }
        
        for(int rootIdx = start; rootIdx <= end; rootIdx++) {
            List<TreeNode> leftNodes = generateTreeFromNode(start, rootIdx - 1);
            List<TreeNode> rightNodes = generateTreeFromNode(rootIdx + 1, end);
            for(TreeNode left: leftNodes) {
                for(TreeNode right: rightNodes) {
                    TreeNode currNode = new TreeNode(rootIdx);
                    currNode.left = left;
                    currNode.right = right;
                    output.add(currNode);
                }
            }
        }
        return output;
    }
}