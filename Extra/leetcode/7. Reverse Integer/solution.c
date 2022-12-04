#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

int len(int x) {
  int len = 0;
  if (x == 0) return 1;
  while (x > 0) {
    x /= 10;
    len++;
  }
  return len;
}

int power(int n, int e) {
  // printf("here\n");
  if (e <= 0) return 1;
  else return n * (power(n, e - 1));
}

int reverse(int x) {
  bool neg = false;
  bool minint = false;
  if (x < 0) {
    if (x == INT_MIN) minint = true;
    if (minint) x++;
    x *= -1;
    neg = true;
  }
  int res = 0;
  int pos = 1;
  int l = len(x);
  int cyphers[l];

  for (int i = 0; i < l; i++) {
    cyphers[i] = x % 10;
    x /= 10;
  }

  if (l == 10) {
    int maxint[10] = {2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
    for (int i = 0; i < l; i++) {
      //printf("max: %d, curr: %d\n", maxint[i], cyphers[i]);
    }
    for (int i = 0; i < l; i++) {
      if (cyphers[i] < maxint[i]) break;
      else if (cyphers[i] > maxint[i]) return 0;
    }
  }

  for (int i = 0; i < l; i++) {
    res += cyphers[i] * power(10, l - i - 1);
  }

  if (minint) res++;
  if (neg) return res * -1;
  return res;
}

