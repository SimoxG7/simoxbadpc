class Solution {
  public int longestCommonSubsequence(String text1, String text2) {
    int sub = 0;
    StringBuilder sb = new StringBuilder();

    if (text1.length() > text2.length()) {
      String temp = text1;
      text1 = text2;
      text2 = temp;
    }

    for (int i = 0, j = i; i < text1.length(); i++) {
      while (j < text2.length() && text1.charAt(i) != text2.charAt(j)) {
        j++;
      }

      if (j >= text2.length()) break;
      sb.append(text2.charAt(j));
      sub++;
      j++;

    }

    System.out.println(sb.toString());

    if (sub == 0) {
      for (int i = 0; i < text2.length(); i++) {
        if (text1.contains(String.valueOf(text2.charAt(i)))) return 1;
      }
    }
    
    return sub;
  }
}