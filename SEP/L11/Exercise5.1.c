//primo -> stampa x = 10

int x = 0;
int function() {
	x = x +13;
}

void main() {
	x = function(x);
	x = 10;
	printf("Value of x = %d\n", x);
}


//secondo -> stampa x = 13  //sovrascrive il return address
int x = 0;
int function() {
	int *ret;
	x = x +13;
	ret = (int *)&ret + 8; //con questa ret punta alla locazione di stack contenente il return address
	(*ret) = (*ret) + xx; //per far saltare il return address a un'altra istruzione
	//nello stack il return address è una posizione come le altre, non è protetto e può essere modificato
}

void main() {
	x = function(x); //alla chiamata di function, il RA punta a x=10, RBP
	x = 10;
	printf("Value of x = %d\n", x);
}



