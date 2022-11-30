#include <stdio.h>
#include <stdlib.h>





/**
 * Definition for singly-linked list.
*/
struct ListNode {
  int val;
  struct ListNode *next;
};


/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

void addlast(struct ListNode* node, int val) {
  while(node->next != NULL) {
    node = node->next;
  }
  struct ListNode* next = malloc(sizeof(struct ListNode));
  node->next = next;
  next->next = NULL;
  //node->next = NULL;
  node->val = val;
  printf("inserting %d\n", val);
}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
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
  
  printf("%d, %d, %d\n", n1, n2, res);
  
  struct ListNode* resl = malloc(sizeof(struct ListNode));
  resl->next = NULL;
  
  while(res != 0) {
    int rem = res%10;
    res /= 10;
    addlast(resl, rem);
  }
  
  return resl;
}

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















/*
//struct ListNode* addlast(struct ListNode* node, int val) {
void addlast(struct ListNode* node, int val) {
  while(node->next != NULL) {
    node = node->next;
  }
  struct ListNode* next = malloc(sizeof(struct ListNode));
  next->next == NULL;
  node->next = next;
  next->val = val;
  //return next;
}

int main(void) {
  struct ListNode* l1 = malloc(sizeof(struct ListNode));
  l1->val = 2;
  struct ListNode* l2 = malloc(sizeof(struct ListNode));
  l1->next = l2;
  l2->val = 5;
  l2->next = NULL;

  while (l1 != NULL) {
    printf("%d\n", l1->val);
    l1 = l1->next;  
  }

  int num = 123;
  
  struct ListNode* res = malloc(sizeof(struct ListNode));

  while(num != 0) {
    int rem = num%10;
    num /= 10;
    //struct ListNode* next = addlast(res, rem);
    addlast(res, rem);
    //printf("%d\n", next->val);
  }

  while(res != NULL) {
    printf("%d\n", res->val);
    res = res->next;
  }

  return 0;
}



/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

/*
void addlast(struct ListNode* node, int val) {
  while(node->next != NULL) {
    node = node->next;
  }
  struct ListNode* next = malloc(sizeof(struct ListNode));
  node->next = next;
  next->val = val;
}
*/



/*
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
  resl->next = NULL;
  
  while(res != 0) {
    int rem = res%10;
    res /= 10;
    addlast(resl, rem);
  }
  
  return resl;
}

*/


