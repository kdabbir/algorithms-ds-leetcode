// 236. Lowest Common Ancestor of a Binary Tree : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

// Medium

// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

// According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 

// Example 1:


// Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
// Output: 3
// Explanation: The LCA of nodes 5 and 1 is 3.
// Example 2:


// Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
// Output: 5
// Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
// Example 3:

// Input: root = [1,2], p = 1, q = 2
// Output: 1
 

// Constraints:

// The number of nodes in the tree is in the range [2, 105].
// -109 <= Node.val <= 109
// All Node.val are unique.
// p != q
// p and q will exist in the tree.

// Recursion.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // root == null if we reach leaf node, then we return null
        // root == p or q if we reach one target node. we return that node.
        // Below we are comparing if both left and right is not null, then its the LCA. 
        // If one of it is null, return the other node.

        if(root == null || root == p || root == q) 
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}

// lowestCommonAncestor

// Share my understanding of what lowestCommonAncestor() does:

// if both p and q exist in Tree rooted at root, then return their LCA
// if neither p and q exist in Tree rooted at root, then return null
// if only one of p or q (NOT both of them), exists in Tree rooted at root, return it

// Iterative approach DFS:

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentsMap = new HashMap<TreeNode, TreeNode>();
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        parentsMap.put(root, null);
        queue.push(root);
        
        while(!parentsMap.containsKey(p) || !parentsMap.containsKey(q)) {
            TreeNode curr = queue.pollFirst();
            if(curr.left != null) {
                parentsMap.put(curr.left, curr);
                queue.push(curr.left);
            }
            
            if(curr.right != null) {
                parentsMap.put(curr.right, curr);
                queue.push(curr.right);
            }
        }
        
        Set<TreeNode> ancestors = new HashSet<TreeNode>();
        while(p != null) {
            ancestors.add(p);
             p = parentsMap.get(p);
        }
        while(!ancestors.contains(q)) {
            q = parentsMap.get(q);
        }
        return q;
        
    }
}

// Time: O(N)
// Space: O(N)
// Intuition

// If we have parent pointers for each node we can traverse back from p and q to get their ancestors. The first common node we get during this traversal would be the LCA node. We can save the parent pointers in a dictionary as we traverse the tree.

// Algorithm

// Start from the root node and traverse the tree.
// Until we find p and q both, keep storing the parent pointers in a dictionary.
// Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
// Similarly, we traverse through ancestors for node q. If the ancestor is present in the ancestors set for p, this means this is the first ancestor common between p and q (while traversing upwards) and hence this is the LCA node.
