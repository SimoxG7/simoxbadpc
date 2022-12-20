import java.util.List;
import java.util.Stack;

class Solution {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] seen = new boolean[rooms.size()];
    seen[0] = true;
    Stack<Integer> stack = new Stack<>();
    stack.push(0);

    while (!stack.isEmpty()) {
      // Get the next key
      int nextkey = stack.pop();
      // For every key in room nextkey
      for (int nei : rooms.get(nextkey)) 
        // if hasn't been used yet
        if (!seen[nei]) { 
          seen[nei] = true; 
          stack.push(nei); 
        }
    }

    for (boolean v : seen)
      if (!v)
        return false;
    return true;
  }
}

// naive solution

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// class Solution {
// public boolean canVisitAllRooms(List<List<Integer>> rooms) {

// // boolean[] visited = new boolean[rooms.size()];
// boolean[] gotkey = new boolean[rooms.size()];

// boolean changed = true;
// gotkey[0] = true;

// while (changed) {
// changed = false;
// for (int i = 0; i < gotkey.length; i++) {
// if (gotkey[i]) {
// for (Integer keys : rooms.get(i)) {
// if (gotkey[keys] == false) changed = true;
// gotkey[keys] = true;
// }
// }
// }
// }

// // for (List<Integer> keys : rooms) {
// // for (Integer room : keys) {
// // visited[room] = true;
// // }
// // }

// System.out.println(Arrays.toString(gotkey));

// for (int i = 0; i < gotkey.length; i++) {
// if (gotkey[i] == false)
// return false;
// }
// return true;
// }
// }