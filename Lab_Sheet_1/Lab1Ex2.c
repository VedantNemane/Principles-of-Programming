#include<stdio.h>

/*Includes math library for easier calculations.*/
#include<math.h>

/*Output function to avoid repeating lines of code on evolution of code.*/
void output(double distance){
	printf("\n%1.2f\n\n", distance);
}
/*outputs the distance.*/
double finddistance(void){
	/*Instantiates two arrays of double data type values for easy storage of points.*/
	double FirstPoint[3] = {0.0, 3.0, 0.0};
	double SecondPoint[3] = {0.0, 4.0, 0.0};
	double sumofdiffsquared = 0.0;
	
	/*For loop can be edited from i <= 2 to i <= n-1 where n is the number of dimensions of points.*/
	for (int i = 0; i <=2; i++){
		sumofdiffsquared += pow(FirstPoint[i], 2) + pow(SecondPoint[i], 2);
	}
	double distance = sqrt(sumofdiffsquared);
	return distance;
}

/*Only contains two functions for now, but controls flow of program on evolution/refinement of program.*/
void control(void){
	output(finddistance());
}

/*Establishes the context and starts the function that controls which functions are used.*/
int main(void){
	printf("\nQuestion 2");
	printf("\n----------------------------------------\n\n");
	control();
}

