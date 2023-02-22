import java.util.Stack;
class Solution {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c); 
      } else {
        if (stack.empty()) return false;
        Character check = stack.pop();
        if ((check == '(' && c != ')')
          || (check == '[' && c != ']')
          || (check == '{' && c != '}')
          ) return false;
      }
      // switch (c) {
      //   case '(':
      //   case '[':
      //   case '{':
      //     stack.push(c);
      //     break;
      //   case ')':
      //     if (stack.empty()) return false;
      //     else if (stack.pop() != '(') return false;
      //     break;
      //   case ']':
      //     if (stack.empty()) return false;
      //     else if (stack.pop() != '[') return false;
      //     break;
      //   case '}':
      //     if (stack.empty()) return false;
      //     else if (stack.pop() != '{') return false;
      //     break;
      // }
    }
    return stack.empty();
  }
}