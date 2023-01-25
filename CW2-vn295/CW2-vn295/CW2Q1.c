#include <stdio.h>

//declare the array of numbers and length as static so all methods have access.
static int arrNumbers[100];
static int arrLen = 100;

//not using an in-built function to generate random numbers.
int ReturnSingleRandomNumber(int previousNumber){
  //middle-square method to generate a random number.
  int square = previousNumber * previousNumber;
  char strSquare[7];
  //going as far as not using type conversion functions, but rather using sprintf to convert from int to string.
  sprintf(strSquare, "%d", square);
  int sum = 0;
  for (int counter = 2; counter < 6; counter++){
    sum = sum * 10 + (strSquare[counter] - 48);
  }
  return sum;
}

//takes an initial seed to generate random numbers
void GenerateRandomNumberArray(){
  int seed = 5684;
  for (int counter = 0; counter < arrLen; counter++){
    arrNumbers[counter] = ReturnSingleRandomNumber(seed);
    //update seed value otherwise all numbers will be the same
    seed = arrNumbers[counter];
  }
}

//prevents repetition of code
void SwapNumberPlaces(int firstindex, int secondindex){
  int temp = arrNumbers[firstindex];
  arrNumbers[firstindex] = arrNumbers[secondindex];
  arrNumbers[secondindex] = temp;
}

void CocktailSort(){
  //not using booleans as that requires importing libraries
  int swapped = 1;
  while(swapped == 1){
    swapped = 0;
    //go through array forwards
    for (int counter = 0; counter < arrLen - 1; counter++){
      if (arrNumbers[counter] > arrNumbers[counter + 1]){
        SwapNumberPlaces(counter, counter + 1);
        swapped = 1;
      }
    }
    //if there were no swaps, then exit while loop
    if (swapped == 0){
      break;
    }
    swapped = 0;
    //go through array backwards
    for(int counter = arrLen - 1; counter > 0; counter--){
      if (arrNumbers[counter] < arrNumbers[counter - 1]){
        SwapNumberPlaces(counter, counter - 1);
        swapped = 1;
      }
    }
  }
}

//prevents typing this twice
void PrintArray(){
  for (int counter = 0; counter < arrLen; counter++){
    if (counter % 10 == 0){
      printf("\n");
    }
    if (counter == 99){
      printf("%d.\n", arrNumbers[counter]);
    }
    else{
      printf("%d, ", arrNumbers[counter]);
    }
  }
}

int main(void) {
  GenerateRandomNumberArray();
  printf("\nHere is the array before: \n");
  PrintArray();
  CocktailSort();
  printf("\nHere is the array after sorting: \n");
  PrintArray();
}