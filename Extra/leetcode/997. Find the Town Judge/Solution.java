class Solution {
  public int findJudge(int N, int[][] trust) {
    if (N < 1)
      return -1;
    int[] in = new int[N + 1];
    int[] out = new int[N + 1];
    for (int[] tr : trust) {
      ++in[tr[1]];
      ++out[tr[0]];
    }
    for (int i = 1; i < N + 1; i++) {
      if (in[i] == N - 1 && out[i] == 0)
        return i;
    }
    return -1;
  }
}