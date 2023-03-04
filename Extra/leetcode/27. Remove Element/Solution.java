class Solution {
  public int removeElement(int[] nums, int val) {
    int currIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[currIndex++] = nums[i];
      }
    }
    return currIndex;
  }
}