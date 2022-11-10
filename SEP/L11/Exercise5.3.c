//which input data has to be provided to the following program to get it to output "You Win!"

#include <stdio.h>

int main(){
	char buf[80];
	int cookie;
	printf("buf: %08x cookie: %08x\n", &buf, &cookie);
	gets(buf);
	if (cookie == 0x41424344) printf("You Win!\n");
}

/* stack: (dal basso verso l'alto), il cookie è più in basso del buffer -> sbuffero e poi raggiunto RA ci metto l'indirizzo della printf
RA 
RBP
buf
cookie
*/


//serve scoprire quanto è distante RA dal buf e poi l'indirizzo della printf (objdump o debuggando)

//gcc -fno-stack-protector -z execstack -g Exercise5.3.c -o Exercise5.3



