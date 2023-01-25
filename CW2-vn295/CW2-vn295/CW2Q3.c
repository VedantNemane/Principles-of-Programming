#include <stdio.h>
#include <stdbool.h>

//defines hashtable rows and columns: HashTable[rows][columns]. This can be changed as needed.
char HashTable[500][20];
int tableRows = sizeof(HashTable) / sizeof(HashTable[0]);
int tableColumns = sizeof(HashTable[0])/sizeof(HashTable[0][0]);

void view(); 
int string_length(char string[]);
int search(char string[]);

//checks if the string matches to the value in the table.
int MatchString(int index, char String[]){
  if (string_length(HashTable[index]) == string_length(String) - 1){
    for (int counter = 0; counter < string_length(String); counter++){
      if (HashTable[index][counter] != String[counter]){
        return 0;
      }
      else{
        return 1;
      }
    }
  } 
  else{
    return 0;
  }
  return 0;
}
//finds the length of the given string
int string_length(char string[]){
  int counter = 0;
  for (counter = 0; string[counter] != '\0'; counter++);
  return counter;
}
//create a prime number which is *just* under the limit (number of rows)
int CreatePrime(int limit){
  int prime[10000];
  int topPointer = 0;  
  for(int counter = 2; counter <= limit;counter++){
    int number = 0;
    for(int counter2 = 1;counter2 <= counter; counter2++){
      if(counter % counter2 == 0){
        number++;
      }
    }
    if(number == 2){
      prime[topPointer] = counter;
      topPointer++;
    }
  }
  return prime[topPointer - 1];
}
//converts string to hash value according to ascii values
int StringtoHash(char string[]){
  int sum = 0;
	for (int counter = 0; counter < 5; counter++) {
		sum = sum + (int)(string[counter]);
	}
	sum = sum % CreatePrime(tableRows);
	return sum;
}
//add the given string to the table at the correct position.
void add(char string[]){
  int index = StringtoHash(string);
  //make sure that elements before the index position are also considered.
  while (index > tableRows){
    index = index - (tableRows + 1);
  }
  int counter = 0;
  bool isEmpty = false;
	while (!isEmpty) {
    //avoid going over the maximum allowed index count for table.
    while(index > tableRows){
      index = index - (tableRows + 1);
    }
    //if the entry is deleted or is empty, then add the string there.
		if ((HashTable[index][0] == '*') || (HashTable[index][0] == '0')) {
			for (int i = 0; i < string_length(string); i++) {
				HashTable[index][i] = string[i];
			}
			for (int i = string_length(string) - 1; i < tableColumns; i++) {
				HashTable[index][i] = '\0';
			}
			isEmpty = true;
		}
    //if the counter reaches the same as tablerows, then there is a loop and no gap was found.
		else {
			if (counter == tableRows + 2) {
				printf("The table is full! The value was not added.\n");
				isEmpty = true;
			}
			counter++;
      index++;
		}
	}
  
}
//removes a specified string, whereever it is.
void removeEntry(char string[]){
  //uses search function to locate string.
  if (search(string) != -1){
    int index = search(string);
    for(int counter = 0; counter < tableColumns; counter++){
      HashTable[index][counter] = '*';
    }
    printf("Removed the string!\n");
  }
  else{
    printf("Couldn't find the specified string!\n");
  }
}
//had to change from bool -> int because its more useful to return a position than true/false
int search(char string[]){
  int index = StringtoHash(string);
  int counter = 0;
  while(true){
    while (index > tableRows){
      index = index - (tableRows + 1);
    }
    //if you hit an empty spot, then the string was never there so return false;
    if (HashTable[index][0] == 0){
      return -1;
    }
    if ((HashTable[index][0] != '*') && MatchString(index, string)){
      return index;
    }
    else{
      if (counter == tableRows + 2){
        return -1;
      }
      counter++;
      index++;
    }
  }
  return -1;
}
//called to view the table.
void view(){
  for (int counter = 0; counter < tableRows; counter++) {
    if (counter < 10){
      printf(" ");
    }
    if (counter < 100){
      printf(" ");
    }
		printf("%d. ", counter);
		for (int subcounter = 0; subcounter < tableColumns; subcounter++) {
			printf("%c", HashTable[counter][subcounter]);
		}
		printf("\n");
	}
  printf("\n");
}

int main(void) {
  //make all values in the table 0;
  for (int counter = 0; counter < tableRows; counter++) {
		for (int subcounter = 0; subcounter < tableColumns; subcounter++) {
			HashTable[counter][subcounter] = '0';
		}
	}
  bool running = true;
	int choice;
	while (running) {
		char input[tableColumns];
    printf("----------------------------------------------------\n");
		printf("You can either do (type the number, then press enter):\n    1. Add\n    2. Remove\n    3. Search\n    4. View\n    5. Exit\n");
		scanf("%d", &choice);
    //switch case statement easier than multiple if conditions.
		switch (choice) {
			case(1):
				printf("Type the string you want to add: \n");
        char ch;
        scanf("%c", &ch);
        fgets(input, tableColumns, stdin);
				add(input);
				break;
			case(2):
				printf("Type the string you want to remove: \n");
				scanf("%c", &ch);
        fgets(input, tableColumns, stdin);
				removeEntry(input);
				break;
			case(3):
				printf("Type the string you want to search: \n");
				scanf("%c", &ch);
        fgets(input, tableColumns, stdin);
        if (search(input) != -1){
          printf("Found the string at position: %d.\n", search(input));
        }
        else{
          printf("Couldn't find the string in the array.\n");
        }
				break;
			case(4):
				printf("HashTable displayed below: \n");
				view();
				break;
			case(5):
				running = false;
				break;
			default:
				printf("Something went wrong, here have another try: \n\n");
		}
	}
  return 1;
}