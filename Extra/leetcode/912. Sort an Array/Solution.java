import java.util.Arrays;

public class Solution {
  public static int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length-1);
    return nums;
  }

  public static void mergeSort(int[] nums, int start, int end) {
    if (start < end) {
      //split
      int mid = (end - start)/2 + start;
      //recursive sort 1 e 2
      mergeSort(nums, start, mid);
      mergeSort(nums, mid + 1, end);
      merge(nums, start, mid, end);
    }
  }

  public static void merge(int[] nums, int start, int mid, int end) {
    int leftsize = mid + 1 - start;
    int rightsize = end - mid;
    int[] left = new int[leftsize];
    int[] right = new int[rightsize];
    for (int i = 0; i < leftsize; i++) {
      left[i] = nums[start + i];
    }
    for (int i = 0; i < rightsize; i++) {
      right[i] = nums[mid + 1 + i];
    }
    int i = 0, j = 0, k = start;
    while (i < leftsize || j < rightsize) {
      if (j == rightsize || i < leftsize && left[i] < right[j]) {
        nums[k++] = left[i++];
      } else {
        nums[k++] = right[j++];
      }
    }
  }

  public static void main(String[] args) {
    int[] array = {5,4,3,2,1,0};
    Solution.sortArray(array);
    System.out.println(Arrays.toString(array));
  }
} 