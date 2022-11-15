//quale input dare al programma per fargli stampare "You win!"? -> bisogna fare unp shellcode che stampa "you win", iniettarlo sul buffer sfruttando la get e sul return address mettere l'indirizzo dello shell code 

//non c'è spazio per shell code -> si potrebbero usare le variabili d'ambiente

/* stack5-stdin.c gera */

#include <stdio.h> 

int main(void) {
    int cookie;
    char buf[4];

    printf("buf: %08x cookie: %08x \n", &buf, &cookie);
    gets(buf);
    if (cookie == 0x000d0a00) printf("You lose!\n");
}






