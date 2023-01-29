import java.util.HashMap;

class LFUCache {

  private HashMap<Integer, Node> map;
  private FreqNode head;
  private FreqNode tail;

  private int capacity;
  private int size;

  public LFUCache(int capacity) {
    this.capacity = capacity;

    map = new HashMap<>();

    size = 0;
    head = new FreqNode(0);
    tail = new FreqNode(0);

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node ret = map.get(key);

      this.increaseFreq(ret);

      return ret.val;
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    if (capacity == 0) {
      return;
    }

    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.val = value;

      this.increaseFreq(node);
    } else {
      if (size >= capacity) {
        this.evict();
        size--;
      }

      Node item = new Node(key, value);
      this.increaseFreq(item);
      map.put(key, item);
      size++;
    }
  }

  private void increaseFreq(Node node) {
    FreqNode freqNode = (node.parent == null) ? head : node.parent;
    FreqNode nextFreqNode = freqNode.next;
    if (nextFreqNode.freq != freqNode.freq + 1) {
      nextFreqNode = new FreqNode(freqNode.freq + 1);

      nextFreqNode.next = freqNode.next;
      freqNode.next.prev = nextFreqNode;

      nextFreqNode.prev = freqNode;
      freqNode.next = nextFreqNode;
    }

    if (freqNode != head) {
      freqNode.unlinkNode(node);
      if (freqNode.isEmpty()) {
        this.unlinkFreqNode(freqNode);
      }
    }
    nextFreqNode.addNode(node);
  }

  private void evict() {
    FreqNode node = head.next;
    if (node != tail) {
      Node tmp = node.evict();
      map.remove(tmp.key);
      if (node.isEmpty()) {
        this.unlinkFreqNode(node);
      }
    }
  }

  private void unlinkFreqNode(FreqNode node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;

    node.next = null;
    node.prev = null;
  }

  private class FreqNode {
    public int freq;
    public FreqNode next, prev;
    public Node head, tail;

    public FreqNode(int freq) {
      this.freq = freq;

      head = new Node(-1, -1);
      tail = new Node(-1, -1);

      head.next = tail;
      tail.prev = head;

      next = null;
      prev = null;
    }

    public boolean isEmpty() {
      return head.next == tail;
    }

    public void addNode(Node node) {
      node.next = head.next;
      head.next.prev = node;

      node.prev = head;
      head.next = node;

      node.parent = this;
    }

    public void unlinkNode(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;

      node.next = null;
      node.prev = null;
      node.parent = null;
    }

    public Node evict() {
      Node tmp = tail.prev;
      this.unlinkNode(tmp);

      return tmp;
    }
  }

  private class Node {
    public int key, val;
    public FreqNode parent;
    public Node next, prev;

    public Node(int key, int val) {
      this.key = key;
      this.val = val;

      parent = null;
      next = null;
      prev = null;
    }
  }
}