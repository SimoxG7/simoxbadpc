import java.util.ArrayList;
import java.util.List;

public class Solution {

  public String frequencySort(String s) {
  
    List<Character> lst = new ArrayList<>();
    int occ[] = new int[62];

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      lst.add(c);
      
      if (Character.isLowerCase(c)) {
        occ[c - 'a']++;
      } else if (Character.isUpperCase(c)) {
        occ[c - 'A' + 26]++;
      } else {
        occ[c - '0' + 52]++;
      }
    }

    //System.out.println(Arrays.toString(occ));

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int max = 0;
      int index = 0;
      for (int j = 0; j < occ.length; j++) {
        if (occ[j] > max) {
          max = occ[j];
          index = j;
        }        
      }
      occ[index] = 0;
        char c;
        if (index >= 52) {
          c = (char) (index + '0' - 52);
        } else if (index >= 26) {
          c = (char) (index + 'A' - 26);
        } else {
          c = (char) (index + 'a');
        }
        for (int k = 0; k < max; k++) {
          sb.append(c);
        }
        if (max == 0) break;
        max = 0;
    }

    return sb.toString();
  }
}



  /* 
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
  */

