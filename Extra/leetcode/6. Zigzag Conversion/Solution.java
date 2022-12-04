class Solution {

  public String convert(String s, int numRows) {

    if (numRows >= s.length() || numRows == 1) return s;
    
    int cont = 0;
    StringBuilder res = new StringBuilder();
    int i = 0;
    int j = 0;
    int matrix[][] = new int[s.length()][numRows];

    while (cont != s.length()) {
      if (j == numRows -1) {
        while (j != 0) {
          if (cont == s.length()) break;
          matrix[i++][j--] = s.charAt(cont++);
        }
      } else {
        matrix[i][j++] = s.charAt(cont++);
      }
    }

    for (int h = 0; h < numRows; h++) {
      for (int k = 0; k < s.length(); k++) {
        if (matrix[k][h] != 0) res.append(Character.toChars(matrix[k][h]));
      }
    }

    return res.toString();
  }
}
