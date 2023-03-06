class Solution {
  public void nextPermutation(int[] nums) {
    int n = nums.length;
    int index = n-2;
    
    while(index >= 0 && nums[index] >= nums[index+1]) {
      index--;
    }
    
    if (index >= 0) {
      int last = n-1;
      while(last >= 0 && nums[index] >= nums[last]) {
        last--;
      }
      int temp = nums[last];
      nums[last] = nums[index];
      nums[index] = temp;
    }

    int end = n-1;
    int start = index+1;
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }
}