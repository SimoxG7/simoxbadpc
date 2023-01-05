class Solution {
  public int findMinArrowShots(int[][] points) {
    if (points.length == 0)
      return 0;
    java.util.Arrays.sort(points, new java.util.Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return Integer.compare(a[1], b[1]);
      }
    });

    /**
     * Two key Ideas :
     * (a) Greedy heruistic : burst all the baloons whose start is <= min(end);
     * (b) If the start > min(end), you need another arrow to burst it so increment
     * arrows and move the end farword.
     **/
    int i = 1, n = points.length, arrows = 1, arrowPos = points[0][1];
    while (i < n) {
      if (points[i][0] > arrowPos) {
        arrowPos = points[i][1];
        arrows++;
      }
      i++;
    }
    return arrows;
  }
}