#include <stdio.h>
#include <stdlib.h>

double findMedianSortedArrays(int *nums1, int nums1Size, int *nums2, int nums2Size)
{
  int n = nums1Size + nums2Size;
  float res;
  float nums3[n];
  int i, j, k, a;
  i = j = k = 0;
  while (i < nums1Size && j < nums2Size)
  {
    if (nums1[i] < nums2[j])
      nums3[k++] = nums1[i++];
    else
      nums3[k++] = nums2[j++];
  }
  for (; i < nums1Size; i++)
    nums3[k++] = nums1[i];
  for (; j < nums2Size; j++)
    nums3[k++] = nums2[j];

  if (n % 2 == 0)
  {
    a = n / 2;
    res = (nums3[a] + nums3[a - 1]) / 2;

    return res;
  }
  else
  {
    res = nums3[n / 2];
    return res;
  }

  return -1;
}

/*
double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size){
  int numsfinalsize = nums1Size + nums2Size;

  //int* numsfinal = malloc(sizeof(int) * numsfinalsize);
  int numsfinal[numsfinalsize];

  int i = 0;
  int j = 0;
  int k = 0;

  while (i < nums1Size && j < nums1Size) {
    if (*(nums1 + i) <= *(nums2 + j)  ) {
      *(numsfinal + k++) = *(nums1 + i++);
    } else {
      *(numsfinal + k++) = *(nums2 + j++);
    }
  }

  if (i < nums1Size) {
    while (j < nums2Size) {
      *(numsfinal + k++) = *(nums2 + j);
    }
  } else {
    while (i < nums1Size) {
      *(numsfinal + k++) = *(nums1 + i);
    }
  }

  for (int h = 0; h < numsfinalsize; h++) {
    printf("a:%d\n", *(numsfinal + h));
  }

  printf("%d %d\n", *(numsfinal + numsfinalsize/2 -1), *(numsfinal + numsfinalsize/2));

  printf("%d\n", numsfinalsize);

  double res;
  if (numsfinalsize % 2 == 1) {
    res = *(numsfinal + numsfinalsize/2);
    return res;
  } else {
    res = (*(numsfinal + numsfinalsize/2 -1) + *(numsfinal + numsfinalsize/2))/2;
    return res;
  }
}
*/

int main(void)
{
  int a[4] = {1, 3, 5, 6};
  int b[2] = {2, 4};

  printf("%f\n", findMedianSortedArrays(a, 4, b, 2));

  for (int h = 0; h < 4; h++)
  {
    printf("a:%d\n", *(a + h));
  }

  return 0;
}