import java.util.ArrayList;
import java.util.List;

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

  List<Integer> nodes = new ArrayList<>();

  public int minDiffInBST(TreeNode root) {
    inOrder(root);
    int res = Integer.MAX_VALUE;
    for (int i = 0; i < nodes.size()-1; i++) {
      int distance = nodes.get(i+1) - nodes.get(i);
      res = Integer.min(distance, res);
    }
    return res;
  }

  public void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    nodes.add(root.val);
    inOrder(root.right);
  }
}