
class Solution {
  public int singleNonDuplicate(int[] nums) {
    //i can binary search this:
    //if it is before the split, then the 2 equal values are at i and i+1, with i being odd, i+1 being even
    //if it is after the split, then the 2 equal vlaues are at i and i+1, with i being even and i+1 being odd 
    int n; 
    int start = 0; 
    int end = nums.length;
    if (end <= 1) return nums[end-1];
    else if (nums[end-1] != nums[end-2]) return nums[end-1]; //last
    else if (nums[start] != nums[start+1]) return nums[start]; //first

    while (true) {
      n = start + (end - start)/2; 
      if ((nums[n] != nums[n-1]) && (nums[n] != nums[n+1])) return nums[n];
      else if ((nums[n] == nums[n+1] && n % 2 == 0) || (nums[n] == nums[n-1] && n % 2 != 0))  {
        //is after
        start = n+1;
      } else {
        //is before
        end = n-1;
      }
    }
  }
}