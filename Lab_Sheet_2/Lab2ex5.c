#include <stdio.h>

/*Array actually used as 1-100, so that the index relates to the actual value. 
As in index 42 would also contain the 0 or 1 if 42 is prime or not.*/
int prime[101];

/*Applies the Sieve to the global array "prime".*/
void Sieve(){
	/*In the array "prime", 1 is prime, and 0 is not prime. Every number is initialised as prime.*/
	for (int i = 1; i < 101; i++){
		prime[i] = 1;
	}
	/*If this started from 1, no number would be prime.*/
	int mainCounter = 2;
	/*This needs to repeated for each number- no telling how repeats you need. So a "for" loop is inappropriate.*/
	while (mainCounter < 101){
		/*i is the inner loop counter variable.*/
		int i = mainCounter;
		/*We are only interested in the multiple of the number, rather than the number itself. 
		Hence if the multiple is more than 101, we don't really care.*/
		while (i + mainCounter < 101){
			/*i is now the multiple of itself.*/
			i = i + mainCounter;
			/*If i was prime, we just proved it was a multiple- so assign 0 (not prime) to it.*/
			if (prime[i] == 1){
				prime[i] = 0;
			}
		}
		mainCounter++;
	}
	
}

/*Checks if the given integer parameter is between 1 and 100 and if it is prime or not.*/
int CheckifPrime(int TestNumber){
	/*Exercise asks for the number to be in this range.*/
	if ((TestNumber <= 0) || (TestNumber >= 101)){
		printf("\nInvalid input entered.\n");
	}
	else if (TestNumber == 1){
		printf("\n1 is not prime.\n");
	}
	else{
		/*since the range of values in the array is either 1 or 0, this is easier to write an if statement for.*/
		if (prime[TestNumber]){
			printf("\nNumber is prime.\n");
		}
		else{
			printf("\nNumber is not prime.\n");
		}
	}
	/*This return doesn't really matter, it's here for correct syntax.*/
	return 0;
}

/*Runs the Sieve function and number checking function.*/
int main(){
	Sieve();
	CheckifPrime(69);
}