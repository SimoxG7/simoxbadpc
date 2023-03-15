import java.util.LinkedList;
import java.util.Queue;

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

  public boolean isCompleteTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    boolean foundnull = false;
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        if (curr == null) foundnull = true;
        else {
          if (foundnull) return false;
          queue.add(curr.left);
          queue.add(curr.right);
        }
      }
    }
    return true;
  }
}



