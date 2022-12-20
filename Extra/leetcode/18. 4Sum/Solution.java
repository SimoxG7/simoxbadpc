import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();

    // pointers to left and to right
    int left = 0;
    int right = nums.length - 1;
    int innerleft = left + 1;
    int innerright = right - 1;

    int a, b, c, d;

    // tofix
    while (innerleft < innerright) {
      a = nums[left];
      b = nums[innerleft];
      c = nums[innerright];
      d = nums[right];

      //System.out.println(List.of(a, b, c, d));

      if ((a + b + c + d) == target) {
        //System.out.println("In equal");
        List<Integer> part = List.of(a, b, c, d);
        if (!res.contains(part))
          res.add(part);
        if (Math.abs(a + b) - Math.abs(c + d) < target) {
          if (innerleft < innerright - 1)
            innerleft++;
          else {
            left++;
            innerleft = left + 1;
          }
        } else {
          if (innerleft < innerright - 1)
            innerright++;
          else {
            right--;
            innerright = right + 1;
          }
        }
      } else if (target - (a + b + c + d) > 0) {
        //System.out.println("In inc left");
        if (innerleft < innerright - 1)
          innerleft++;
        else {
          left++;
          innerleft = left + 1;
        }
      } else {
        //System.out.println("In dec right");
        if (innerleft < innerright - 1) //fix this, needs to work even if reaching the end
          innerright--;
        else {
          right--;
          innerright = right - 1;
        }
      }
    }
    return res;
  }
}


/* last working sub
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();

    // pointers to left and to right
    int left = 0;
    int right = nums.length - 1;
    int innerleft = left + 1;
    int innerright = right - 1;

    int a, b, c, d;

    // tofix
    while (innerleft < innerright) {
      a = nums[left];
      b = nums[innerleft];
      c = nums[innerright];
      d = nums[right];

      System.out.println(List.of(a, b, c, d));

      if ((a + b + c + d) == target) {
        System.out.println("In equal");
        List<Integer> part = List.of(a, b, c, d);
        if (!res.contains(part))
          res.add(part);
        // if (innerleft < innerright - 1)
        // innerleft++;
        // else {
        // left++;
        // innerleft = left + 1;
        // }
        if (Math.abs(a + b) - Math.abs(c + d) > target) {
          if (innerleft < innerright - 1)
            innerleft++;
          else {
            left++;
            innerleft = left + 1;
          }
        } else {
          if (innerleft < innerright - 1)
            innerleft++;
          else {
            left++;
            innerleft = left + 1;
          }
        }
      } else if (target - (a + b + c + d) > 0) {
        System.out.println("In inc left");
        if (innerleft < innerright - 1)
          innerleft++;
        else {
          left++;
          innerleft = left + 1;
        }
      } else {
        System.out.println("In dec right");
        if (innerleft < innerright - 1)
          innerright--;
        else {
          right--;
          innerright = right - 1;
        }
      }
    }
    return res;
  }
}
*/