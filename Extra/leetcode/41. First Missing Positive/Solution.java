class Solution {
  public int firstMissingPositive(int[] nums) {
    int missing = 0, todelete = nums.length;
    
    while (missing != todelete) {
      int target = nums[missing] - 1;

      if (missing == target)
        missing++;
      else if (target < 0 || target >= nums.length || nums[target] == nums[missing])
        swap(nums, missing, --todelete);
      else
        swap(nums, missing, target);
    }
    return missing + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}