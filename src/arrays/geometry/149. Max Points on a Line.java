// 149. Max Points on a Line: https://leetcode.com/problems/max-points-on-a-line/

// Hard

// Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

// Example 1:


// Input: points = [[1,1],[2,2],[3,3]]
// Output: 3
// Example 2:


// Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
// Output: 4

// Constraints:

// 1 <= points.length <= 300
// points[i].length == 2
// -104 <= xi, yi <= 104
// All the points are unique.

class Solution {
    public int maxPoints(int[][] points) {
            int maxPoints = 1;

		for(int idx1 = 0; idx1 < points.length; idx1++) {
			Map<String, Integer> pointsWithSameSlope = new HashMap<>();
			for(int idx2 = idx1 + 1; idx2 < points.length; idx2++) {
				int[] point1 = points[idx1];
				int[] point2 = points[idx2];
                int[] slope = getSlopeBetweenPoints(point1, point2); // Idea is, points with same slope is on the same line.

                String hashKey = getHashKey(slope); // Instead of storing fractions, better to encode it as a string and store it.

				if(!pointsWithSameSlope.containsKey(hashKey)){
					pointsWithSameSlope.put(hashKey, 1);
				}

				pointsWithSameSlope.put(hashKey, pointsWithSameSlope.get(hashKey) + 1);
				maxPoints = Math.max(maxPoints, pointsWithSameSlope.get(hashKey));
			}
		}
    return maxPoints;
    }

	public String getHashKey(int[] slope) {
		return String.valueOf(slope[0]) + ":" + String.valueOf(slope[1]);
	}

	public int[] getSlopeBetweenPoints(int[] point1, int[] point2){
        int[] slope = new int[] { 1, 0}; // Slope of a vertical line. Vertical line has 1/0 -> infinity slope and horizontal line has 0 slope.

		if(point1[0] != point2[0]) { // If line is not vertical since X-axis is different.
			int xdiff = point1[0] - point2[0];
			int ydiff = point1[1] - point2[1];
			int gcd = getGcdBetween2Numbers(Math.abs(xdiff), Math.abs(ydiff)); // GCD to make sure fractions can be normalized to a smaller value and stored in hashmap.
			xdiff = xdiff/gcd;
			ydiff = ydiff/gcd;
			if(xdiff < 0) {
				xdiff *= -1;
				ydiff *= -1;
			}
			slope = new int[]{ydiff, xdiff};
		}
		return slope;
	}

	public int getGcdBetween2Numbers(int numb1, int numb2){
		int a = numb1, b = numb2;
		while(true) {
			if(a == 0) return b;
			if(b == 0) return a;
			int temp = a;
			a = b;
			b = temp % b;
		}
	}
}

// Time: O(N ^ 2)
// Space: O(N)