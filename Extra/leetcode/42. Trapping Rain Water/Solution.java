class Solution { 
  //two pointers?
  public int trap(int[] height) {
    if (height.length <= 2) return 0;
    int n = height.length;
    int maxLeft = height[0];
    int maxRight = height[n - 1];
    int left = 1;
    int right = n - 2;
    int res = 0;
    while (left <= right) {
      if (maxLeft < maxRight) {
        if (height[left] > maxLeft)
          maxLeft = height[left];
        else
          res += maxLeft - height[left];
        left += 1;
      } else {
        if (height[right] > maxRight)
          maxRight = height[right];
        else
          res += maxRight - height[right];
        right -= 1;
      }
    }
    return res;
  }
}