#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

//https://leetcode.com/problems/minimum-falling-path-sum/
int minFallingPathSum(int** matrix, int matrixSize, int* matrixColSize){
  int max = INT_MIN; 

  for (int i = 0; i < matrixSize; i++) {
    //per ogni elemento della prima riga
    int curr = INT_MIN;
    int prev = i;
    for (int j = 0; j < matrixSize; j++) {
      //guardo riga per riga
      
      if (j > 0) {
        if (matrix[i+1][j-1]) return 0;
      }
    }
  }


}