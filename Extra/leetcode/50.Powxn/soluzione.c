#include <stdio.h>
#include <stdbool.h>

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