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
  char* curr = calloc(arrsize, sizeof(char));
  
  for (int index = 0; *(s + index) != '\0'; index++) {
    //printf("%d\n", index);
    if (cont+1 > arrsize) {
      arrsize *= 2;
      curr = realloc(curr, arrsize);
      //printf("here\n");
    }
    //issue in this if/else, maybe even in the contains
    //seems like issue is in the contains
    if (contains(curr, *(s + index), size(curr))) {
      arrsize = 1;
      curr = calloc(arrsize, sizeof(char));
      cont = 0;
    } else {
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

  char* word = malloc(25);
  *(word) = 'C';
  *(word + 1) = 'd';
  printf("%d\n", size(word));

  return 0;
}





/*

bool contains(char arr[], char c) {
  for (int i = 0; arr[i] != '\0'; i++) {
    if (arr[i] == c) return true;
  }
  return false;
}

int lengthOfLongestSubstring(char * s){
  char* s1;
  char* s2;
  int arrsize = 1;
  s1 = malloc(arrsize * sizeof(char));
  s2 = malloc(arrsize * sizeof(char));

  for (int i = 0; s[i] != '\0'; i++) {
    if (i+1 > arrsize) {
      arrsize *= 2;
      s1 = realloc(s1, arrsize);
      s2 = realloc(s2, arrsize);
    }
    s1[i] = s[i];
    s2[i] = s[i];
  }

  for (int i = 0; s1[i] != '\0'; i++) {
    for (int j = 0; s2[j] !0 '\0'; j++) {
      return 0;
    }
  }

  return 0;
}

int main(void) {
  lengthOfLongestSubstring("hello");
  return 0;
}
*/

