class Solution {
  public int findKthPositive(int[] arr, int k) {
    int start = 1;
    int diff = 0;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      if (arr[i] != start++) {
        diff++;
        i--;
      }
      if (diff == k) return start-1;
    }
    return n + k;
  }
}