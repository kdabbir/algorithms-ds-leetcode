// Move Element To End

// You're given an array of integers and an integer. Write a function that moves
// all instances of that integer in the array to the end of the array and returns
// the array.

// The function should perform this in place (i.e., it should mutate the input
// array) and doesn't need to maintain the order of the other integers.

// Sample input:  = [2, 1, 2, 2, 2, 3, 4, 2], toMove = 2
// Sample output: [1, 3, 4, 2, 2, 2, 2, 2] 

import java.util.*;

class Program {
  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
		int start = 0, end = array.size() - 1;
		while(start < end) {
			while(start < end && array.get(end) == toMove)
				end--;
			if(array.get(start) == toMove){
				int temp = array.get(end);
				array.set(end, array.get(start));
				array.set(start, temp);
			}
			start++;
		}
    return array;
  }
}
