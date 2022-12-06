  /**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* oddEvenList(struct ListNode* head){

    if (head == NULL) return NULL;

    struct ListNode* odd = head;
    struct ListNode* even = head->next;
    //struct ListNode* oddhead = odd; //this is the same as head, but for readability
    struct ListNode* evenhead = even;

     while (even != NULL && even->next != NULL) {
        odd->next = even->next;
        odd = odd->next;
        even->next = odd->next;
        even = even->next;
    }

    odd->next = evenhead;
    return head;
}