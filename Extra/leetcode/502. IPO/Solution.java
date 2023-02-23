import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

  public class CapitalProfit implements Comparable<CapitalProfit> {
    public int capital;
    public int profit;

    public CapitalProfit(int capital, int profit) {
      this.capital = capital;
      this.profit = profit;
    }

    @Override
    public int compareTo(Solution.CapitalProfit other) {
      return this.capital - other.capital;
    }
  }

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int res = w;
    int len = profits.length;
    CapitalProfit[] projects = new CapitalProfit[len];
    for (int i = 0; i < len; i++) {
      projects[i] = new CapitalProfit(capital[i], profits[i]);
    }

    Arrays.sort(projects);
    PriorityQueue<Integer> priority = new PriorityQueue<Integer>(Collections.reverseOrder());
    int j = 0;
    for (int i = 0; i < k; i++) {
      while (j < len && projects[j].capital <= res) {
        priority.add(projects[j++].profit);
      }
      if (priority.isEmpty()) {
        break;
      }
      res += priority.poll();
    }
    return res;
  }
}