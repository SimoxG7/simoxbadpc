class Solution {
  public int longestCommonSubsequence(String text1, String text2) {
    if (text1.length() < text2.length()) {
      return calculate(text1, text2);
    }
    return calculate(text2, text1);
  }

  public int calculate(String s1, String s2) {
    int[][] M = new int[2][s1.length() + 1];
    // row represnets the length of s2, col represents the length of s1

    for (int i = 1; i <= s2.length(); i++) {
      // base case
      M[i % 2][0] = 0;
      for (int j = 1; j <= s1.length(); j++) {
        if (s1.charAt(j - 1) == s2.charAt(i - 1)) {
          M[i % 2][j] = M[(i - 1) % 2][j - 1] + 1;
        } else {
          M[i % 2][j] = Math.max(M[(i - 1) % 2][j - 1],
              Math.max(M[(i - 1) % 2][j], M[i % 2][j - 1]));
        }
      }
    }
    return M[s2.length() % 2][s1.length()];
  }

  // public int calculate(String text1, String text2) {
  // int sub = 0;
  // StringBuilder sb = new StringBuilder();

  // int j = 0;
  // int len = text1.length();

  // for (int i = 0; i < text2.length(); i++) {
  // if (text1.substring(j, len).contains(String.valueOf(text2.charAt(i)))) {
  // sb.append(text2.charAt(i));
  // j += text1.substring(j, len).indexOf(String.valueOf(text2.charAt(i))) +1;
  // sub++;
  // }
  // }

  // System.out.println(sb.toString());
  // return sub;
  // }
}
