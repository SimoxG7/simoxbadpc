class Solution {
  public int[] searchRange(int[] nums, int target) {
    int first = search(nums, target - 1);
    int last = search(nums, target);

    if (first == last) return new int[] {-1, -1};

    return new int[] {first + 1, last};
  }

  private int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = right - (right - left) / 2;
      if (nums[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return right;
  }
}
