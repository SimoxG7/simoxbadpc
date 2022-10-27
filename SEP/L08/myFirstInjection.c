#include <stdio.h>
#include <math.h>

void bye1() { puts("Goodbye!"); }
void bye2() { puts("Farewell!"); }

void hello(char *name, void (*bye_func) ()) {
    printf("Hello %s!\n", name);
    bye_func();
} 

int main(int argc, char **arv) {
    char name[1024];
    gets(name);
    srand(time(0));
    if (rand() % 2) hello(bye1, name);
    else hello(name, bye2);
}

