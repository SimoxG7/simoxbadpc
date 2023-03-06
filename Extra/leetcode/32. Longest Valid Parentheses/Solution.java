import java.util.Stack;

class Solution {
  public int longestValidParentheses(String s) {

    Stack<Integer> stack = new Stack<>();
    if (!s.isEmpty()) stack.push(0);
    for (int i = 1; i < s.length(); i++) {
      if (stack.isEmpty()) stack.push(i);
      else if (s.charAt(stack.peek()) == '(' && s.charAt(i) == ')') {
        stack.pop();
      } else stack.push(i);
    }

    if (stack.isEmpty()) return s.length();

    if (stack.size() == 1 && s.length() != 1 && ((stack.peek() == 0) || (stack.peek() == s.length() - 1))) return s.length() - 1;

    int max = 0;
    int len = s.length() - 1;
    while (!stack.isEmpty()) {
      int a = stack.pop();
      max = Math.max(len - a, max);
      len = a - 1;
    }

    return Math.max(max, len+1);
  }
}

// class Solution {
// public int longestValidParentheses(String s) {
// int max = 0;
// for (int i = 0; i < s.length(); i++) {
// Stack<Character> stack = new Stack<>();
// int curr = 0;
// for (int j = i; j < s.length(); j++) {
// if (stack.isEmpty()) {
// max = Math.max(curr, max);
// }
// //System.out.println("i: " + i + " - j: " + j + " - max: " + max + " - curr:
// " + curr + " " + stack.isEmpty());
// if (s.charAt(j) == '(') {
// stack.push(s.charAt(j));
// curr++;
// } else if (s.charAt(j) == ')') {
// if (!stack.isEmpty() && stack.pop() == '(') {
// curr++;
// } else {
// break;
// }
// }
// }
// if (stack.isEmpty()) {
// max = Math.max(curr, max);
// }
// }
// return max;
// }
// }