#include <stdio.h>

int main(int argc, int** argv){
	
	char buf[40];
	printf("Give me your name\n");
	gets(buf); //prende input finchè non riceve un invio -> man gets

	printf("Hello %s\n", buf);
	return 0;
}

/* se inserisco più di 40 caratteri, fa che rileva che stiamo smashando la stack e interrompe l'esecuzione. Dopo il buffer vi è una variabile "canarino", che viene controllata dopo l'esecuzione del programma, se modificata viene termianta l'exe. (Come canarino e i minatori) */

/*
protezioni:
canarino
stack non eseguibile
aslr (address space layout randomization) -> indirizzi vengono cambiati ad ogni esecuzione
*/

/*
per disabilitare stack e canarino:
-fno-stack-protector -z execstack 

installare gef -> gdb migliorato
*/

/*
gdb -> disassemble
*/

/*
bufferoverflow -> oltre il buffer per sovrascrivere ra (tipicamente)
*/

/*
come faccio a trovare quanto devo salire? 
echo 0 | sudo tee /proc/sys/kernel/randomize_va_space
gdb programma 
disass main 
b *main (breakpoint a inizio main)
mettiamo breakpoint -> b *main+84 (all'indirizzo del ret)
run shellcode
c per continuare

usare x/100x $rsp -> per vedere roba in byte


man ascii 
vim payload.py
python3 payload.py
hexdump attack

quando faccio girare in gdb e faccio girare gli do in pasto il file:
r < attack

x/100x $rsp

stepi -> fa prossima istruzione 

indirizzo dopo il buffer: 0x7fffffffdd08 (-16)

stepi

x/100x $rsp -80 -> voglio mettere e350 

ora funzione (modificando lo script in python)

b *main+84

//90 per nop 
//dobbiamo mettere lo shellcode scaricato, se la misura non torna bisogna fillare con nop


r < attack



*/