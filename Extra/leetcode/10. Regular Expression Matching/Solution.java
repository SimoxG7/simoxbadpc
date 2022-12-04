/**
 * Solution
 */
public class Solution {
  public boolean isMatch(String s, String p) {
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];

    dp[0][0] = true;
    
    for (int i = 0; i < p.length(); i++) {
      if (p.charAt(i) == '*' && dp[0][i-1]) {
        dp[0][i+1] = true;
      }
    }
    for (int i = 0 ; i < s.length(); i++) {
      for (int j = 0; j < p.length(); j++) {
        if (p.charAt(j) == '.') {
          dp[i+1][j+1] = dp[i][j];
        }
        if (p.charAt(j) == s.charAt(i)) {
          dp[i+1][j+1] = dp[i][j];
        }
        if (p.charAt(j) == '*') {
          if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
            dp[i+1][j+1] = dp[i+1][j-1];
          } else {
            dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
          }
        }
      }
    }
    return dp[s.length()][p.length()];
  }
  
}



/* 
class Solution {
  public boolean isMatch(String s, String p) {
    char prec = 0;
    if (s.length() != p.length()) return false;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isAlphabetic(p.charAt(i))) {
        if (s.charAt(i) != p.charAt(i)) return false;
        prec = p.charAt(i);
      } else if (p.charAt(i) == '.') prec = '.';
      else if (p.charAt(i) == '*') {
        if (prec == '.') continue;
        else if (s.charAt(i) != prec) return false;
      } else return false;
    }
    return true;
  }
}
*/