// 200. Number of Islands
// Medium

// Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.


// Example 1:

// Input: grid = [
//   ["1","1","1","1","0"],
//   ["1","1","0","1","0"],
//   ["1","1","0","0","0"],
//   ["0","0","0","0","0"]
// ]
// Output: 1
// Example 2:

// Input: grid = [
//   ["1","1","0","0","0"],
//   ["1","1","0","0","0"],
//   ["0","0","1","0","0"],
//   ["0","0","0","1","1"]
// ]
// Output: 3
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] is '0' or '1'.

// Using DFS
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

// Using BFS

class Solution {
    public final int[][] directions = new int[][]{{0,1}, {0,-1}, {-1, 0}, {1, 0}};
    public final char seenChar = '.';
    public int numIslands(char[][] grid) {
        int count = 0;
        if(grid == null || grid.length == 0) return count;
        
        Queue<int[]> queue = new LinkedList<int[]>();
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == '1'){
                   count++;
                   queue.offer(new int[]{row, col}); 
                   bfs(grid, queue);
                }
            }
        }
        return count;
    }
    
     
    public void bfs(char[][] grid, Queue<int[]> queue){
        while(!queue.isEmpty()){
            int[] front = queue.poll();
            
            for(int[] dir: directions){
                int x = front[0] + dir[0];
                int y = front[1] + dir[1];
                if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1' ) continue;
                
                grid[x][y] = seenChar;
                
                queue.offer(new int[]{x,y});
            }            
        }
    }
}