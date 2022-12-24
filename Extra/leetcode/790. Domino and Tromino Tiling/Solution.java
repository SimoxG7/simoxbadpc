import java.util.ArrayList;
import java.util.List;

/* Pattern:
 * 1 - 2 * 0 = 0
 * 2 - 2 * 1 = 0
 * 5 - 2 * 2 = 1
 * 11 - 2 * 5 = 1
 * 24 - 2 * 11 = 2
 * 53 - 2 * 24 = 5 
 * 117 - 2 * 53 = 11
 * 
 */

class Solution {
  public int numTilings(int n) {
    List<Long> lst = new ArrayList<>(List.of(0L,1L,2L,5L));
    if (n < 4) {
      return Math.toIntExact(lst.get(n));
    }
    while (lst.size() <= n) {
      lst.add(((2 * lst.get(lst.size() - 1)) + lst.get(lst.size() - 3)) % 1000000007);
    }
    System.out.println(lst.get(lst.size()-1));
    System.out.println(lst.get(lst.size()-2));
    return Math.toIntExact(lst.get(lst.size()-1)) % 1000000007;
  }
}