/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* middleNode(struct ListNode* head){
  struct ListNode* slow = head;
  struct ListNode* fast = head;
  
  while (fast != NULL && fast->next != NULL) {
    slow = slow->next;
    fast = fast->next->next;
  }
  return slow;
}






//WORKS, BUT FOUND AN INTERESTING ARTICLE ABOUT SLOW AND FAST POINTERS TO LINKED LIST

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/*
struct ListNode* middleNode(struct ListNode* head){
  int cont = 0;
  struct ListNode* node = head;
  while (node->next != NULL) {
    node = node->next;
    cont++;
  }

  node = head;
  for (int i = 0; i < (cont+1)/2; i++) {
    node = node->next;
  }
  return node;
}
*/