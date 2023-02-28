class Solution {

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode curr = head;
    while (curr != null && curr.next != null) {
      int temp = curr.val;
      curr.val = curr.next.val;
      curr.next.val = temp;
      curr = curr.next.next;
    }
    return head;
  }
}