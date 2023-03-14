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

  public int sumNumbers(TreeNode root) {
    return recursion(root, "");
  }

  private int recursion(TreeNode root, String curr) {
    if (root == null) {
      //System.out.println("returning " + curr);
      return Integer.parseInt(curr);
    }
    //System.out.println("Visiting " + root.val + " with " + curr);
    String v = curr + Integer.toString(root.val);
    if (root.left != null && root.right != null) {
      return recursion(root.left, v) + recursion(root.right, v);
    } else if (root.left != null) {
      return recursion(root.left, v);
    } else if (root.right != null) {
      return recursion(root.right, v);
    }
    return Integer.parseInt(v);
  }
}



