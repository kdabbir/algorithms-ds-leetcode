// Rectangle Mania
// Hard

// Write a function that takes in a list of Cartesian coordinates (i.e., (x, y)
// coordinates) and returns the number of rectangles formed by these coordinates.

// A rectangle must have its four corners amongst the coordinates in order to be
// counted, and we only care about rectangles with sides parallel to the x and y
// axes (i.e., with horizontal and vertical sides--no diagonal sides).


// You can also assume that no coordinate will be farther than 100 units from the
// origin.

// Sample Input
// = [
//     [0, 0], [0, 1], [1, 1], [1, 0],
//     [2, 1], [2, 0], [3, 1], [3, 0],
//   ]
//   Sample Output
//   6

// Refer to doc for explanation
import java.util.*;

class Program {
  public static int rectangleMania(List<Integer[]> coords) {
    Set<String> coordSet = getCoordSet(coords);
		int rectangleCount = 0;
		for(Integer[] coord1: coords){
			for(Integer[] coord2: coords) {
				if(!isUpperRightValid(coord1, coord2)) continue;

				String upperLeftCoord = coordToString(new Integer[] {coord1[0], coord2[1]});
				String lowerRightCoord =  coordToString(new Integer[] {coord2[0], coord1[1]});
				if(coordSet.contains(upperLeftCoord) && coordSet.contains(lowerRightCoord)) {
					rectangleCount++;
				}
			}
		}
		return rectangleCount;
  }

	public static String coordToString(Integer[] coord) {
		return Integer.toString(coord[0]) + '-' + Integer.toString(coord[1]);
	}

	public static boolean isUpperRightValid(Integer[] coord1, Integer[] coord2) {
		return coord2[0] > coord1[0] && coord2[1] > coord1[1];
	}

	public static Set<String> getCoordSet(List<Integer[]> coords) {
		Set<String> coordSet = new HashSet<>();
		for(Integer[] coord: coords) {
			coordSet.add(coordToString(coord));
		}
		return coordSet;
	}

  static class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
// Time: O(N ^2)
// Space: O(N)