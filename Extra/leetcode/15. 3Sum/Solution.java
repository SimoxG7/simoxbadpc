import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < n - 2; i++) {
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      twoSum(nums, i, i + 1, n - 1, -nums[i], res);
    }

    return res;
  }

  // This helper method process the array arr from index l to index r (both
  // inclusive),
  // and check whether there exists two indices whose value summing to sum.
  // If so, add all three indices into sol
  private void twoSum(int[] arr, int temp, int l, int r, int sum, List<List<Integer>> res) {
    int left = l;
    int right = r;
    while (left < right) {
      if (arr[left] + arr[right] < sum) {
        left++;
      } else if (arr[left] + arr[right] > sum) {
        right--;
      } else {
        res.add(Arrays.asList(arr[temp], arr[left], arr[right]));
        while ((left + 1) <= arr.length - 1 && arr[left + 1] == arr[left]) {
          left++;
        }
        left++;

      }
    }
  }
}

/*
 * class Solution {
 * public List<List<Integer>> threeSum(int[] nums) {
 * int len = nums.length;
 * Arrays.sort(nums);
 * List<List<Integer>> res = new ArrayList<>();
 * for (int i = 0; i < len; i++) {
 * for (int j = i+1; j < len; j++) {
 * for (int k = j+1; k < len; k++) {
 * if (nums[i] > 0) return res;
 * else if (nums[i] + nums[j] + nums[k] == 0) {
 * List<Integer> part = new ArrayList<>();
 * part.add(nums[i]);
 * part.add(nums[j]);
 * part.add(nums[k]);
 * if (!res.contains(part)) res.add(part);
 * }
 * }
 * }
 * }
 * return res;
 * }
 * }
 */

/*
 * class Solution {
 * public List<List<Integer>> threeSum(int[] nums) {
 * int len = nums.length;
 * Arrays.sort(nums);
 * List<List<Integer>> res = new ArrayList<>();
 * for (int i = 0; i < len; i++) {
 * for (int j = i+1; j < len; j++) {
 * for (int k = j+1; k < len; k++) {
 * if (nums[i] + nums[j] + nums[k] == 0) {
 * List<Integer> part = new ArrayList<>();
 * part.add(nums[i]);
 * part.add(nums[j]);
 * part.add(nums[k]);
 * if (!res.contains(part)) res.add(part);
 * }
 * }
 * }
 * }
 * return res;
 * }
 * }
 */