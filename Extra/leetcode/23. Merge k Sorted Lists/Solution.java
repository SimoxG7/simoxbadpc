import java.util.PriorityQueue;

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

// public ListNode mergeKLists(ListNode[] lists) {
// ListNode res = new ListNode();
// if (lists.length == 0) {
// return null;
// } else if (lists.length == 1) {
// return lists[0];
// }

// //mergesort?
// for (int i = 0; i < lists.length; i++) {
// res = mergeSort(res, lists[i]);
// }
// return res.next;
// }

// public ListNode mergeSort(ListNode a, ListNode b) {
// ListNode res = new ListNode();
// ListNode start = res;
// while(a != null && b != null) {
// if (a.val <= b.val) {
// res.next = new ListNode(a.val);
// a = a.next;
// } else {
// res.next = new ListNode(b.val);
// b = b.next;
// }
// res = res.next;
// }
// if (a == null) {
// res.next = b;
// } else {
// res.next = a;
// }
// return start.next;
// }