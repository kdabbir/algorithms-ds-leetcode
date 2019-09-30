package app;

import java.util.ArrayList;

// Problem: Given a sorted array of integers, find two numbers in the array that sumto a given integer target.
// For example, if a = [1,2,3,5,6,7] and target = 11, the answer will be 5 and 6

public class Arrays_TwoSum {
    public static void main(String[] args) throws Exception {
       System.out.println(twoSum(new int[]{1,2,3,5},4)) ; 
    }

    public static ArrayList<Integer> twoSum(int[] a, int target) {
        if (a == null) return null;
        int start = 0, end = a.length - 1;
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        while (start < end) {
          int sum = a[start] + a[end];
          if(sum < target)
            start++;
          else if(sum > target)
            end--;
          else
          {
            arrList.add(a[start]);
            arrList.add(a[end]);
            return arrList;
          }
        }
        return null;
      }
}