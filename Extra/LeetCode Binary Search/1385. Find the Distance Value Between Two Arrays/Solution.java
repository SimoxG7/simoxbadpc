class Solution {
  public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
    int res = 0;
    for (int i = 0; i < arr1.length; i++) {
      int arr1val = arr1[i];
      boolean found = false;
      for (int j = 0; j < arr2.length; j++) {
        int arr2val = arr2[j];
        if (Math.abs(arr1val - arr2val) <= d) {
          found = true;
          break;
        }
      }
      if (!found) res++;
    }
    return res;
  }
}