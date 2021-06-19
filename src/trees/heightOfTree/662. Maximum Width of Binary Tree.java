// 662. Maximum Width of Binary Tree: https://leetcode.com/problems/maximum-width-of-binary-tree/

// Medium

// Given the root of a binary tree, return the maximum width of the given tree.

// The maximum width of a tree is the maximum width among all levels.

// The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.

// It is guaranteed that the answer will in the range of 32-bit signed integer.



// Example 1:


// Input: root = [1,3,2,5,3,null,9]
// Output: 4
// Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
// Example 2:


// Input: root = [1,3,null,5,3]
// Output: 2
// Explanation: The maximum width existing in the third level with the length 2 (5,3).
// Example 3:


// Input: root = [1,3,2,5]
// Output: 2
// Explanation: The maximum width existing in the second level with the length 2 (3,2).
// Example 4:


// Input: root = [1,3,2,5,null,null,9,6,null,null,7]
// Output: 8
// Explanation: The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


// Constraints:

// The number of nodes in the tree is in the range [1, 3000].
// -100 <= Node.val <= 100

// DFS. Calculate first index for each level and using current Index, we can calculate difference as width of tree.

class Solution {
    private int maxWidth = 0;
    private Map<Integer, Integer> firstColMap;
    public int widthOfBinaryTree(TreeNode root) {
        firstColMap = new HashMap<>();
        calculateMaxWidth(root, 0, 0);
        return this.maxWidth;
    }

    public void calculateMaxWidth(TreeNode node, int depth, int currentColumnIdx) {
        if(node == null) return;

        if(!this.firstColMap.containsKey(depth)) {
            this.firstColMap.put(depth, currentColumnIdx);
        }

        int firstColIdx = this.firstColMap.get(depth);

        this.maxWidth = Math.max(this.maxWidth, currentColumnIdx - firstColIdx + 1);

        calculateMaxWidth(node.left, depth + 1, 2 * currentColumnIdx);

        calculateMaxWidth(node.right, depth + 1, 2 * currentColumnIdx + 1);
    }

}