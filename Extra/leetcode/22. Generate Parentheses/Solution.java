import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    generateRecursive(n, 0, 0, res, sb);
    return res;
  }

  public void generateRecursive(int n, int open, int close, List<String> res, StringBuilder sb) {
    if (sb.length() == n * 2) { //complete
      res.add(sb.toString());
      return;
    }
    if (open < n) {
      sb.append("(");
      generateRecursive(n, open + 1, close, res, sb);
      sb.deleteCharAt(sb.length() - 1);
    }
    if (close < open) {
      sb.append(")");
      generateRecursive(n, open, close + 1, res, sb);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}