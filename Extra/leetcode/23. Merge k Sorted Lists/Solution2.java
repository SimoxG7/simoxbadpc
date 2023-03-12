/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution2 {
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

    for (ListNode node : lists) {
      if (node != null)
        minHeap.add(node);
    }

    ListNode start = new ListNode(0);
    ListNode cur = start;
    while (!minHeap.isEmpty()) {
      ListNode minNode = minHeap.remove();
      cur.next = minNode;
      if (minNode.next != null)
        minHeap.add(minNode.next);
      cur = cur.next;
    }
    return start.next;
  }
}