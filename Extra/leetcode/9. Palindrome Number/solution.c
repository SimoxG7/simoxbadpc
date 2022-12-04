#include <stdio.h>
#include <stdbool.h>

bool isPalindrome(int x){
  if (x < 0) return false;
  
  char occ[10];
  int cont = 0;
  
  while(x > 0) {
    occ[cont++] = x%10 - '0';
    x /= 10;
  }

  for (int i = 0; i < cont; i++) {
    if (occ[i] != occ[cont-i-1]) return false;
  }
  return true;
}