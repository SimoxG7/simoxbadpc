#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

struct TreeNode {
  int val;
  struct TreeNode *left;
  struct TreeNode *right;
};

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

void leafNodeArray(char* array, int *nodeCnt, struct TreeNode* root){
  if (root == NULL) return;
  if (root->left == NULL && root->right == NULL) {
    *(array + *nodeCnt) = root->val;
    (*nodeCnt)++;   
    return;
  } else {
    leafNodeArray(array, nodeCnt, root->left);
    leafNodeArray(array, nodeCnt, root->right);
  } 
}

bool leafSimilar(struct TreeNode* root1, struct TreeNode* root2) {
  int nodeCnt1 = 0;
  int nodeCnt2 = 0;
  
  char * str1 = (char *)malloc(200);
  char * str2 = (char *)malloc(200);

  leafNodeArray(str1, &nodeCnt1, root1);
  leafNodeArray(str2, &nodeCnt2, root2);
  
  if (nodeCnt1 == nodeCnt2) {
    return (memcmp(str1,str2,nodeCnt1) == 0 );
  } else {
    return false;
  } 
  
  free(str1);
  free(str2);
}
