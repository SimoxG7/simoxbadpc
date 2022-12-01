#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


int size(char* s) {
  int i = 0;
  while(*(s + i) != '\0') {
    i++;
  } 
  return i;
}

bool contains(char* arr, char c, int arrsize) {
  for (int i = 0; i < arrsize; i++) {
    if (*(arr + i) == c) return true;
  }
  return false;
}

int lengthOfLongestSubstring(char * s){
  int arrsize = 1;
  
  int index = 0;
  int cont = 0;
  int bestcont = 0;
  char* curr = malloc(arrsize);
  
  for (int index = 0; *(s + index) != '\0'; index++) {
    printf("%d\n", index);
    if (cont+1 > arrsize) {
      arrsize *= 2;
      curr = realloc(curr, arrsize);
    }
    //issue in this if/else, maybe even in the contains
    //seems like issue is in the contains
    if (contains(curr, *(s + index)), size(curr)) {
      arrsize = 1;
      curr = malloc(arrsize);
      cont = 0;
    } else {x
      *(curr + cont) = *(s + index);
      cont++;
    }
    if (cont > bestcont) bestcont = cont;
  }

  return bestcont;
}

char* copy(char* a1, int arrsize) {
  char* res = malloc(arrsize); 
  for (int i = 0; i < arrsize; i++) {
    *(res + i) = *(a1 + i);
  }
  return res;
}

int main(void) {

  char* s = "simox2simoxsimoxalealealeaaaaallleee123456789";
  printf("%d\n", lengthOfLongestSubstring(s));
  
  s = "abcabcbb";
  printf("%d\n", lengthOfLongestSubstring(s));
  s = "bbbbb";
  printf("%d\n", lengthOfLongestSubstring(s));
  s = "pwwkew";
  printf("%d\n", lengthOfLongestSubstring(s));

  printf("%d\n", size("simox"));

  return 0;
}


