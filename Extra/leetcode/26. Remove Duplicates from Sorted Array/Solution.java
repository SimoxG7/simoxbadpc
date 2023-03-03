class Solution {
  public int removeDuplicates(int[] nums) {
    int putIndex = 0;
    int n = nums.length;

    if (n == 0 || nums == null) return 0;
    else if (n == 1) return 1;

    int prev = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      if (nums[i] != prev) {
        nums[putIndex++] = nums[i];
        prev = nums[i];
      }
    }
    return putIndex;
  }
}