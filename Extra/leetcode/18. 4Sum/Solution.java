import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 0, 4);
  }

  public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();

    if (start == nums.length) {
      return res;
    }

    long average_value = target / k;

    if (nums[start] > average_value || average_value > nums[nums.length - 1]) {
      return res;
    }

    if (k == 2) {
      return twoSum(nums, target, start);
    }

    for (int i = start; i < nums.length; ++i) {
      if (i == start || nums[i - 1] != nums[i]) {
        for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
          res.add(new ArrayList<>(Arrays.asList(nums[i])));
          res.get(res.size() - 1).addAll(subset);
        }
      }
    }

    return res;
  }

  public List<List<Integer>> twoSum(int[] nums, long target, int start) {
    List<List<Integer>> res = new ArrayList<>();
    int lo = start, hi = nums.length - 1;

    while (lo < hi) {
      int currSum = nums[lo] + nums[hi];
      if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
        ++lo;
      } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
        --hi;
      } else {
        res.add(Arrays.asList(nums[lo++], nums[hi--]));
      }
    }

    return res;
  }
}

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// class Solution {
// public List<List<Integer>> fourSum(int[] nums, int target) {
// Arrays.sort(nums);
// List<List<Integer>> res = new ArrayList<>();

// // pointers to left and to right
// int left = 0;
// int right = nums.length - 1;
// int innerleft = left + 1;
// int innerright = right - 1;

// int a, b, c, d;

// // tofix
// while (left < right-2) {
// a = nums[left];
// b = nums[innerleft];
// c = nums[innerright];
// d = nums[right];

// System.out.println(List.of(a, b, c, d));
// System.out.println(List.of(left, innerleft, innerright, right));

// if ((a + b + c + d) == target) {
// System.out.println("In equal");
// List<Integer> part = List.of(a, b, c, d);
// if (!res.contains(part))
// res.add(part);
// if (innerleft == innerright-1) {
// left++;
// innerleft = left+1;
// } else {
// innerleft++;
// }
// } else if (target - (a + b + c + d) > 0) {
// System.out.println("In inc left");
// if (innerleft < innerright - 1)
// innerleft++;
// else {
// left++;
// innerleft = left + 1;
// }
// } else {
// System.out.println("In dec right");
// if (innerleft < innerright - 1) // fix this, needs to work even if reaching
// the end
// innerright--;
// else {
// right--;
// innerright = right - 1;
// }
// }
// }
// return res;
// }
// }
