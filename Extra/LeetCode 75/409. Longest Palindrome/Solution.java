import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestPalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int cont = 0;
    boolean notpaired = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c)+1);
      } else {
        map.put(c, 1);
      }
    }

    for (Character c : map.keySet()) {
      int temp = map.get(c);
      cont += 2 * (temp/2);
      temp %= 2;
      if (temp == 1) notpaired = true;
    }
    if (notpaired) return cont + 1;
    return cont;
  }
}