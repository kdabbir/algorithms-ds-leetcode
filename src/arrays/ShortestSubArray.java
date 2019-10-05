package arrays;
import java.util.Arrays;

// Problem: Given an array of integers, find the shortest sub array, that if sorted, results in theentire array being sorted For example:
//[1,2,4,5,3,5,6,7] --> [4,5,3] - If you sort from indices 2 to 4, the entire array is sorted.
//[1,3,5,2,6,4,7,8,9] --> [3,5,2,6,4] -  If you sort from indices 1 to 5, the entire array is sorted.

public class ShortestSubArray {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(squares(new int[]{-4,-2,-1,0,3,5})));
    }
    
    public static int[] squares(int[] arr){
        int start = 0, end = arr.length-1;
        int[] resultArr = new int[arr.length];
        int resultIndex = resultArr.length - 1;
        while(start <= end){
            if(abs(arr[start]) > abs(arr[end]))  {
                resultArr[resultIndex] = square(arr[start]);
                start++;
            } else {
                resultArr[resultIndex] = square(arr[end]);
                end--;
            }  
            resultIndex--;
        }
        return resultArr;
    }

    public static int abs(int number) {
        return number >= 0 ? number : -1 * number;
    }
    public static int square(int number) {
        return number * number;
    }
}