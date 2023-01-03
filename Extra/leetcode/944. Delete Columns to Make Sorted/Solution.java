class Solution {
  public int minDeletionSize(String[] strs) {
    int len = strs[0].length();
    int res = 0;

    for (int i = 0; i < len; i++) {
      for (int j = 1; j < strs.length; j++) {
        if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
          res++;
          break;
        }
      }
    }

    return res;
  }
}