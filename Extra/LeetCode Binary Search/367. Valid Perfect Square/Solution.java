class Solution {
  public boolean isPerfectSquare(int num) {
    int left = 1, right = num;
    while (left <= right) {
      long mid = (left + right) / 2;
      if (mid * mid == num) {
        return true;
      } else if (mid * mid < num) {
        left = (int) mid + 1;
      } else {
        right = (int) mid - 1;
      }
    }
    return false;
  }
}