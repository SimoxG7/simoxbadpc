#include <stdio.h>


int testfunc(int matrix[9][9]) {
  return *(*(matrix + 1) + 1);
}


int main(void) {
  int matrix2[9][9] = {
    { 1, 2, 3, 4, 1, 1, 1, 1, 1 },
    { 9, 8, 7, 1, 1, 1, 1, 1, 15 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 }
  };

  int (*matrix)[9][9] = &matrix2;

  /*
  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      printf("%d\n", **(matrix + (9*i*(sizeof(int))) + (j*sizeof(int))));  
    }
  } 
  */

  printf("%d\n", *(*(matrix)+1));
  printf("%d\n", *(*(matrix)+2));
  printf("%d\n", **(matrix+2));
  printf("%d\n", **(matrix+3));
  printf("%d\n", **(matrix+8));
  printf("%d\n", *(*(matrix+1)+1));

  testfunc(*matrix);
  
  return 0;
}