#include <stdio.h>

int findRemainder(int x, int y){
	
/*iterates till x is no longer equal to or less than y.*/
	while(x >= y){
		x = x - y;
	}
	return x;
}

/*checks if input entered was valid according to bounds provided.*/
int checkifvalid(int x, int y, int lowerbound, int upperbound){
	if ((x <= upperbound) && (y <= upperbound) && (x >= lowerbound) && (y >= lowerbound) && (lowerbound < upperbound)){
		return 1;
	}
	else{
		printf("\nInvalid input entered.\n");
		return 0;
	}
}

/*controls the flow of the program.*/
void control(){
	int x = 67;
	int y = 23;
	
/*Examples shown below (with x divided by y).*/
	if (checkifvalid(x, y, 0, 9999)){
		printf("\n%d", findRemainder(x, y));
	}
	
	x = -53;
	y = 2;
	
	if (checkifvalid(x, y, 0, 9999)){
		printf("\n%d\n", findRemainder(x, y));
	}
	
}

int main(void){
	control();
	return 1;
}
