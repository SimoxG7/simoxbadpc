#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

int sizeofstring(char* s) {
  int size = 0;
  while(*(s + size) != '\0') {
    size++;
  } 
  return size;
}

int isvowel(char c) {
  c = tolower(c);
  return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}

bool halvesAreAlike(char * s){
  
  int half = sizeofstring(s)/2;

  // char* s1 = malloc(half);
  // char* s2 = malloc(half);

  int cont1 = 0;
  int cont2 = 0;

  // for (int i = 0; i < half; i++) {
  //   *(s1 + i) = *(s + i);
  //   *(s2 + i) = *(s + half + i);
  // }

  for (int i = 0; i < half; i++) {
    cont1 += isvowel(*(s + i));
    cont2 += isvowel(*(s + i + half));
  }

  return cont1 == cont2;
}