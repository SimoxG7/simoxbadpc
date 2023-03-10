import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isIsomorphic(String s, String t) {
    int n = s.length();
    if (n != t.length()) return false;
    Map<Character, Character> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      Character schar = s.charAt(i);
      Character tchar = t.charAt(i);
      if (map.containsKey(schar)) {
        if (map.get(schar) != tchar) return false;
      } else {
        if (map.containsValue(tchar)) return false;
        else map.put(schar, tchar);
      }
    }
    return true;
  }
}