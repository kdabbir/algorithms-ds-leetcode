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

// Using binary search

class Solution {
    // Return tree depth in O(d) time.
    public int computeTillLastLevelDepth(TreeNode node) {
      int currDepth = 0;
      while (node != null && node.left != null) { // Here if we just use node != null, it will check entire node depth
        node = node.left;
        currDepth++;
      }
      return currDepth;
    }

        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Return True if last level node idx exists.
        // Binary search with O(d) complexity.
      public boolean elementFound(int idx, int depth, TreeNode node) {
          int start = 0, end = (int) Math.pow(2, depth) - 1;
          for(int currDepth = 0;currDepth < depth; currDepth++) {
              int mid = start + (end - start)/2;
              if(idx <= mid) {
                  end = mid;
                  node = node.left;
              } else {
                  start = mid + 1;
                  node = node.right;
              }
          }
          return node != null;
      }

    public int countNodes(TreeNode root) {
      // if the tree is empty
      if (root == null) return 0;

      int tillLastLevelDepth = computeTillLastLevelDepth(root); // Compute depth for N-1 levels
      // if the tree contains 1 node
      if (tillLastLevelDepth == 0) return 1;

        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Perform binary search to check how many nodes exist.
      int start = 1, end = (int) Math.pow(2, tillLastLevelDepth) - 1;
      while(start <= end) {
          int mid = start + (end - start)/2;
          if(elementFound(mid, tillLastLevelDepth, root)) {
              start = mid + 1;
          } else {
              end = mid - 1;
          }
      }
        // The tree contains 2**d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
      return (int) Math.pow(2, tillLastLevelDepth) - 1  + start;
    }
  }

  // Time: O(LogN * LogN)
  // Space: O(1)