//hell nah
#include <stdio.h>
#include <stdlib.h>

char * longestCommonPrefix(char ** strs, int strsSize){

  int commonsize = 2;
  char* common = malloc(sizeof(char) * commonsize);
  int j = 0;
  char curr;

  for (int i = 0; i < strsSize; i++, j++) {
    if (i == commonsize) {
      commonsize *= 2;
      realloc(common, commonsize);
    }

    if (i == 0) curr = *(*(strs) + j);

    if (*(*(strs + i) + j) == '\0') {
      break;
    } else if (*(*(strs + i) +j ) != *(*(strs + i+1) + j)) {
      break;
    } else {
      
    }

  }

  return common;
}