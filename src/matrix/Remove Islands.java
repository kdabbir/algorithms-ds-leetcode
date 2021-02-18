// Remove Islands


// You're given a two-dimensional array (a matrix) of potentially unequal height
// and width containing only 0s and 1s. The matrix
// represents a two-toned image, where each 1  represents black and
// each 0  represents white. An island is defined as any number of 1s that are horizontally or vertically adjacent (but not
// diagonally adjacent) and that don't touch the border of the image. In other
// words, a group of horizontally or vertically adjacent 1s isn't an
// island if any of those 1s are in the first row, last row, first
// column, or last column of the input matrix.
// Note that an island can twist. In other words, it doesn't have to be a
// straight vertical line or a straight horizontal line; it can be L-shaped, for
// example.
// You can think of islands as patches of black that don't touch the border of
// the two-toned image.


// Write a function that returns a modified version of the input matrix, where
// all of the islands are removed. You remove an island by replacing it with 0s.

// Naturally, you're allowed to mutate the input matrix.
// Sample Input = 
// [
//   [1, 0, 0, 0, 0, 0],
//   [0, 1, 0, 1, 1, 1],
//   [0, 0, 1, 0, 1, 0],
//   [1, 1, 0, 0, 1, 0],
//   [1, 0, 1, 1, 0, 0],
//   [1, 0, 0, 0, 0, 1]
// ]

// Sample Output
// [
//   [1, 0, 0, 0, 0, 0],
//   [0, 0, 0, 1, 1, 1],
//   [0, 0, 0, 0, 1, 0],
//   [1, 1, 0, 0, 1, 0],
//   [1, 0, 0, 0, 0, 0],
//   [1, 0, 0, 0, 0, 1]
// ] 
// Alternate way of forming question:

// Given a binary grid where 0 represents water and 1 represents land. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. Delete all islands except their borders. A border is land adjacent to water. You may assume all four edges of the grid are surrounded by water.

// Approach 1: Changing border connected 1s to 2s 

import java.util.*;

class Program1 {

  public int[][] removeIslands(int[][] matrix) {
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				boolean isBorderRow = row == 0 || row == matrix.length - 1;
				boolean isBorderCol = col == 0 || col == matrix[row].length - 1;
				boolean isBorder = isBorderRow || isBorderCol;
				
				// We dont care about non-border nodes
				if(!isBorder) {
					continue;
				}
			
				// We dont care if val is 0
				if(matrix[row][col] != 1) {
					continue;
				}				
				markConnectedOnes(matrix, row, col);
			}
		}
		
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				int color = matrix[row][col];
				if(color == 1){
					matrix[row][col] = 0;
				} else if(color == 2){
					matrix[row][col] = 1;
				}
			}
		}
		
    return matrix;
  }
	
	public void markConnectedOnes(int[][] matrix, int row, int col) {
		Stack<int[]> neighborStack = new Stack<int[]>();
		neighborStack.push(new int[]{row, col});
		
		while(neighborStack.size() > 0) {
			int[] currNode = neighborStack.pop();
			int currRow = currNode[0], currCol = currNode[1];
			
			matrix[currRow][currCol] = 2;
			
			List<int[]> neighbors = getNeighbors(matrix, row, col);
			for(int[] neighbor: neighbors) {
				if(matrix[neighbor[0]][neighbor[1]] != 1) {
						continue;
				}
				neighborStack.push(neighbor);
			}
		}
		
	}

	
	public List<int[]> getNeighbors(int[][] matrix, int row, int col) {
		List<int[]> neighborsList = new ArrayList<int[]>();
		if(row - 1 >= 0) neighborsList.add(new int[] {row - 1, col});
		if(col - 1 >= 0) neighborsList.add(new int[] {row, col - 1});
		if(row + 1 < matrix.length) neighborsList.add(new int[] {row + 1, col});
		if(col + 1 < matrix[row].length) neighborsList.add(new int[] {row, col + 1});
		return neighborsList;
	}
	
}


// Approach 2 : marking border connected ones as true and using that to change inner ones as 0s

class Program {

  public int[][] removeIslands(int[][] matrix) {
    boolean[][] connectedToBorderNodes = new boolean[matrix.length][matrix[0].length];
		
		for(int i = 0; i < matrix.length; i++){
			connectedToBorderNodes[i][matrix[0].length - 1] = false;
		}
		
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				boolean isBorderRow = row == 0 || row == matrix.length - 1;
				boolean isBorderCol = col == 0 || col == matrix[row].length - 1;
				boolean isBorder = isBorderRow || isBorderCol;
				
				// We dont care about non-border nodes
				if(!isBorder) {
					continue;
				}
			
				// We dont care if val is 0
				if(matrix[row][col] != 1) {
					continue;
				}				
				markConnectedOnes(matrix, row, col, connectedToBorderNodes);
			}
		}
		
		for(int row = 1; row < matrix.length - 1; row++) {
			for(int col = 1; col < matrix[row].length - 1; col++) {
				if(connectedToBorderNodes[row][col]) {
					continue;
				}
				
				matrix[row][col] = 0;
			}
		}
		
    return matrix;
  }
	
	public void markConnectedOnes(int[][] matrix, int row, int col,  boolean[][] connectedToBorderNodes) {
		Stack<int[]> neighborStack = new Stack<int[]>();
		neighborStack.push(new int[]{row, col});
		
		while(neighborStack.size() > 0) {
			int[] currNode = neighborStack.pop();
			int currRow = currNode[0], currCol = currNode[1];
			
			// Already visited, continue to next item in stack
			if(connectedToBorderNodes[currRow][currCol]) {
				continue;
			}
			
			connectedToBorderNodes[currRow][currCol] = true;
			int[][] neighbors = getNeighbors(matrix, row, col);
			for(int[] neighbor: neighbors) {
				if(matrix[neighbor[0]][neighbor[1]] != 1) {
						continue;
				}
				neighborStack.push(neighbor);
			}
		}
		
	}
	
	public List<int[]> getNeighbors(int[][] matrix, int row, int col) {
		List<int[]> neighborsList = new ArrayList<int[]>();
		if(row - 1 >= 0) neighborsList.add(new int[] {row - 1, col});
		if(col - 1 >= 0) neighborsList.add(new int[] {row, col - 1});
		if(row + 1 < matrix.length) neighborsList.add(new int[] {row + 1, col});
		if(col + 1 < matrix[row].length) neighborsList.add(new int[] {row, col + 1});
		return neighborsList;
	}
	
}
