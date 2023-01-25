#include <stdio.h>

/*Finds the sum of square numbers from lowerbound to upperbound.*/
int SumofSquares(int lowerbound, int upperbound){
	int intTotal = 0;
	for(int i = lowerbound; i < upperbound + 1; i++){
		intTotal = intTotal + (i*i);
	}
	return intTotal;
}

/*Finds the square of the sum of numbers from lowerbound to upperbound.*/
int SquareofSum(int lowerbound, int upperbound){
	int intTotal = 0;
	for(int i = lowerbound; i < upperbound + 1; i++){
		intTotal = intTotal + i;
	}
	intTotal = intTotal * intTotal;
	return intTotal;
}

/*Makes the code more robust by checking bounds.*/
int checkInput(int lowerbound, int upperbound){
	if ((lowerbound >= 0) && (upperbound >= 0) && (lowerbound < upperbound)){
		return 1;
	}
	else{
		printf("\nInvalid input entered.");
		return 0;
	}
}

/*Controls the flow of the program*/
void control(){
	if (checkInput(1, 100)){
		printf("%d\n", SquareofSum(1, 100) - SumofSquares(1, 100));
	}
}

int main(){
	control();
	return 1;
}
