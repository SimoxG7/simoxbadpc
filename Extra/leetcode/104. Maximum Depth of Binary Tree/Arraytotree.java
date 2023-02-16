import java.util.LinkedList;
import java.util.Queue;

class Arraytotree {

  public static class TreeNode {
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
    // TODO
    return 0;
  }

  public static TreeNode arraytotree(Integer[] array, int pos) {
    TreeNode root = null;
    if (pos < array.length) {
      if (array[pos] == null)
        return null;
      root = new TreeNode(array[pos]);
      root.left = arraytotree(array, (pos * 2) + 1);
      root.right = arraytotree(array, (pos * 2) + 2);
    }
    return root;
  }

  public static void main(String[] args) {
    Integer[] array = { 3, 9, 20, null, null, 15, 7 };
    TreeNode tree = arraytotree(array, 0);
    printTree(tree);
  }

  static void printTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode temp = queue.poll();
      System.out.print(temp.val + " ");
      if (temp.left != null) {
        queue.add(temp.left);
      } else System.out.print("null ");
      if (temp.right != null) {
        queue.add(temp.right);
      } else System.out.print("null ");
    }
  }
}
