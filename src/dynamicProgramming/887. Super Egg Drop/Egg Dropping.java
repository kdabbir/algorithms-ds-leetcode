// 887. Super Egg Drop: https://leetcode.com/problems/super-egg-drop/
// Hard

// You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.

// You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.

// Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

// Return the minimum number of moves that you need to determine with certainty what the value of f is.

// Example 1:

// Input: k = 1, n = 2
// Output: 2
// Explanation:
// Drop the egg from floor 1. If it breaks, we know that f = 0.
// Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
// If it does not break, then we know f = 2.
// Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
// Example 2:

// Input: k = 2, n = 6
// Output: 3
// Example 3:

// Input: k = 3, n = 14
// Output: 4

// Constraints:

// 1 <= k <= 100
// 1 <= n <= 104


// DP solution with binary search (Refer doc for proofs) or https://www.youtube.com/watch?v=3hcaVyX00_4&ab_channel=TusharRoy-CodingMadeSimple


class Solution {
    public int superEggDrop(int K, int N) {
       int[][] attemptsDp = new int[K + 1][N + 1];
        for(int floor = 1; floor <= N; floor++) {
            attemptsDp[1][floor] = floor;
        }

        for(int eggNum = 2; eggNum <= K; eggNum++) {
            for(int floor = 1; floor <= N; floor++) {
                int minVal = Integer.MAX_VALUE;
                int leftFloor = 1;
                int rightFloor = floor;

                while(leftFloor <= rightFloor) {
                    int midFloor = leftFloor + ( rightFloor - leftFloor)/2;
                    int eggBreaksFl = attemptsDp[eggNum - 1][midFloor - 1];
                    int eggDoesntbreakFl = attemptsDp[eggNum][floor - midFloor];
                    minVal = Math.min(minVal, Math.max(eggBreaksFl,eggDoesntbreakFl) + 1);
                    if(eggBreaksFl == eggDoesntbreakFl) {
                        break;
                    } else if(eggBreaksFl < eggDoesntbreakFl) {
                        leftFloor = midFloor + 1;
                    } else {
                        rightFloor = midFloor - 1;
                    }
                }

                attemptsDp[eggNum][floor] = minVal;
            }
        }
        return attemptsDp[K][N];
     }

 }

// Time complexity: O(KNlogN)
// Space complexity: O(KN)

// Recursive solution for binary search with memoization
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }
    private int helper(int K, int N, int[][] memo) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }
        int min = N;
        for (int i = 1; i <= N; i++) {
            int left = helper(K - 1, i - 1, memo);
            int right = helper(K, N - i, memo);
            min = Math.min(min, Math.max(left, right) + 1);
        }
        memo[K][N] = min;
        return min;
    }
}

// DP solution without binary search

final int results[][] = new int[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            results[i][1] = i;
        }
        for (int i = 0; i < k + 1; i++) {
            results[1][i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < k + 1; j++) {
                results[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x < i; x++) {
                    final int brokenEggResult = results[x - 1][j - 1];
                    final int EggSurvivedResult = results[i - x][j];
                    int temp = Math.max(brokenEggResult, EggSurvivedResult) + 1;
                    if (temp < results[i][j]) {
                        results[i][j] = temp;
                    }
                }
            }
        }
        for (final int[] a : results) {
            System.out.println(Arrays.toString(a));
        }
        return results[n][k];


// Using moves concept, DP Solution
class Solution {
    public int superEggDrop(int K, int N) {
         int[][] dp = new int[N + 1][K + 1];
         int moves = 0;

        while(dp[moves][K] < N) {
            moves++;
            for(int eggNum = 1; eggNum <= K; eggNum++) {
                dp[moves][eggNum] = dp[moves - 1][eggNum - 1] + 1 + dp[moves - 1][eggNum];
            }
        }
        return moves;
     }
 }

