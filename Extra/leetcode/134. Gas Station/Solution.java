class Solution {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int candidate = 0;
    int count = 0;
    int sum = 0;
    for (int i = 0; i < gas.length * 2; i++) {
      sum += gas[i % gas.length] - cost[i % gas.length];
      if (sum < 0) {
        candidate = i + 1;
        sum = 0;
        count = 0;
      } else {
        count++;
        if (count >= gas.length) {
          return candidate;
        }
      }
    }
    return -1;
  }
}