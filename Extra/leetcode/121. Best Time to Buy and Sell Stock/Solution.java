class Solution {
  public int maxProfit(int[] prices) {
    int profit = 0;
    int currval = prices[0];
    
    for (int i = 1; i < prices.length; i++) {
      currval = Math.min(currval, prices[i]);
      profit = Math.max(prices[i]-currval, profit);
    }

    return profit;
  }
}