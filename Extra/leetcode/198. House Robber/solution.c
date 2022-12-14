#include <math.h>
#include <stdio.h>

//Input: nums = [2,7,9,3,1]
/*
1° -> numsval:2, max_val: 2, rob1: 0, rob2: 2 
2° -> numsval:7, max_val: 7, rob1: 2, rob2: 7 
3° -> numsval:9, max_val: 9, rob1: 2, rob2: 9 
4° -> numsval:3, max_val: 9, rob1: 9, rob2: 9 
5° -> numsval:1, max_val: 10, rob1: 9, rob2: 10 
*/

int rob(int *nums, int numsSize) {
  int rob1 = 0;
  int rob2 = 0;
  int max_val = 0;
  
  for (int i = 0; i < numsSize; i++) {
    if (rob2 > (*(nums + i) + rob1)) max_val = rob2;
    else max_val = (*(nums + i) + rob1);
    rob1 = rob2;
    rob2 = max_val;
  }
  
  return max_val;
}