import java.util.Stack;

class Solution {
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String s : tokens) {
      if (s.equals("+"))
        stack.push(stack.pop() + stack.pop());
      else if (s.equals("-"))
        stack.push(-stack.pop() + stack.pop());
      else if (s.equals("/"))
        stack.push((int) (1D / stack.pop() * stack.pop()));
      else if (s.equals("*"))
        stack.push(stack.pop() * stack.pop());
      else
        stack.push(Integer.valueOf(s));
    }

    return stack.pop();
  }
}
