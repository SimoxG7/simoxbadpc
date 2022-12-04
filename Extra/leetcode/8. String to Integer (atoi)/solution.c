#include <stdio.h>
#include <stdbool.h>
#include <limits.h>
#include <stdint.h>

int myAtoi(char * s){
  long num = 0; //or int_fast64_t from stdint.h
  bool neg = false;
  
  //ignoring whitespace
  while (*(s) == ' ') s++;

  if (!isdigit(*s))
  {
    if (*s == '-') neg = true;
    else if (*s != '+') return num;
    s++;
  }
  
  //skipping zeros
  while (*(s) == '0') s++;

  for (int i = 0; i < 12 && isdigit(*s) ; s++, i++) num = (num * 10) + (*s - '0');
  
  if (num > INT_MAX) {
    if (neg) return INT_MIN;
    else return INT_MAX;
  }

  if (neg) return -num;
  else return num;
}








/*
bool isDigit(char c) {
  if (c - '0' >= 0 && c - '0' <= 9) return true;
  return false;
}

int power(int n, int e) {
  if (e == 0) return 1;
  return n*(power(n, e-1));
}

int myAtoi(char * s){
  bool neg = false;
  int i = 0;
  int res[10];
  for (int i = 0; i < 10; i++) {
    res[i] = 0;
  }
  int digitcount = 0;

  while(*(s + i) != '\0') {
    char c = *(s + i);

    if (digitcount == 10) {
      if (!neg) return INT_MAX;
      else return INT_MIN;
    }

    if (isDigit(c)) {
      res[digitcount++] = c - '0';
    } else if (c == '-') neg = true;
    else if (c == '+') neg = false;
    else if (c != ' ') {
      //if (digitcount > 0) break;
      break;
    }
    i++;
  }

  

  int exp = 0;
  int ret = 0;
  int newcount = digitcount;
  for (int i = 0; i < newcount; i++) {
    ret += res[i] * power(10, --digitcount);
  }

  if (neg) return ret * -1;
  return ret;
}

int main(void) {
  printf("%d\n", myAtoi("42"));
} 
*/