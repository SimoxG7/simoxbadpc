class AnotherSolution {

    public String convert(String s, int numRows) {
  
      if (numRows >= s.length() || numRows == 1) return s;
      
      int cont = 0;
      StringBuilder res = new StringBuilder();
      int i = 0;
      int j = 0;
      int len = s.length()/2 + 1;
      int matrix[][] = new int[len][numRows];
  
      while (cont != s.length()) { //this is O(n)
        if (j == numRows -1) {
          while (j != 0) {
            if (cont == s.length()) break;
            matrix[i++][j--] = s.charAt(cont++);
          }
        } else {
          matrix[i][j++] = s.charAt(cont++);
        }
      }
  
      for (int h = 0; h < numRows; h++) { //this is O(n * m)
        for (int k = 0; k < len; k++) {
          if (matrix[k][h] != 0) res.append(Character.toChars(matrix[k][h]));
        }
      }
  
      return res.toString();
    }
  }