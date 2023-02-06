class Solution {
  public int[] shuffle(int[] nums, int n) {
    int[] res = new int[n*2];
    int cont = 0;
    for (int i = 0; i < n; i++) {
      res[cont++] = nums[i];
      res[cont++] = nums[i+n];
    }
    return res;
  }
}