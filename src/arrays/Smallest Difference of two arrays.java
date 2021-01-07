
// Smallest Difference of two arrays

// Write a function that takes in two non-empty arrays of integers, finds the
// pair of numbers (one from each array) whose absolute difference is closest to
// zero, and returns an array containing these two numbers, with the number from
// the first array in the first position.

// Note that the absolute difference of two integers is the distance between
// them on the real number line. For example, the absolute difference of -5 and 5
// is 10, and the absolute difference of -5 and -4 is 1.

// You can assume that there will only be one pair of numbers with the smallest
// difference.

// Sample Input: A1 = [-1, 5, 10, 20, 28, 3]
// A2 = [26, 134, 135, 15, 17]

// Sample output:
// [28, 26]

import java.util.*;

class Program {
  public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
   	    Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);
		int[] smallestDiff = new int[2];
		int pointer1 = 0;
		int pointer2 = 0;
		int smallestVal = Integer.MAX_VALUE;
		int currDiff = Integer.MAX_VALUE;
		while(pointer1 < arrayOne.length && pointer2 < arrayTwo.length) {
			int firstNum = arrayOne[pointer1];
			int secondNum = arrayTwo[pointer2];
			if(firstNum <  secondNum){
				currDiff =  secondNum - firstNum;
				pointer1++;
			} else if(secondNum < firstNum) {
				currDiff =  firstNum - secondNum;
				pointer2++;
			} else {
				smallestDiff = new int[] {arrayOne[pointer1], arrayTwo[pointer2]};
				return smallestDiff;
			}
			if(currDiff < smallestVal) {
				smallestVal = currDiff;
				smallestDiff = new int[] {firstNum, secondNum};
			}
		}
		
		return smallestDiff;
  }
}
