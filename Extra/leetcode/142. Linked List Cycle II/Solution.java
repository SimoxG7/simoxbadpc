public class Solution {

  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode detectCycle(ListNode head) {
    //fast and slow pointers maybe?
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        slow = head;
        while (slow != fast) {
          fast = fast.next;
          slow = slow.next;
        }
        return slow;
      }
    }
    return null;
  }
}