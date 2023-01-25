#include <stdio.h>
#include <string.h>

/*Outputs the message provided to it.*/
void PrintString(char message[]){
	printf("\nThe message is: %s  \n", message);
}

/*Does the reversing of the string.*/
void ReverseInput(){
	
	/*Stores the string to be reversed.*/
	char str[] = "Hello world!";
	int strlength = strlen(str);
	
	/*Used to store the reversed string, and is given the same length as input.*/
	char output[strlength];
	
	/*Stops the runtime error where the code tries to access element -1 from an array from a blank string.*/
	if (strlength == 0){
		printf("\nThe string entered was empty!");
	}
	else{
		/*counter used to identify "flipped" position of letter in new string.*/
		int counter = 0;
		/*Instead of counting forwards, loop counts backwards from the back.*/
		for(int i = strlength - 1; i >= 0; i--){
			output[counter] = str[i];
			counter++;
		}
		/*Unique to the linux terminal, you have to make sure end of string is defined.*/
		output[strlength] = '\0';
		/*Output message to console.*/
		PrintString(output);
	}
}

/*Calls the main function that finds the reverse of the string.*/
int main(){
	ReverseInput();
}