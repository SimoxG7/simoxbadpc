class Solution {
  public int shipWithinDays(int[] weights, int days) {
    //keep track of max weight, ship needs to hold at least that
    int max = 0; 
    int sum = 0;
    for (int i = 0; i < weights.length; i++) {
      int curr = weights[i];
      max = Math.max(max, curr);
      sum += curr;
    }
    //the answer is beetwen [max, sum]
    int start = max, end = sum;
    while (start < end) {
      int n = start + (end - start)/2;
      if (trasportableInDays(weights, n, days)) {
        end = n;
      } else {
        start = n+1;
      }
    }
    return start;
  }

  private boolean trasportableInDays(int[] weights, int n, int days) {
    int daysneeded = 1;
    int currsum = 0;
    for (int i = 0; i < weights.length; i++) {
      int curr = weights[i];
      currsum += curr;
      if (currsum > n) {
        currsum = curr;
        daysneeded++;
      }
    }
    return daysneeded <= days;
  } 
}