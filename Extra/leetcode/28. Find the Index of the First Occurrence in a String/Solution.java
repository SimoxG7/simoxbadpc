class Solution {
  public int strStr(String haystack, String needle) {
    int indexNeedle = 0;
    int n = needle.length();
    int needleStart = 0;
    for (int i = 0; i < haystack.length(); i++) {
      if (haystack.charAt(i) == needle.charAt(indexNeedle++)) {
        if (indexNeedle == n) return needleStart;
      } else {
        indexNeedle = 0;
        i = needleStart;
        needleStart = i+1;
      }
    }
    return -1;
  }
}