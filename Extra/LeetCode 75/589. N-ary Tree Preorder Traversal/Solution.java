import java.util.ArrayList;
import java.util.List;

class Solution {

  class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  public List<Integer> preorder(Node root) {
    List<Integer> ans = new ArrayList<>();
    return helper(root, ans);
  }

  public List<Integer> helper(Node root, List<Integer> res) {
    if (root == null) {
      return res;
    }
    res.add(root.val);
    for (Node n : root.children) {
      helper(n, res);
    }
    return res;
  }
}