import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution {

  private Map<Character, Integer> map = new LinkedHashMap<>();
  private Map<Integer, Character> pam = new LinkedHashMap<>();
  

  public String frequencySort(String s) {
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }
    System.out.println(map);
    
    //fix here
    System.out.println(map.keySet());
    for (Character k : map.keySet()) {
      //ofc, can't use another map...
      pam.put(map.get(k), k);
    }
    System.out.println(pam);

    List<Integer> lst = new ArrayList<>(pam.keySet());
    
    Collections.sort(lst);
    Collections.reverse(lst);

    StringBuilder sb = new StringBuilder();
    System.out.println(lst);

    for (Integer i : lst) {
      char c = pam.get(i);
      for (int j = 0; j < i; j++) {
        sb.append(c);
      }
    }

    return sb.toString();
  }
}
