import java.util.Stack;

/**
 * Solution
 */
public class Solution {
  public int[] dailyTemperatures(int[] temperatures) {
    int[] ans = new int[temperatures.length];
    Stack<Integer> stk = new Stack<>();
    for (int i = 0; i < temperatures.length; i++) {
      if (stk.isEmpty())
        stk.push(i);
      else {
        while (!stk.isEmpty() && temperatures[i] > temperatures[stk.peek()]) {
          int o = stk.pop();
          ans[o] = i - o;
        }
        stk.push(i);
      }
    }
    return ans;
  }
}

// //O(n^2)
// class Solution {
// public int[] dailyTemperatures(int[] temperatures) {
// for (int i = 0; i < temperatures.length; i++) {
// int cont = 0;
// for (int j = i; j < temperatures.length; j++) {
// if (temperatures[i] >= temperatures[j]) {
// if (j == temperatures.length-1) cont = 0;
// else cont++;
// } else break;
// }
// temperatures[i] = cont;
// }
// return temperatures;
// }
// }
