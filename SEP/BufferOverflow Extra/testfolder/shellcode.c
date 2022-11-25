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
*/

/*
gdb -> disassemble
*/
