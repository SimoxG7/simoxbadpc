int climbStairs(int n) {
  int first = 0;
  int second = 1;

  //fibonacci
  while(n-- > 0) {
    int temp = first + second;
    first = second;
    second = temp;
  }

  return second;
}
