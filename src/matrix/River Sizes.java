// River Sizes
// MEDIUM


// You're given a two-dimensional array (a matrix) of potentially unequal height
// and width containing only 1 and 0. Each 1  represents land, and each 0 represents part of a
// river. A river consists of any number of s that are either
// horizontally or vertically adjacent (but not diagonally adjacent). The number
// of adjacent s forming a river determine its size.

// Note that a river can twist. In other words, it doesn't have to be a straight
// vertical line or a straight horizontal line; it can be L-shaped, for example.


// Write a function that returns an array of the sizes of all rivers represented
// in the input matrix. The sizes don't need to be in any particular order.    

// Example input:
// {
//     "matrix": [
//       [1, 0, 0, 1, 0],
//       [1, 0, 1, 0, 0],
//       [0, 0, 1, 0, 1],
//       [1, 0, 1, 0, 1],
//       [1, 0, 1, 1, 0]
//     ]
//   }

//Output: [2, 1, 5, 2, 2]
import java.util.*;

class Program {
	
	public static final int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
	
  public static List<Integer> riverSizes(int[][] matrix) {
    List<Integer> output = new ArrayList<Integer>();
    for(int row = 0; row < matrix.length; row++) {
        for(int col = 0; col < matrix[row].length; col++) {
            if(matrix[row][col] == 1){
                output.add(0);
                dfsSearch(matrix, row, col, output);
            }
        }
    }
    return output;
  }
	
	public static void dfsSearch(int[][] matrix, int row, int col, List<Integer> output) {
		if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] == 0) return;
		if(matrix[row][col] == 1) {
			output.set(output.size() - 1, output.get(output.size() - 1) + 1);
			matrix[row][col] = 0;
		}
		
		for(int[] dir: dirs){
			dfsSearch(matrix, row + dir[0], col + dir[1], output);
		}
		return;
	} 
}
