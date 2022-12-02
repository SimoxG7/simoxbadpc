/**
 * Solution with expansion around center
 */
public class Solution {

  private int expandAroundCenter(String s, int sxt, int dxt) {
    int sx = sxt;
    int dx = dxt;

    while(sx >= 0 && dx < s.length() && s.charAt(sx) == s.charAt(dx)) {
      sx--;
      dx++;
    }
    return dx - sx -1;
  }

  public String longestPalindrome(String s) {
    int start = 0;
    int end = 0;

    for (int i = 0; i < s.length(); i++) {
      int len1 = expandAroundCenter(s, i, i);
      int len2 = expandAroundCenter(s, i, i+1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1)/2;
        end = i + len/2;
      }
    }
    return s.substring(start, end + 1);
  }
}


// class Solution {
//   private boolean isPalindrome(String s) {
//     int len = s.length();
//     for (int i = 0; i < len/2; i++) {
//       if (s.charAt(i) != s.charAt(len-i-1)) return false;
//     }
//     return true;
//   }
  
//   public String longestPalindrome(String s) {
//     String longest = "";
//     int longestsize = 0;

//     //if (s.length() == 1) return s;

//     for (int i = 0; i < s.length(); i++) {
//       if (s.length() - i < longestsize) break;
//       for (int j = s.length(); j >= i; j--) {
//         String newstring = s.substring(i, j);
//         if (isPalindrome(newstring) && (newstring.length() > longestsize)) {
//           longest = newstring;
//           longestsize = newstring.length();
//         }
//       }
//     }
//     return longest;
//   }
// }