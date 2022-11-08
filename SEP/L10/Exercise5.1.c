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


//secondo -> stampa x = 13
int x = 0;
int function() {
	int *ret;
	x = x +13;
	ret = (int *)&ret + 8; //con questa ret punta alla locazione di stack contenente il return address
	(*ret) = (*ret) + xx;
}

void main() {
	x = function(x);
	x = 10;
	printf("Value of x = %d\n", x);
}
