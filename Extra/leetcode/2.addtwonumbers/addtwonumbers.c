#include <stdio.h>
#include <stdlib.h>

/**
 * Definition for singly-linked list.
*/
struct ListNode {
  int val;
  struct ListNode *next;
};

int main(void) {
  struct ListNode* l1 = malloc(sizeof(struct ListNode));
  l1->val = 2;
  struct ListNode* l2 = malloc(sizeof(struct ListNode));
  l1->next = l2;
  l2->val = 5;

  while (l1 != NULL) {
    printf("%d\n", l1->val);
    l1 = l1->next;  
  }
}

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
  int n1 = 0, n2 = 0;
  int n1mult = 1, n2mult = 1;
  
  while(l1->next != NULL) {
    n1 += n1mult * l1->val;
    n1mult *= 10;
    l1 = l1->next;
  }
  
  while(l2->next != NULL) {
    n2 += n2mult * l2->val;
    n2mult *= 10;
    l2 = l2->next;
  }
  
  int res = n1 + n2;
  
  struct ListNode* resl = malloc(sizeof(struct ListNode));
  
  while (res != 0) {
    resl->val = res%10;
    res /= 10;
    struct ListNode* temp = malloc(sizeof(struct ListNode));
    resl->next = temp;
    resl = temp;
  }
  return resl;
}


