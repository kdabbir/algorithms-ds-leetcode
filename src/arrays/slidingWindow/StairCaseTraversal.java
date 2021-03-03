// Staircase Traversal
// Medium
// You're given two positive integers representing the height of a staircase and
// the maximum number of steps that you can advance up the staircase at a time.
// Write a function that returns the number of ways in which you can climb the
// staircase.

// For example, if you were given a staircase of height = 3, maxSteps = 2,  you could climb the staircase in 3 ways. You could
// take 1 step, 1 step, then 1 step, you could also take
// 1 step, then 2 steps etc

// Sample Input
// height = 4, maxSteps = 2
// Output: 5

import java.util.*;
// Sliding window algorithm. Refer doc for flow.
class Program {

  public int staircaseTraversal(int height, int maxSteps) {
		int[] dp = new int[height + 1];
		dp[0] = 1;
		int currNumberOfWays = 0;
		for(int currHeight = 1; currHeight <= height;currHeight++){
			int startWindow = currHeight - maxSteps - 1;
			int endWindow = currHeight - 1;
			if(startWindow >= 0)
				currNumberOfWays -= dp[startWindow];
			currNumberOfWays += dp[endWindow];
			dp[currHeight] = currNumberOfWays;
		}
    return dp[height];
  }
}

// Time complexity: O(N)
// Space complexity: O(N)

// Optimized dp with while loop
class Program {

  public int staircaseTraversal(int height, int maxSteps) {
		int[] dp = new int[height + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int currHeight = 2; currHeight <= height;currHeight++){
			int step = 1;
			while(step <= currHeight && step <= maxSteps) {
				dp[currHeight] += dp[currHeight - step]; 
				step += 1;
			}	
		}
    return dp[height];
  }
}

// Time complexity: O(N *K) N - height and k -max steps
// Space complexity: O(N) 

// Classic dp, coin change 2 style.
class Program {

  public int staircaseTraversal(int height, int maxSteps) {
		int[] dp = new int[height + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int currHeight = 2; currHeight <= height;currHeight++){
			for(int step = 1; step <= maxSteps; step++){
				if(step <= currHeight) {
					dp[currHeight] += dp[currHeight - step]; 
				}
			}
		}
    return dp[height];
  }
}
