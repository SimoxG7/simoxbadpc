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

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode start = new ListNode();
    start.next = head;

    ListNode pre = start, cur = start, nex = start;
    int count = 0;

    while (cur.next != null) {
      count++;
      cur = cur.next;
    }

    while (count >= k) {
      cur = pre.next;
      nex = cur.next;
      for (int i = 1; i < k; i++) {
        cur.next = nex.next;
        nex.next = pre.next;
        pre.next = nex;
        nex = cur.next;
      }
      pre = cur;
      count -= k;
    }
    return start.next;
  }
}