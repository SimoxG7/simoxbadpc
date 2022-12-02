class Solution {
  private boolean isPalindrome(String s) {
    int len = s.length();
    for (int i = 0; i < len/2; i++) {
      if (s.charAt(i) != s.charAt(len-i-1)) return false;
    }
    return true;
  }
  
  public String longestPalindrome(String s) {
    String longest = "";
    int longestsize = 0;

    //if (s.length() == 1) return s;

    for (int i = 0; i < s.length(); i++) {
      if (s.length() - i < longestsize) break;
      for (int j = s.length(); j >= i; j--) {
        String newstring = s.substring(i, j);
        if (isPalindrome(newstring) && (newstring.length() > longestsize)) {
          longest = newstring;
          longestsize = newstring.length();
        }
      }
    }
    return longest;
  }
}