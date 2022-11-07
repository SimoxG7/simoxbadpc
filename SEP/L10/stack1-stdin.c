#include <stdio.h>

int main() {
    int cookie;
    char buf[80];

    printf("buf: %08x cookie: %08x\n", &buf, &cookie);
    gets(buf);

    if (cookie == 0x41424344) printf("You win!\n");
}
/*
soluzione? -> lo stack frame del main sarà:
non ha parametri, 


*/