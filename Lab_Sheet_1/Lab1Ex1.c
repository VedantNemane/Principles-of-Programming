#include <stdio.h>
#include <stdbool.h>
/*Includes <stdbool.h> to include minimal robustness for input from user and leads to the creation of boolean isValid.*/

//Gets input and validates, and returns the input.
int getInput(){
  bool isValid = false;
  int intInput = 0;
  /*Input instantiated to have value zero.*/
  /*While loop created to ask for valid input infinitely until correct input is received.*/
  while (isValid == false){
    printf("\nEnter an integer between 1 and 9999: ");
    scanf("%d", &intInput);
    /*Way of assigning a value to a boolean variable via a shorthand if statement below.*/
    isValid = ((intInput >= 0)&&(intInput <= 9999)) ? true: false;
  }
  return intInput;
}

/*Function checks if number is prime; takes the input from the function above; outputs a boolean response.*/
bool checkPrime(int intInput){
  bool isPrime = true;
  /*1 is a special number and so must be catered for.*/
  if(intInput == 1){
    isPrime = false;
  }
  else
  {
    /*for loop checks through all possibilities and combinations of division.*/
    for(int i = 2; i < intInput; i++){
      if (intInput % i == 0){
        isPrime = false;
        break;
      }
    }
  }
  return isPrime;
}

/*Function checks if number is even. Takes intInput as input from function and gives a Boolean response.*/
bool checkEven(int intInput){
  /*Way of assigning a value to a boolean variable via a shorthand if statement below.*/
  bool isEven = (intInput % 2 == 0) ? true: false;
  return isEven;
}

/*Created for easier readability of code.*/
void output(int intInput, bool isEven, bool isPrime){
  if(isEven){
    if(isPrime){
      printf("\neven and prime");
    }
    else{
      printf("\neven and not prime");
    }
  }
  else{
    if(isPrime){
      printf("\nodd and prime");
    }
    else{
      printf("\nodd and not prime");
    }
  }
}

/*Main control function to regulate which functions run and when.*/
void control(){
  int intInput = getInput();
  bool isPrime = checkPrime(intInput);
  bool isEven = checkEven(intInput);
  output(intInput, isEven, isPrime);
}

int main(void) {
  printf("\nQuestion 1");
  printf("\n----------------------------------------\n\n");
  control();
  printf("\n\n\n");
}




