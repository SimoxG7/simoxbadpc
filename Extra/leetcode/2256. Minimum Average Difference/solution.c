#include <limits.h>
#include <stdio.h>

int minimumAverageDifference(int *nums, int numsSize) {
  long sum = 0, left = 0;
  for (int i = 0; i < numsSize; i++)
    sum += nums[i];
  int min = INT_MAX, index = -1, res = 0;
  for (int i = 0; i < numsSize; i++) {
    sum -= nums[i];
    left += nums[i];
    if (i < numsSize - 1)
      res = abs(left / (i + 1) - sum / (numsSize - i - 1));
    else
      res = left / (i + 1);
    if (res < min) {
      min = res;
      index = i;
    }
  }
  return index;
}

/*
int minimumAverageDifference(int* nums, int numsSize){
  int res[numsSize];

  for (int i = 0; i < numsSize; i++) {
    long pre = 0;
    int precont = 0;
    long post = 0;
    int postcont = 0;

    for (int j = 0; j < numsSize; j++) {
      if (j > i) {
        post += nums[j];
        postcont++;
      } else {
        pre += nums[j];
        precont++;
      }
    }

    if (precont == 0) precont = 1;
    if (postcont == 0) postcont = 1;
    long resnum = pre/precont - post/postcont;
    if (resnum < 0) resnum *= -1;
    res[i] = resnum;
    //printf("pre: %d, post: %d, precont: %d, postcont: %d, resnum:%d\n", pre, post, precont, postcont, resnum);
  }

  int min = 0;
  for (int i = 0; i < numsSize; i++) {
    if (res[i] < res[min]) min = i;
    //printf("minval: %d, res[i]: %d, minindex: %d\n", res[min], res[i], min);
  }
  return min;
}

int main(void) {
  int nums[6] = {2,5,3,9,5,3};
  printf("%d\n", minimumAverageDifference(nums, 6));
}
*/
