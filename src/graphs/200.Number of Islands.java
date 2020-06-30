// 200. Number of Islands
// Medium


// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

// Example 1:

// Input:
// 11110
// 11010
// 11000
// 00000

// Output: 1
// Example 2:

// Input:
// 11000
// 11000
// 00100
// 00011

// Output: 3


class Solution {
    public final int[][] directions = new int[][]{{0,1}, {0,-1}, {-1, 0}, {1, 0}};
    public final char seenChar = '.';
    public int numIslands(char[][] grid) {
        int count = 0;
        if(grid == null || grid.length == 0) return count;
        
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == '1'){
                   count++;
                   dfs(grid, row, col);
                }
            }
                }
                return count;
    }
    
     public void dfs(char[][] grid, int row, int col){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] != '1'){
            return;
        }
        grid[row][col] = seenChar;
        for(int[] dir: directions){
            dfs(grid, row + dir[0], col + dir[1]);
        }
    }
}