
class Solution {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return dfs(root, 1);
  }

  public int dfs(TreeNode node, int cont) {
    int left = cont, right = cont;
    if (node.left != null) left = dfs(node.left, cont+1);
    if (node.right != null) right = dfs(node.right, cont+1);
    return Integer.max(left, right);
  }
}