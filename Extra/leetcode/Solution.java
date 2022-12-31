class Solution {

  private int numPath = 0;
  private int numEmpty = 0;
  private int startx = -1;
  private int starty = -1;
  private int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

  public int uniquePathsIII(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0)
          numEmpty++;
        if (grid[i][j] == 1) {
          startx = i;
          starty = j;
        }
      }
    }
    dfs(startx, starty, grid, 0);
    return numPath;
  }

  private void dfs(int i, int j, int[][] grid, int p) {
    for (int[] d : dir) {
      int x = i + d[0], y = j + d[1];
      if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 3) {
        if (grid[x][y] == 0) {
          grid[x][y] = 3;
          dfs(x, y, grid, p + 1);
          grid[x][y] = 0;
        }
        if (grid[x][y] == 2) {
          if (p == numEmpty) {
            numPath++;
          }
        }
      }
    }
  }
}