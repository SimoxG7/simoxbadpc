class Solution {
  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length-1;
    int mid = right - ((right - left)/2);

    while (left < right) {
      if (target == nums[mid]) return mid;
      else if (target > nums[mid]) {
        left = mid;
      } else {
        right = mid-1;
      }
      mid = right - ((right - left)/2);
    }

    if (nums[mid] == target) return mid;
    return -1;
  }
}