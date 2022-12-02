#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

double myPow(double x, int n) {
  if (n == 1) return x;
  else if (n == -1) return 1/x;
  bool neg = false;
  if (n < 0) {
    if (n == INT_MIN && x == 1) return x;
    else if (n == INT_MIN && x == -1) return 1;
    else if (n == INT_MIN) return 0;
    neg = true;
    n *= -1;
  } else if (x == 1 || n == 0) return 1;
  double res = 1;
  if (n % 2 == 0) {
    res = myPow((x*x), n/2);
  } else {
    res = x*myPow((x*x), (n-1)/2);
  }
  if (neg) return 1/res;
  else return res;
}

int main(void)
{
  printf("%f", myPow(2, 2));
}

/* says it's too slow, but works
double myPow(double x, int n){
  if (x == 1) return 1;
  if (x == -1) {
    if (n % 2 == 0) return 1;
    else return -1;
  }
  if(n == INT_MIN) return 0;
  double res = 1;
  bool neg = false;
  if (n < 0) {
    neg = true;
    n *= -1;
  }
  while(n > 0) {
    res *= x;
    n--;
  }
  if (neg) return 1/res;
  else return res;
}
*/