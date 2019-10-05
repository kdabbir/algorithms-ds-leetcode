package arrays;
import java.util.Arrays;
// Problem: Given a sorted array in non-decreasing order, return an array of squares of each number, also in non-decreasing order. For example:
// Sample input: [-4,-2,-1,0,3,5] 
// Sample output: [0,1,4,9,16,25]

public class Squares {
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