import java.util.Random;

class Solution {

  private ListNode head;
  private Random random;

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

  //initialization
  public Solution(ListNode head) {
    this.head = head;
    this.random = new Random();
  }


  public int getRandom() {
    int count = 0;
    int result = 0;
    ListNode curr = this.head;

    //iterating list
    while (curr != null) {
      //if rand == 0 update result, else continue to iterate
      //should garantee equality and use O(1) memory, O(n) time
      count++;
      int rand = random.nextInt(count);
      if (rand == 0) {
        result = curr.val;
      }
      curr = curr.next;
    }
    return result;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */