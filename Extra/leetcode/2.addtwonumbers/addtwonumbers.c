#include <stdio.h>
#include <stdlib.h>

/**
 * Definition for singly-linked list.
*/
struct ListNode {
  int val;
  struct ListNode *next;
};

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){ //funziona, fixare problemi con int -> aggiungere direttamente a lista man mano
  
  struct ListNode* head = malloc(sizeof(struct ListNode));
  head->next = NULL;
  int first = (l1->val + l2->val);
  int carry = 0;
  head->val = first%10;
  if (first > 9) carry = 1;
  l1 = l1->next;
  l2 = l2->next;
  
  while(l1 != NULL && l2 != NULL) {
    struct ListNode* node = head;
    while(node->next != NULL) {
      node = node->next;
    }
    node->next = malloc(sizeof(struct ListNode));
    int res = (l1->val + l2->val) + carry;
    if (res > 9) carry = 1;
    else carry = 0;
    node->next->val = res %10;
    node->next->next = NULL;
    l1 = l1->next;
    l2 = l2->next;
  }

  while (l1 != NULL) {
    struct ListNode* node = head;
    while(node->next != NULL) {
      node = node->next;
    }
    node->next = malloc(sizeof(struct ListNode));
    int res = l1->val + carry;
    if (res > 9) carry = 1;
    else carry = 0;
    node->next->val = res %10;
    node->next->next = NULL;
    l1 = l1->next;
  } 
  while (l2 != NULL) {
    struct ListNode* node = head;
    while(node->next != NULL) {
      node = node->next;
    }
    node->next = malloc(sizeof(struct ListNode));
    int res = l2->val + carry;
    if (res > 9) carry = 1;
    else carry = 0;
    node->next->val = res %10;
    node->next->next = NULL;
    l2 = l2->next;
  }

  if (carry == 1) {
    struct ListNode* node = head;
    while(node->next != NULL) {
      node = node->next;
    }
    node->next = malloc(sizeof(struct ListNode));
    node->next->val = carry;
    node->next->next = NULL;
  }

  return head;
}


//TESTING
int main(void) {
  struct ListNode* l1 = malloc(sizeof(struct ListNode));
  l1->val = 2;
  struct ListNode* new1 = malloc(sizeof(struct ListNode));
  l1->next = new1;
  new1->val = 4;
  struct ListNode* new2 = malloc(sizeof(struct ListNode));
  new1->next = new2;
  new2->val = 3;
  new2->next = NULL;

  struct ListNode* l2 = malloc(sizeof(struct ListNode));
  l2->val = 5;
  struct ListNode* new3 = malloc(sizeof(struct ListNode));
  l2->next = new3;
  new3->val = 6;
  struct ListNode* new4 = malloc(sizeof(struct ListNode));
  new3->next = new4;
  new4->val = 4;
  new4->next = NULL;

  struct ListNode* resl = malloc(sizeof(struct ListNode));

  resl = addTwoNumbers(l1, l2);
  
  while(resl->next != NULL) {
    printf("%d\n", resl->val);
    resl = resl->next;
  }
  
  return 0;
}



//THIS WAS KINDA WORKING
/*
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){ //funziona, fixare problemi con int -> aggiungere direttamente a lista man mano
  int n1 = 0, n2 = 0;
  int n1mult = 1, n2mult = 1;
  
  while(l1 != NULL) {
    n1 += n1mult * l1->val;
    n1mult *= 10;
    l1 = l1->next;
  }
  
  while(l2 != NULL) {
    n2 += n2mult * l2->val;
    n2mult *= 10;
    l2 = l2->next;
  }
  
  int res = n1 + n2;
  
  struct ListNode* head = malloc(sizeof(struct ListNode));
  head->next = NULL;
  head->val = res%10;
  res /= 10;
  
  while(res!=0) {
    int rem = res%10;
    res /= 10;
    struct ListNode* node = head;
    while(node->next != NULL) {
      node = node->next;
    }
    node->next = malloc(sizeof(struct ListNode));
    node->next->val = rem;
    node->next->next = NULL;
  }
  return head;
}
*/