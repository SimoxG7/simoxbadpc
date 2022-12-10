#include <stdio.h>
#include <stdlib.h>

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

#define MOD 1000000007

int dfs1(struct TreeNode* root) {
	if (!root) return 0;
	return dfs1(root->left) + dfs1(root->right) + root->val;
}

int dfs2(struct TreeNode* root, int totalSum, long long* max, int lidx) {
	if (!root) return 0;
	int l = dfs2(root->left, totalSum, max, lidx + 1), r = dfs2(root->right, totalSum, max, lidx + 1);
	if (lidx != 0) (*max) = fmax((*max), (long long) (l + r + root->val) * (long long) (totalSum - (l + r + root->val)));
	return r + l + root->val;
}

int maxProduct(struct TreeNode* root) {
	long long max = 0;
	dfs2(root, dfs1(root), &max, 0);
	return max % MOD;
}


/*
int maxProduct(struct TreeNode* root){
	int max = 0;
	int newprod = 0;
	if (root == NULL) return 0;
	
	int suml = sumFromNode(root->left);
	int sumr = sumFromNode(root->right);
	int val = root->val;

	if ((val + suml) * (sumr) > (val + sumr) * suml) {
		newprod = (val + suml) * sumr;
	} else {
		newprod = (val + sumr) * suml;
	}

	if (newprod > max) max = newprod;
	return max;
}
	
int sumFromNode(struct TreeNode* node) {
	if (node == NULL) return 0;
	int sum = node->val;
	return sum + sumFromNode(node->left) + sumFromNode(node->right);
}
*/