#include <stdio.h>

//declare global variables
int dayCounter = 2;
int dateCounter = 1;
int leapYear = 0;
int yearCounter = 1901;
int monthCounter = 1;

//method to increment day of the week by resetting if it goes higher than 7
void incrementDay(){
    if (dayCounter + 1 > 7){
        dayCounter = 1;
    } else {
        dayCounter++;
    }
}

//method to increment month by resetting if it goes higher than 12
void incrementMonth(){
    if (monthCounter + 1 > 12){
        monthCounter = 1;
        yearCounter++;
    } else {
        monthCounter++;
    }
}

//method to increment date by considering all cases
void incrementDate(){
    if (leapYear == 0){
        //if the month has 30 days, then account for that
        if ((monthCounter == 4) || (monthCounter == 6) || (monthCounter == 9) || (monthCounter == 11)){
            if (dateCounter + 1 > 30) {
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
            //same for if it's a february on a non-leap year
        } else if (monthCounter == 2){
            if (dateCounter + 1 > 28){
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
            //accounts for months that last for 31 days
        } else {
            if (dateCounter + 1 > 31){
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
        }
    } else {
        if ((monthCounter == 4) || (monthCounter == 6) || (monthCounter == 9) || (monthCounter == 11)){
            if (dateCounter + 1 > 30) {
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
        } else if (monthCounter == 2){
            if (dateCounter + 1 > 29){
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
        } else {
            if (dateCounter + 1 > 31){
                dateCounter = 1;
                incrementMonth();
            } else {
                dateCounter++;
            }
        }
    }

    incrementDay();

    if (yearCounter % 4 == 0){
        leapYear = 1;
    } else {
        leapYear = 0;
    }
}

int main() {

    int noofTuesdays = 0;
    int exitLoop = 0;
    
    //repeat until the final date has been reached
    while (exitLoop == 0){
        if ((dateCounter == 1) && (dayCounter == 2)){
            noofTuesdays++;
        }
        //call function to do processing
        incrementDate();
        
        //update value of exitLoop to exit
        if (yearCounter == 2000){
            if (monthCounter == 12){
                if (dateCounter == 31){
                    exitLoop = 1;
                }
            }
        }
    }
    //output answer
    printf("\nThe number of Tuesdays that fell on the first of the month in the twentieth century are: \n");
    printf("%d", noofTuesdays);
    
    return 1;
}
