// #include <stdio.h>
// #include <stdlib.h>

// int main(void) {
//   int numsSize = 4;
//   int* nums = malloc(sizeof(int) * numsSize);

//   for (int i = 0; i < numsSize; i++) {
//     *(nums + i) = i+1;
//   }

//   for(int i = 0; i < numsSize; i++) {
//     printf("%d, ", *(nums + i));
//   }
//   printf("\n");

//   for(int i = 0; i < numsSize; i++) {
//     printf("%d, ", *(nums + i));
//   }

//   return 0;
// }

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* twoSum(int* nums, int numsSize, int target, int* returnSize){
  
  int fixed = *nums;
  int *result = malloc(sizeof(int) * 2);

  
  for (int j = 0; j < numsSize; j++) {
    for (int i = j+1; i < numsSize; i++) {
      if (*(nums + j) + *(nums + i) == target) {
        *result = j;
        *(result + 1) = i;
        *(returnSize) = 2;
        return result;
      }
    }
    //nums++;
    //fixed = *nums;
  }
  return NULL;

}