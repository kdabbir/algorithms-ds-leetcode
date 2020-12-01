// 55. Jump Game
// Medium

// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Determine if you are able to reach the last index.


// Greedy solution

class Solution {
    public boolean canJump(int[] nums) {
        int lastLen = nums.length - 1;
        for(int curr = nums.length - 1; curr >= 0; curr--) {
            if(curr + nums[curr] >= lastLen) {
                lastLen = curr;
            }
        }
        return lastLen == 0;
    }
  
}

// DP Bottom up

enum Index {
    GOOD, BAD, UNKNOWN
}

class Solution {
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
        for(Index item: memo) {
            item = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;
        
        for(int curr = nums.length - 2; curr >= 0; curr--){ 
            int maxLen = curr + nums[curr];
            for(int nextJump = curr + 1; nextJump <= maxLen; nextJump++) {
                if(memo[nextJump] == Index.GOOD) {
                    memo[curr] = Index.GOOD;
                    break;
                }   
            }
        }
        return memo[0] == Index.GOOD;
    }
  
}



// DP Top Down (Recursion with Memoization)

enum Index {
    GOOD, BAD, UNKNOWN
}

public class Solution {
    Index[] memo;

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }
}

// Time: O (N ^ 2) TLE
// Space: O (N)


// Recursion (TLE)
class Solution {
    public boolean canJump(int[] nums) {
        return canReachEndFromPos(0, nums);
    }
    
    public boolean canReachEndFromPos(int pos, int[] nums) {
        if(pos == nums.length - 1) return true;
        
        int maxReach = Math.min(pos + nums[pos], nums.length - 1);
        for(int nextPos = pos + 1; nextPos <= maxReach; nextPos++) {
            if(canReachEndFromPos(nextPos, nums)){
                return true;
            }
        }
        return false;
    }
}

// Time: O (N ^ 2) TLE
// Space: O (N)


