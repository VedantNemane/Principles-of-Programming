#include <stdio.h>
#include <string.h>

/*Checks whether each character in the same position is the same in both strings.*/
int CheckPalindrome(char str[], char newstring[], int strlength){
	for (int i = 0; i < strlength; i++){
		/*If the same char in the same position is not the same, immediately return 0 (false).*/
		if (str[i] != newstring[i]){
			return 0;
		}
	}
	/*If it goes through all loops, it must be true.*/
	return 1;
}

/*Taken from first question to reverse the string.*/
void ReverseString(char str[]){
	int strlength = strlen(str);
	char newstring[strlength];
	
	if (strlength == 0){
		printf("\nThe string entered was empty!");
	}
	else{
		/*reverses string by getting a new string of the same length and reversing the index assigning.*/
		int counter = 0;
		for(int i = strlength - 1; i >= 0; i--){
			newstring[counter] = str[i];
			counter++;
		}
		newstring[strlength] = '\0';
		
		/*if statement releases output based on true/false identity of the input string.*/
		if (CheckPalindrome(str, newstring, strlength)){
			printf("\ntrue\n");
		}
		else{
			printf("\nfalse\n");
		}
	}
}

int main(){
	/*Input string for checking.*/
	char input[] = "racecar";
	ReverseString(input);
	return 1;
}