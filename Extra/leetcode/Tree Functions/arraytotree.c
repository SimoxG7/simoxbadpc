#include <stdio.h>
#include <stdlib.h>

typedef struct btree
{
  int value;
  struct btree *left;
  struct btree *right;
} Btree;

void insert(Btree **t, int *a, int index, int n)
{
  if (index < n)
  {
    *t = malloc(sizeof(**t));

    (*t)->value = a[index];
    (*t)->left = NULL;
    (*t)->right = NULL;

    insert(&(*t)->left, a, 2 * index + 1, n);
    insert(&(*t)->right, a, 2 * index + 2, n);
  }
}

void print(Btree *t, int level)
{
  if (t)
  {
    print(t->left, level + 1);
    printf("%*s->%d\n", 4 * level, "", t->value);
    print(t->right, level + 1);
  }
}

int main(void)
{
  int a[] = {5, 2, 1, 6, 7, 3, 4};
  Btree *t;

  insert(&t, a, 0, 7);
  print(t, 0);

  // TODO: Clean up memory used by nodes

  int n = 0;
  while (n < (sizeof(a) / sizeof(int)) / 2)
  {
    printf("Parent: %d\n", a[n]);              // <= parent node
    printf("Left Child: %d\n", a[2 * n + 1]);  // <= left Child
    printf("Right Child: %d\n", a[2 * n + 2]); // <= right Child

    printf("\n");
    n++;
  }

  return 0;
}

// #include <stdio.h>
// #include <stdlib.h>

// // Definition for a binary tree node.
// struct TreeNode {
//   int val;
//   struct TreeNode *left;
//   struct TreeNode *right;
// };

// void arrToTree(struct TreeNode** node, int *arr, int curr, int arrsize) {
//   if (curr < arrsize) {
//     *(node) = malloc(sizeof(struct TreeNode**));

//     *(node)->val = arr[curr];
//     *(node)->left = NULL;
//     *(node)->right = NULL;

//     arrToTree(*(node)->left, arr, 2 * curr + 1, arrsize);
//     arrToTree(*(node)->right, arr, 2 * curr + 2, arrsize);
//   }
// }

// void printTree(struct TreeNode* node, int level) {
//   if (node) {
//     printTree(node->left, level+1);
//     printf("%d ", node->val);
//     printTree(node->right, level+1);
//   }
// }

// int main(void) {
//   int arr[11] = {1,2,3,4,5,6,7,NULL,NULL,8,NULL};
//   struct TreeNode* tree = malloc(sizeof(struct TreeNode));
//   int arrsize = sizeof(arr)/sizeof(*(arr));
//   int curr = 0;

//   arrToTree(&tree, arr, curr, arrsize);
//   printTree(tree, 0);
//   return 0;
// }
