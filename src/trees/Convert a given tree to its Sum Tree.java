// Convert a given tree to its Sum Tree : https://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/

// Given a Binary Tree where each node has positive and negative values. Convert this to a tree where each node contains the sum of the left and right sub trees in the original tree. The values of leaf nodes are changed to 0.
// For example, the following tree

//                   10
//                /      \
//              -2        6
//            /   \      /  \ 
//          8     -4    7    5
// should be changed to

//                  20(4-2+12+6)
//                /      \
//          4(8-4)      12(7+5)
//            /   \      /  \ 
//          0      0    0    0


public void makeSumChildNodes(TreeNode root) {
      makeSumChildNodes(root, 0) != 0;
}
  
public int makeSumChildNodes(TreeNode root) {
    if(root == null) return 0;
    int oldVal = root.val;
    int leftSum = makeSumChildNodes(root.left);
    int RightSum = makeSumChildNodes(root.right);
    root.val = leftSum + RightSum;
    return root.val + oldVal;
}