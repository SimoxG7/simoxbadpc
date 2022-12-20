import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution
 */
public class Solution {
  private Map<Character, String> map = new HashMap<>();

  private void initMap() {
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
  }

  public List<String> letterCombinations(String digits) {
    initMap();

    if (digits.length() == 0) {
      return new ArrayList<String>();
    }

    List<String> result = new ArrayList<>();
    if (digits.length() == 1) {
      String s = map.get(digits.charAt(0));
      for (int i = 0; i < s.length(); i++) {
        result.add(s.charAt(i) + "");
      }
      return result;
    }

    List<String> list = letterCombinations(digits.substring(1, digits.length()));
    String s = map.get(digits.charAt(0));
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < list.size(); j++) {
        result.add(s.charAt(i) + list.get(j));
      }
    }
    return result;
  }
}
