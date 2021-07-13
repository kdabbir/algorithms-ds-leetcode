// 875. Koko Eating Bananas : https://leetcode.com/problems/koko-eating-bananas/

// Medium

// Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

// Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

// Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

// Return the minimum integer k such that she can eat all the bananas within h hours.



// Example 1:

// Input: piles = [3,6,7,11], h = 8
// Output: 4
// Example 2:

// Input: piles = [30,11,23,4,20], h = 5
// Output: 30
// Example 3:

// Input: piles = [30,11,23,4,20], h = 6
// Output: 23


// Constraints:

// 1 <= piles.length <= 104
// piles.length <= h <= 109
// 1 <= piles[i] <= 109

// Binary search template.

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1, end = getMaxPile(piles);
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(canEatBananas(piles, mid, h)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public boolean canEatBananas(int[] piles, int turnBananaCount, int hour)    {
        int currHour = 0;
        for(int pile: piles) {
            currHour += pile / turnBananaCount;
            if(pile % turnBananaCount != 0) currHour++;
        }
        return currHour <= hour;
    }

    public int getMaxPile(int[] piles) {
        int maxPile = Integer.MIN_VALUE;
        for(int pile: piles) {
            maxPile = Math.max(pile, maxPile);
        }
        return maxPile;
    }
}

// Time: O(NLogW)  //W is max size of pile
// Space: O(1)