// Time Complexity: O(KLogN)
// When K is 1, yes, you will have N loops. But when K is other than 1, you will break the N-loop when dp value reaches N. Not clear why it is O(KlogN) but it is definitely not O(KN).
// Space Complexity: O(KN)


// Explanation:

//  Imagine you have budget of m moves and k eggs (drop eggs m times and break no more than k of them) and we like to figure out maximum floor N, you like to pick a floor n to drop the first egg, the value of n can not be too big as otherwise if it broke, you can not finish the n-1 floors below it with (m-1,k-1). In fact, the largest n you can afford is dp[m-1][k-1]+1. If the first egg does not break, you can check dp[m-1][k] floors above it. So overall, the maximum floors is dp[m-1][k-1]+1+dp[m-1][k].


// dp[m][k] = dp[m-1][k-1] + dp[m-1][k] + 1;

// case 1: Egg Breaks and move used. so dp[m-1][k-1] here->m=move, k=egg
// case 2: Egg Not break and move used. so, dp[m-1][k] here-> m=mode, k=egg
// and +1 is current floor.

// in worst case we keep both try so we add it.

// ans = case1 + case2 + 1

// He has turned the problem around from
// "How many moves do you need to check N floors if you have K eggs"
// to:
// "How many floors can you check given M moves available and K eggs".

// If you can solve this second problem than you can just increase the moves M one by one until you are able to check a number of floors larger or equal to the number N which the problem requires.
// He then defined
// dp[M][K] as the maximum number of floors that you can check within M moves given K eggs

// A move essentially is dropping an egg and it either breaks or doesn't break.
// Case A: The egg breaks and now you have spent 1 move (M=M-1) and also lost 1 egg (K=K-1). You can still check dp[M-1][K-1] floors, with your remaining eggs and moves.
// Case B: The egg remains and you only loose one move (M=M-1). You can still check dp[M-1][K] floors.
// Additionally you just checked a floor by dropping the egg from it.
// Therefore dp[M][K] = dp[M - 1][k - 1] + dp[M - 1][K] + 1
// As you can see we can easily calculate how many floors we can check in M moves if we know how many floors we can check in M-1 moves.

// However we not only have to know how many floors we can can check with one move less, but also how many we can check with one move and one egg less. Therefore we have to calculate how many moves we can check for all number off eggs from 1 to K.

// An example:
// N = 6 and K = 2
// Turn the problem around: How many floors can you check with 2 eggs and M moves:

// Solve for M=1, K=1,2
// you can only check 1 floor (since afterwards you have no more moves left)

// Solve for M=2, K=1
// Case A: Your egg breaks, you have no more eggs left and can check nothing. dp[M=1,K=0]=0
// Case B: your egg survives and you can use it to test an additional floor above the floor you just tested. dp[M=1,K=1]=1
// dp[2][1]=dp[1][0]+dp[1][1]+1=0+1+1=2

// Solve for M=2, K=2
// Case A: Your egg breaks: you have 1 move left and 1 egg. Since you know that the floor F where the eggs break is below the floor you just tested you can now check dp[M=1,K=1] floors below you, with only 1 move left you check 1 additional floor below. dp[M=1,K=1]=1
// Case B: Your eggs survives and you start to search above the current floor. dp[1][2] is still only 1 move and we can check 1 floor. dp[1][2]
// dp[2][2]=1+1+1=3

// Solve for M=3, K=1
// Case A: Your egg breaks and you are out of eggs, no chance to check anything anymore
// Case B: Your egg survives and you can use it for 2 more moves dp[2][1], which as we established above is enough to check 2 floors.
// dp[3][1]=0+2+1=3

// Solve for M=3, K=2
// Case A: Your egg breaks and you check dp[2][1]=2 additional floors
// Case B: Your egg survives and you check dp[2][2]=3 additional floors
// dp[3][2]=2+3+1=6

// As you can see 3 moves and 2 eggs allows you to check 6 floors. Which answers the original question how many moves you need to check 6 floors given 2 eggs,
// The answer is 3



