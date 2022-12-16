import java.util.Arrays;

public class Solution {
  public int threeSumClosest(int[] nums, int target) {

    Arrays.sort(nums);
    int res = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < nums.length - 2; i++) {

      int left = i + 1, right = nums.length - 1;
      while (left < right) {
        int sum = nums[left] + nums[right] + nums[i];
        if (Math.abs(target - sum) < Math.abs(target - res)) res = sum;
        if (sum == target) return sum;
        if (sum > target) right--;
        else left++;
      }

    }
    return res;
  }
}

// class Solution {
// public int threeSumClosest(int[] nums, int target) {
// Arrays.sort(nums);
// int len = nums.length;
// int newdiff = Integer.MAX_VALUE;
// int res = 0;

// for (int i = 0; i < len - 2; i++) {
// int sum = nums[i] + nums[i+1] + nums[i+2];
// if (Math.abs(sum - target) < newdiff) {
// newdiff = Math.abs(sum - target);
// res = sum;
// System.out.println(nums[i] + " " + nums[i+1] + " " + nums[i+2] + " " +
// newdiff);
// }
// }
// return res;
// }
// }