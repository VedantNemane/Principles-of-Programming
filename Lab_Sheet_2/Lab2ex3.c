#include <stdio.h>

/*Draws the trunk.*/
void DrawTrunk(int trunkheight, int midtreewidth){
	/*First for loop regulates spacing.*/
	for (int i = 0; i < trunkheight; i++){
		printf("\n");
		/*For loop prints spaces for the trunk.*/
		for (int j = 0; j < midtreewidth - 1; j++){
			printf(" ");
		}
		printf("***");
	}
}

/*Draws tree.*/
int DrawTree(int treewidth, int trunkheight){
	/*Even if it is given, program checks if width entered was an even number.*/
	if (treewidth % 2 == 0){
		printf("\nThe tree width was set to an even number.\n");
		return 0;
	}
	/*The tree width nor the trunk height cannot be 0 or anything below.*/
	if ((treewidth <= 0) || (trunkheight <= 0)){
		printf("\nTree width or trunk height was set to 0 or below.\n");
		return 0;
	}
	
	/*float stores the midpoint of the tree width to calculate spacing.*/
	float midtreewidth = (treewidth / 2) + 0.5;
	
	/*Contains the number of rows needed for the "tree" part of the tree.*/
	int numberofrows = (treewidth + 1) / 2;
	/*Spaces for the "tree" part.*/
	int numberofspaces = ((treewidth + 1) / 2) - 1;
	/*counter for the number of stars in the tree.*/
	int counter = 1;
	
	/*Outer for loop controls adding new lines.*/
	for (int i = numberofrows; i > 0; i--){
		printf("\n");
		for (int j = numberofspaces; j > 0; j--){
			printf(" ");
		}
		/*Puts in *s according to the value stored in counter.*/
		for(int k = 0; k < counter; k++){
			if (counter > treewidth){
				break;
			}
			printf("*");
		}
		counter = counter + 2;
		numberofspaces--;
	}
	
	/*Calls draw trunk function.*/
	DrawTrunk(trunkheight, midtreewidth);
	
	printf("\n");
	return 1;
}

/*Calls the draw tree function.*/
int main(){
	DrawTree(9, 4);
	return 1;
}