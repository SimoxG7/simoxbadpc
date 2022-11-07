#include <stdio.h>

int main() {
    int cookie;
    char buf[80];

    printf("buf: %08x cookie: %08x\n", &buf, &cookie);
    gets(buf); //get non controlla dimensione input

    if (cookie == 0x41424344) printf("You win!\n");
}
/*
soluzione? -> lo stack frame del main sarà:
non ha parametri, 

dobbiamo fare buffer overflow in modo che il cookie valga 0x41424344 (ABCD)

La distanza tra le due variabili è di 92 byte, gets non limita l'input utente e dunque abbiamo bisogno di usare 92 byte in ingresso terminati da ABCD (96 byte quindi) (tenendo conto dell'endianness).

00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ABCD


AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADCBA

compilare con -fno-stack-protector (-z execstack?)
*/