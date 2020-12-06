// Maximum sum increasing subsequence 
// Medium

// Given an array arr of N positive integers, the task is to find the maximum sum increasing subsequence of the given array.

// Example 1:

// Input: N = 5, arr[] = {1, 101, 2, 3, 100} 
// Output: 106
// Explanation:The maximum sum of a
// increasing sequence is obtained from
// {1, 2, 3, 100}
// Example 2:

// Input: N = 3, arr[] = {1, 2, 3}
// Output: 6
// Explanation:The maximum sum of a
// increasing sequence is obtained from
// {1, 2, 3}
// Your Task:  
// You don't need to read input or print anything. Complete the function maxSumIS() which takes N and array arr as input parameters and returns the maximum value.

// Expected Time Complexity: O(N2)
// Expected Auxiliary Space: O(N)

// Constraints:
// 1 ≤ N ≤ 103
// 1 ≤ arr[i] ≤ 105


// Dynamic programming.  

class Solution
{
	public int maxSumIS(int arr[], int n)  
	{  
	    if(arr == null || arr.length == 0) return 0;
	    int maxSum = arr[0];
	    int[] dpMemo = new int[arr.length];
	    for(int idx = 0; idx < arr.length; idx++) {
	        dpMemo[idx] = arr[idx];
	    }
	    
	    for(int end = 1; end < arr.length; end++) {
	        for(int start = 0; start < end; start++) {
	            if(arr[start] < arr[end]) {
	                dpMemo[end] = Math.max(dpMemo[end], dpMemo[start] + arr[end]);
	            }
	        }
            maxSum = Math.max(maxSum, dpMemo[end]); 
	    }
	    
	    return maxSum;
	}  
}

//O( N ^2 ) solution. Similar to LIS.