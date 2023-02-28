import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    List<TreeNode> res = new LinkedList<>();
    preOrder(root, new HashMap<>(), res);
    return res;
  }

  public String preOrder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
    if (cur == null)
      return "#";
    String serial = cur.val + ",";
    serial += preOrder(cur.left, map, res) + ",";
    serial += preOrder(cur.right, map, res);

    map.put(serial, map.getOrDefault(serial, 0) + 1);
    if (map.get(serial) == 2)
      res.add(cur);
    return serial;
  }
}