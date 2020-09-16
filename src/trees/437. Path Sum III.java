//437. Path Sum III
// Medium

// You are given a binary tree in which each node contains an integer value.

// Find the number of paths that sum to a given value.

// The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

// The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

// Example:

// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

//       10
//      /  \
//     5   -3
//    / \    \
//   3   2   11
//  / \   \
// 3  -2   1

// Return 3. The paths that sum to 8 are:

// 1.  5 -> 3
// 2.  5 -> 2 -> 1
// 3. -3 -> 11

class Solution {
    public int count = 0;

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        dfs(root, 0, sum, map);
        return count;
    }


    public void dfs(TreeNode curr, int currSum, int target,  HashMap<Integer, Integer> map) {
        if(curr == null) return;
        currSum += curr.val;
        if(currSum == target) {
            count++;
        }
        
        count+= map.getOrDefault(currSum - target , 0);
    
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        
        dfs(curr.left, currSum, target, map);
        
        dfs(curr.right, currSum, target, map);
    
        map.put(currSum, map.get(currSum) - 1);
    }
}