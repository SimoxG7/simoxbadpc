class Solution {
  public int totalFruit(int[] fruits) {
    int size = fruits.length;
    int max = 0;
    for (int i = 0; i < size; i++) {
      if (size - i < max) return max; //cuts some calculation
      int newmax = 0;
      int t1 = -1, t2 = -1;
      for (int j = i; j < size; j++) {
        if (t1 == -1) {
          t1 = fruits[j];
        }
        else if (t2 == -1 && fruits[j] != t1) {
          t2 = fruits[j];
        }
        else if (fruits[j] != t1 && fruits[j] != t2) break;
        newmax++;
      }
      max = Math.max(max, newmax);
    }
    return max;
  }
} 