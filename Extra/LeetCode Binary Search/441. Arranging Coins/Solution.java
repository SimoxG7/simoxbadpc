class Solution {
  public int arrangeCoins(int n) {
    int low = 1;
    int high = n;
    int ans = 0;
    while (low <= high) {
      long mid = (high - low) / 2 + low;
      long sum = (mid) * (mid + 1) / 2;
      if (sum > n) {
        high = (int) (mid - 1);
      } else {
        low = (int) (mid + 1);
        ans = (int) (mid);
      }
    }
    return ans;
  }
}