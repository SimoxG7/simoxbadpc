#include <stdio.h>

int main() {
    int cookie;
    char buf[80];

    printf("buf: %08x cookie: %08x\n", &buf, &cookie);
    gets(buf); //get non controlla dimensione input

    if (cookie == 0x01020305) printf("You win!\n");
}

/*
sempre distanza di 92
00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000☺☻♥♣
*/