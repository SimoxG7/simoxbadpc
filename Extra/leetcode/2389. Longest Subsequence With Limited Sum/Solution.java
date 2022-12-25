import java.util.Arrays;

class Solution {
  public int[] answerQueries(int[] nums, int[] queries) {

    Arrays.sort(nums);
    int[] res = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int count = 0; 
      int val = queries[i];

      for (int j = 0; j < nums.length; j++) {
        if (val >= nums[j]) {
          count++;
          val -= nums[j];
        } else break;
      }

      res[i] = count;
    }
    return res;
  }
}