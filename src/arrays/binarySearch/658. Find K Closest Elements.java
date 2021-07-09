// 658. Find K Closest Elements: https://leetcode.com/problems/find-k-closest-elements/

// Medium

// Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

// An integer a is closer to x than an integer b if:

// |a - x| < |b - x|, or
// |a - x| == |b - x| and a < b


// Example 1:

// Input: arr = [1,2,3,4,5], k = 4, x = 3
// Output: [1,2,3,4]
// Example 2:

// Input: arr = [1,2,3,4,5], k = 4, x = -1
// Output: [1,2,3,4]


// Constraints:

// 1 <= k <= arr.length
// 1 <= arr.length <= 104
// arr is sorted in ascending order.
// -104 <= arr[i], x <= 104

// Binary search to land at closest range and expand window by k length

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        int start = 0, end = arr.length - k;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(x - arr[mid] > arr[mid + k] - x ) {
               start = mid + 1;
            } else {
               end = mid;
            }
        }

        for(int i = start; i < start + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
// Time: O(log(N - k) + K)
// Space: O(1)


// Binary search to find closest position and expand left and right pointers to find k elements.

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        int start = 0, end = arr.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(arr[mid] >= x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        start -= 1;
        end = start + 1;

        while(end - start - 1 < k) {
            if(start == -1) {
                end += 1;
                continue;
            }
            if(end == arr.length || Math.abs(arr[start] - x) <= Math.abs(arr[end] - x)) {
                start -= 1;
            } else {
                end += 1;
            }
        }
        for(int idx = start + 1; idx < end; idx++) {
            result.add(arr[idx]);
        }

        return result;

    }
}

// Time: O(NLogN + K)
// Space: O(1)

// Sort with custom comparator

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Convert from array to list first to make use of Collections.sort()
        List<Integer> sortedArr = new ArrayList<Integer>();
        for (int num: arr) {
            sortedArr.add(num);
        }

        // Sort using custom comparator
        Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        // Only take k elements
        sortedArr = sortedArr.subList(0, k);

        // Sort again to have output in ascending order
        Collections.sort(sortedArr);
        return sortedArr;
    }
}

