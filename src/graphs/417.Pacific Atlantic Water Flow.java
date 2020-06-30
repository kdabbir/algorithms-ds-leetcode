// 417. Pacific Atlantic Water Flow
// Medium

// Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

// Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

// Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

// Note:

// The order of returned grid coordinates does not matter.
// Both m and n are less than 150.
 

// Example:

// Given the following 5x5 matrix:

//   Pacific ~   ~   ~   ~   ~ 
//        ~  1   2   2   3  (5) *
//        ~  3   2   3  (4) (4) *
//        ~  2   4  (5)  3   1  *
//        ~ (6) (7)  1   4   5  *
//        ~ (5)  1   1   2   4  *
//           *   *   *   *   * Atlantic

// Return:

// [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 
class Solution {
    public int[][] directions = new int[][]{{0, 1}, {0,-1}, {1,0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> output = new ArrayList<>();
        if(matrix == null || matrix.length == 0) return output;
        int rowLength = matrix.length, colLength = matrix[0].length;
        boolean[][] atlVisited = new boolean[rowLength][colLength];
        boolean[][] pacVisited = new boolean[rowLength][colLength];
        
        for(int row = 0 ; row < rowLength; row++){
            dfs(matrix, pacVisited, Integer.MIN_VALUE, row, 0);
            dfs(matrix, atlVisited, Integer.MIN_VALUE, row, colLength - 1);
        }
        
        for(int col = 0 ; col < colLength; col++){
            dfs(matrix, pacVisited, Integer.MIN_VALUE, 0, col);
            dfs(matrix, atlVisited, Integer.MIN_VALUE, rowLength - 1, col);
        }
        
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
             if(atlVisited[i][j] && pacVisited[i][j]){
                 ArrayList<Integer> currArr = new  ArrayList<Integer>();
                 currArr.add(i);
                 currArr.add(j);
                 output.add(currArr);
             }   
            }
        }
        return output;    
    }
    
    public void dfs(int[][] matrix, boolean[][] visited, int height, int row, int col){
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || visited[row][col] == true || matrix[row][col] < height){
            return;
        }

        visited[row][col] = true;
        for(int[] dir : directions){
            dfs(matrix, visited, matrix[row][col], row + dir[0], col + dir[1]);
        }
    }
}