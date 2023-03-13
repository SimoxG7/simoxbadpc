class Solution {
  public char nextGreatestLetter(char[] letters, char target) {
    int n = letters.length;
    int left = 0;
    int right = n-1;

    while (left <= right) {
      int mid = right - (right-left)/2;
      char temp = letters[mid];
      if (target < temp) {
        right = mid-1;
      } else {
        left = mid+1;
      }
    }
    return letters[left%n];
  }
}