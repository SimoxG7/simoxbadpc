#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <math.h>

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode {
  int val;
  struct TreeNode *left;
  struct TreeNode *right;
};

//postorder

int gainFromSubTree(struct TreeNode* root, int* maxSum) {
  if (!root) return 0;

  int gainFromLeft = gainFromSubTree(root->left, maxSum);
  if (gainFromLeft < 0) gainFromLeft = 0;

  int gainFromRight = gainFromSubTree(root->right, maxSum);
  if (gainFromRight < 0) gainFromRight = 0;

  // int gainFromLeft = max(gainFromSubTree(root->left), 0);
  // int gainFromRight = max(gainFromSubTree(root->right), 0);

  //if paths right and left are counted as negative, they are 0 here, so it works
  if (gainFromLeft + gainFromRight + root->val > *maxSum) *maxSum = gainFromLeft + gainFromRight + root->val;

  if (gainFromRight + root->val > gainFromLeft + root->val) {
    return gainFromRight + root->val;
  } else return gainFromLeft + root->val;
}

int maxPathSum(struct TreeNode* root){
  int maxSum = INT_MIN;
  gainFromSubTree(root, &maxSum);
  return maxSum;
}


