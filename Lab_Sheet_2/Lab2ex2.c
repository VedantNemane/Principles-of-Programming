#include <stdio.h>
#include <string.h>
#include <math.h>
/*Includes math library and string library for basic string and math manipulation.*/

/*Checks the string for digits about 7 and any negative input.*/
int CheckInput(char str[], int len){
	for (int i = 0; i < len; i++){
		/*(str[i] - '0') is a form of typecasting to convert from char data type to integer.*/
		if ((str[i] - '0') > 7){
			return 0;
		}
	}
	return 1;
}

/*Converts the octal number to decimal and outputs the result.*/
void ConvertToDecimal(){
	/*Input string: */
	char str[] = "420";
	int len = strlen(str);
	
	/*decimalvalue stores the total of the decimal value.*/
	int decimalvalue = 0;
	/*power is gradually incremented to store the power of 8.*/
	int power = 0;
	
	if (CheckInput(str, len)){
		/*for loop is essentially backwards to start from the end of the string in ascending order of powers of 8.*/
		for (int i = len - 1; i > -1; i--){
			decimalvalue = decimalvalue + ((str[i] - '0') * pow(8, power));
			power++;
		}
		printf("\n%d\n", decimalvalue);
	}else{
		printf("\nInput string entered was invalid.\n");
	}
}

/*Starts the function which converts octal to decimal.*/
int main(){
	ConvertToDecimal();
}