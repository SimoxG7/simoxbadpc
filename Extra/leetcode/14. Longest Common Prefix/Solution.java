class Solution {
  public String longestCommonPrefix(String[] strs) {
    StringBuilder common = new StringBuilder();

    String first = strs[0];

    for (int i = 0; i < first.length(); i++) {
      char c = first.charAt(i);

      for (int j = 1; j < strs.length; j++) {
        //System.out.println(strs[j].length() + " " + i + " " + strs[j].charAt(i) + " " + c);
        if (strs[j].length() <= i || strs[j].charAt(i) != c) return common.toString();
      }

      common.append(first.charAt(i));
    }
    return common.toString();
  }
}

