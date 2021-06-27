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



public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
    int[][] nums=new int[values.length][2];
    for(int i=0;i<values.length;i++) {
        nums[i][0]=values[i];
        nums[i][1]=labels[i];
    }
    Arrays.sort(nums, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            return b[0]-a[0];
        }
    });
    int res=0;
    int[] used=new int[20001];
    for(int i=0;i<nums.length;i++) {
        if(used[nums[i][1]]<use_limit) {
            used[nums[i][1]]++;
            num_wanted--;
            res+=nums[i][0];
        }
        if(num_wanted==0) return res;
    }
    return res;
}

class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        HashMap<Integer, Integer> hash_label = new HashMap<Integer, Integer>();

        // create a hashmap for maintaining the use_limit of each label group
		for (int i : labels) {
			hash_label.put(i, 0);
		}

		int size = values.length;
		int[][] val_lab = new int[size][2];

        // creating a 2D array which has values and labels corresponding to the values
		for (int i = 0; i < size; i++) {
			val_lab[i][0] = values[i];
			val_lab[i][1] = labels[i];
		}

        // sorting the array in descending order based on the values from column 0
		Arrays.sort(val_lab, new Comparator<int[]>() {
			public int compare(final int[] entry1, final int[] entry2) {

				if (entry1[0] < entry2[0])
					return 1;
				else
					return -1;
			}
		});
		int sum = 0;

		for (int i = 0; i < size; i++) {
			int val = val_lab[i][0];
			int lab = val_lab[i][1];
        // if label usage less than use_limit and subset size is less than num_wanted, include array item in the subset
			if (num_wanted > 0 && hash_label.get(lab) < use_limit) {

				sum += val;
				hash_label.put(lab, hash_label.get(lab) + 1);
				num_wanted--;
			}
		}

		return sum;
    }
}