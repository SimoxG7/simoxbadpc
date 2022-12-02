import java.util.ArrayList;
import java.util.List;

class Solution {
  public int lengthOfLongestSubstring(String s) {
    int best = 0;
    int curr = 0;
    
    for (int i = 0; i < s.length(); i++) {
      List<Character> lst = new ArrayList<>();
      String newstring = s.substring(i);
      for (int j = 0; j < newstring.length(); j++) {
        char c = newstring.charAt(j);
        if (lst.contains(c)) {
          if (best < curr) best = curr;
          curr = 0;
          break;
        } else {
          lst.add(c);
          curr++;
        }
      }
      if (curr > best) best = curr;
      curr = 0;
    } 
    return best;
  }
}