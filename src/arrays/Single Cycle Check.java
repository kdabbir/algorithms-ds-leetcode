// Single Cycle Check: https://www.algoexpert.io/questions/Single%20Cycle%20Check
// Medium

// You're given an array of integers where each integer represents a jump of its
// value in the array. For instance, the integer 2  represents a jump
// of two indices forward in the array; the integer. -3  represents a
// jump of three indices backward in the array.


// If a jump spills past the array's bounds, it wraps over to the other side. For
// instance, a jump of -1. At index 0 brings us to the last index in
// the array. Similarly, a jump of 1  at the last index in the array brings us to
// index 0.

// Sample i/p:  = [2, 3, 1, -4, -4, 2]
// Sample o/p: true

import java.util.*;

class Program {
  public static boolean hasSingleCycle(int[] array) {
    int elementsVisited = 0, currIdx = 0;
		while(elementsVisited < array.length){
			if(elementsVisited > 0 && currIdx == 0) return false;
			elementsVisited++;
			currIdx = getNextIdx(currIdx, array);
		}
		
    return currIdx == 0;
  }
	public static int getNextIdx(int currIdx, int[] array) {
		int jump = array[currIdx];
		int nextIdx = (currIdx + jump) % array.length;	// Modulo is to get correct index if index goes out of bounds by calculating the remainder.
		return nextIdx < 0 ?  nextIdx + array.length: nextIdx; //To fetch correct modulus in Java for negative numbers: https://torstencurdt.com/tech/posts/modulo-of-negative-numbers/
	}
	
}
