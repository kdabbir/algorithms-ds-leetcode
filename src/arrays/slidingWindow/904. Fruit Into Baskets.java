// 904. Fruit Into Baskets: https://leetcode.com/problems/fruit-into-baskets/

// Medium

// 904. Fruit Into Baskets
// Medium

// 1267

// 1788

// Add to List

// Share
// In a row of trees, the i-th tree produces fruit with type tree[i].

// You start at any tree of your choice, then repeatedly perform the following steps:

// Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
// Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
// Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

// You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

// What is the total amount of fruit you can collect with this procedure?



// Example 1:

// Input: [1,2,1]
// Output: 3
// Explanation: We can collect [1,2,1].
// Example 2:

// Input: [0,1,2,2]
// Output: 3
// Explanation: We can collect [1,2,2].
// If we started at the first tree, we would only collect [0, 1].
// Example 3:

// Input: [1,2,3,2,2]
// Output: 4
// Explanation: We can collect [2,3,2,2].
// If we started at the first tree, we would only collect [1, 2].
// Example 4:

// Input: [3,3,3,1,2,1,1,2,3,3,4]
// Output: 5
// Explanation: We can collect [1,2,1,1,2].
// If we started at the first tree or the eighth tree, we would only collect 4 fruits.


// Note:

// 1 <= tree.length <= 40000
// 0 <= tree[i] < tree.length

// Sliding window using hashmap.
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int start = 0, maxLength = Integer.MIN_VALUE;

        for(int end = 0; end < tree.length; end++) {
            countMap.put(tree[end], end); //putting fruitId vs index.
            if(countMap.size() > 2) {
                int minIdx = Collections.min(countMap.values());
                countMap.remove(tree[minIdx]);  // Removing left most/min Idx to remove extra size value from map.
                start = minIdx + 1;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}

// Time: O(N)
// Space: O(2)

// Other approach using count.
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}