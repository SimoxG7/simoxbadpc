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

  public TreeNode invertTree(TreeNode root) {
    if (root != null) {
      DFSswapLR(root);
    }
    return root;
  }

  public TreeNode DFSswapLR(TreeNode root) {
    if (root != null) {
      TreeNode left = root.left;
      TreeNode right = root.right;
      root.left = right;
      root.right = left;
      DFSswapLR(root.left);
      DFSswapLR(root.right);
    }
    return root;
  }
}