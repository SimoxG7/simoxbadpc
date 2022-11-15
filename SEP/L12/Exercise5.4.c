//quale input dare al programma per fargli stampare "You win!"? -> bisogna fare unp shellcode che stampa "you win", iniettarlo sul buffer sfruttando la get e sul return address mettere l'indirizzo dello shell code 

/* stack5-stdin.c gera */

#include <stdio.h> 

int main(void) {
    int cookie;
    char buf[80];

    printf("buf: %16x cookie: %16x \n", &buf, &cookie);
    gets(buf);
    if (cookie == 0x000d0a00) printf("You lose!\n");
}






