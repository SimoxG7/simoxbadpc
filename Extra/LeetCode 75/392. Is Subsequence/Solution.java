class Solution {
  public boolean isSubsequence(String s, String t) {
    int lens = s.length();
    int lent = t.length();
    int sindex = 0;

    if (lens == 0) return true;

    for (int i = 0; i < lent; i++) {
      char tchar = t.charAt(i);
      if (s.charAt(sindex) == tchar) sindex++;
      if (sindex == lens) return true;
    }
    return false;
  }
}