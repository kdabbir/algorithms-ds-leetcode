// 1090. Largest Values From Labels
// Medium
// We have a set of items: the i-th item has value values[i] and label labels[i].

// Then, we choose a subset S of these items, such that:

// |S| <= num_wanted
// For every label L, the number of items in S with label L is <= use_limit.
// Return the largest possible sum of the subset S.

// Example 1:

// Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
// Output: 9
// Explanation: The subset chosen is the first, third, and fifth item.
// Example 2:

// Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
// Output: 12
// Explanation: The subset chosen is the first, second, and third item.
// Example 3:

// Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
// Output: 16
// Explanation: The subset chosen is the first and fourth item.
// Example 4:

// Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
// Output: 24
// Explanation: The subset chosen is the first, second, and fourth item.


// Note:

// 1 <= values.length == labels.length <= 20000
// 0 <= values[i], labels[i] <= 20000
// 1 <= num_wanted, use_limit <= values.length

// Alternate problem statement

// You are given a sets of N items, each item have a value values[i] and a label labels[i].

// You will need to pick out some items in the set. Being greedy, you want to maximize the total values of your items while following these constraints:

// You can only choose maximum M items.
// You can only choose maximum L items of the same label.
// Return the maximum value of the items you pick.

// Note:

// 1 <= N <= 20,000
// 0 <= values[i], labels[i] <= 20,000
// 1 <= M, L <= N
// N is values.length or labels.length
// M is the num_wanted
// L is the use_limit


class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < values.length; i++){
            items.add(new Item(values[i], labels[i]));
        }

        PriorityQueue<Item> maxHeap = new PriorityQueue<Item>((Item a, Item b) -> b.value - a.value);
        maxHeap.addAll(items);

        HashMap<Integer, Integer> itemCount = new HashMap<>();
        int res = 0;
        while(!maxHeap.isEmpty() && num_wanted > 0){
            Item curr = maxHeap.remove();
            itemCount.put(curr.label, itemCount.getOrDefault(curr.label, 0) + 1);
            if(itemCount.get(curr.label) <= use_limit){
                res += curr.value;
                num_wanted--;
            }
        }
        return res;
    }

    public class Item {
        int value;
        int label;

        public Item(int value, int label){
            this.value = value;
            this.label = label;
        }
    }
}

// Time: O(N)
// Space: O(N)