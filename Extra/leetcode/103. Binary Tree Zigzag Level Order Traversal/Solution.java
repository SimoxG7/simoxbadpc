import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    //inorder
    Queue<TreeNode> q = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    q.add(root);
    boolean left = true;
    if (root == null) return res;
    while (!q.isEmpty()) {
      int size = q.size();
      List<Integer> temp = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode curr = q.poll();
        temp.add(curr.val);
        if (curr.left != null) q.add(curr.left);
        if (curr.right != null) q.add(curr.right);
      }
      if (!left) Collections.reverse(temp);
      left = !left;
      res.add(temp);
    }
    return res;
  }

}