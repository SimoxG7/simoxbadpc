import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int minJumps(int[] arr) {
    int n = arr.length;
    if (n == 1) return 0;

    Map<Integer, List<Integer>> indexes = new HashMap<>();
    for (int i = 0; i < n; i++) {
      /*
      if (!indexes.containsKey(arr[i])) {
        List<Integer> t = new ArrayList<>();
        t.add(i);
        indexes.put(arr[i], t);
      } else {
        List<Integer> t = indexes.get(arr[i]);
        t.add(i);
        indexes.put(arr[i], t);
      }
      */
      indexes.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
    }

    Queue<Integer> storeIndex = new LinkedList<>();
    boolean[] visited = new boolean[n];
    storeIndex.offer(0);
    visited[0] = true;
    int steps = 0;

    while (!storeIndex.isEmpty()) {
      int size = storeIndex.size();
      while (size-- > 0) {
        int currIndex = storeIndex.poll();
        if (currIndex == n - 1)
          return steps;

        List<Integer> jumpNextindexes = indexes.get(arr[currIndex]);
        jumpNextindexes.add(currIndex - 1);
        jumpNextindexes.add(currIndex + 1);
        for (int jumpNextIndex : jumpNextindexes) {
          if (jumpNextIndex >= 0 && jumpNextIndex < n && !visited[jumpNextIndex]) {
            storeIndex.offer(jumpNextIndex);
            visited[jumpNextIndex] = true;
          }
        }
        jumpNextindexes.clear();
      }
      steps++;
    }
    return -1;
  }
}