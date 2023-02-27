class Solution {
  
  public class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
      this.val = false;
      this.isLeaf = false;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = topLeft;
      this.topRight = topRight;
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
    }
  }

  public Node construct(int[][] grid) {
    return recConstruct(grid);
  }

  public Node recConstruct(int[][] grid) {
    if (hasSameValues(grid)) {
      boolean val = grid[0][0] == 1;
      return new Node(val, true);
    } else {
      int n = grid[0].length;
      int h = n/2;
      int[][] tl = subMatrix(grid, 0, 0, h);
      int[][] tr = subMatrix(grid, 0, h, h);
      int[][] bl = subMatrix(grid, h, 0, h);
      int[][] br = subMatrix(grid, h, h, h);

      return new Node(true, false, recConstruct(tl), recConstruct(tr), recConstruct(bl), recConstruct(br)); 
    }
  }

  public boolean hasSameValues(int[][] grid) {
    int zero = grid[0][0];
    int n = grid[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] != zero) return false;
      }
    }
    return true;
  }

  public int[][] subMatrix(int[][] grid, int xstart, int ystart, int len) {
    int[][] res = new int[len][len];
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        res[i][j] = grid[xstart+i][ystart+j];
      }
    }
    return res;
  }
}