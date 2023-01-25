#include<stdio.h>

/*Recursion method to find remainder, where x is divided by y.*/
int findRemainder(int x, int y){
	if (x < y){
		return x;
	}
	else {
		return findRemainder(x - y, y);
	}
	return 0;
}

/*To control data input to be more robust.*/
int checkInput(int x, int y, int lowerbound, int upperbound){
	if ((x >= lowerbound) && (y >= lowerbound) && (x <= upperbound) && (y <= upperbound) && (lowerbound < upperbound)){
		return 1;
	}
	else {
		printf("\nInvalid input entered.\n");
		return 0;
	}
}

/*saves on writing "printf" statements.*/
void output(int intRemainder){
	printf("%d\n", intRemainder);
}

/*controls the flow of the program.*/
void control(){
	if (checkInput(53, 3, 0, 9999)){
		output(findRemainder(53, 3));
	}
	if (checkInput(-50, 10, 0, 9999)){
		output(findRemainder(-50, 10));
	}
}

/*starts the program*/
int main(){
	control();
	return 1;
}
