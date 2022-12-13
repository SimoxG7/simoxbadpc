#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// https://leetcode.com/problems/minimum-falling-path-sum/

int minFallingPathSum(int **matrix, int matrixSize, int *matrixColSize)
{
  int a, b, c;
  int n = matrixSize, m = *matrixColSize;
  for (int i = 1; i < n; i++)
  {
    for (int j = 0; j < m; j++)
    {
      if (j == 0)
      {
        a = matrix[i - 1][j], b = matrix[i - 1][j + 1];
        if (a < b)
        {
          matrix[i][j] += a;
        }
        else
        {
          matrix[i][j] += b;
        }
      }
      else if (j == m - 1)
      {
        a = matrix[i - 1][j - 1], b = matrix[i - 1][j];
        if (a < b)
        {
          matrix[i][j] += a;
        }
        else
        {
          matrix[i][j] += b;
        }
      }
      else if (j != 0 || j != m - 1)
      {
        a = matrix[i - 1][j - 1], b = matrix[i - 1][j], c = matrix[i - 1][j + 1];
        if (a <= b && a <= c)
        {
          matrix[i][j] += a;
        }
        else if (b <= a && b <= c)
        {
          matrix[i][j] += b;
        }
        else
        {
          matrix[i][j] += c;
        }
      }
    }
  }
  int min = matrix[n - 1][0];
  for (int i = 1; i < m; i++)
  {
    if (min > matrix[n - 1][i])
    {
      min = matrix[n - 1][i];
    }
  }
  return min;
}