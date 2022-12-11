#include <stdio.h>

int main(void) {
  
  int num = 78;
  int* num_ptr = &num;

  printf("num: %d\nnum_ptr: %p\nnum_ptr dereferenced: %d\n\n", num, num_ptr, *num_ptr);

  char* string = "Hello Simox";

  printf("string: %s\nstring_ptr: %d\n", string, *string);

  //moving the string_ptr
  string += 6;

  printf("Incremented dereferenced pointer: %s\n", string);

  return 0;
}