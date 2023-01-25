#include <stdio.h>

char message[] = {"It will now be inquired how the machine can of itself, and without having recourse to the hand of man, assume the successive dispositions suited to the operations. The solution of this problem has been taken from Jacquard's apparatus, used for the manufacture of brocaded stuffs, in the following manner.Two species of threads are usually distinguished in woven stuffs; one is the warp or longitudinal thread, the other the woof or transverse thread, which is conveyed by the instrument called the shuttle, and which crosses the longitudinal thread or warp. When a brocaded stuff is required, it is necessary in turn to prevent certain threads from crossing the woof, and this according to a succession which is determined by the nature of the design that is to be reproduced. Formerly this process was lengthy and difficult, and it was requisite that the workman, by attending to the design which he was to copy, should himself regulate the movements the threads were to take. Thence arose the high price of this description of stuffs, especially if threads of various colours entered into the fabric. To simplify this manufacture, Jacquard devised the plan of connecting each group of threads that were to act together, with a distinct lever belonging exclusively to that group. All these levers terminate in rods, which are united together in one bundle, having usually the form of a parallelopiped with a rectangular base. The rods are cylindrical, and are separated from each other by small intervals. The process of raising the threads is thus resolved into that of moving these various lever-arms in the requisite order. To effect this, a rectangular sheet of pasteboard is taken, somewhat larger in size than a section of the bundle of lever-arms. If this sheet be applied to the base of the bundle, and an advancing motion be then communicated to the pasteboard, this latter will move with it all the rods of the bundle, and consequently the threads that are connected with each of them. But if the pasteboard, instead of being plain, were pierced with holes corresponding to the extremities of the levers which meet it, then, since each of the levers would pass through the pasteboard during the motion of the latter, they would all remain in their places. We thus see that it is easy so to determine the position of the holes in the pasteboard, that, at any given moment, there shall be a certain number of levers, and consequently of parcels of threads, raised, while the rest remain where they were. Supposing this process is successively repeated according to a law indicated by the pattern to be executed, we perceive that this pattern may be reproduced on the stuff. For this purpose we need merely compose a series of cards according to the law required, and arrange them in suitable order one after the other; then, by causing them to pass over a polygonal beam which is so connected as to turn a new face for every stroke of the shuttle, which face shall then be impelled parallelly to itself against the bundle of lever-arms, the operation of raising the threads will be regularly performed. Thus we see that brocaded tissues may be manufactured with a precision and rapidity formerly difficult to obtain.Arrangements analogous to those just described have been introduced into the Analytical Engine. It contains two principal species of cards: first, Operation cards, by means of which the parts of the machine are so disposed as to execute any determinate series of operations, such as additions, subtractions, multiplications, and divisions; secondly, cards of the Variables, which indicate to the machine the columns on which the results are to be represented. The cards, when put in motion, successively arrange the various portions of the machine according to the nature of the processes that are to be effected, and the machine at the same time executes these processes by means of the various pieces of mechanism of which it is constituted."};
char keyword[] = {"LOVELACE"};

//made table a global variable to avoid passing it as a parameter which can get complicated (the 8 needs to be changed
// if keyword is changed since it cannot be a variable)
char table[5000][8] = {'\0'};


//avoids using sizeof(array) function that is built-in
int findSize(char array[]){
    int size = 0;
    while(array[size] != '\0'){
        size++;
    }
    return size;
}

//sorts the keyword and in doing so, swaps the columns of table also
void sortKeyword(int encryptLength, int encryptHeight){
    //bubble sort algorithm to sort the keyword
    //i = the horizontal part of the table, j = the vertical part of the table, k = current number of characters processed
    for (int i = 0; i < encryptLength - 1; i++){
        for (int j = 0; j < encryptLength - 1 - i; j++){
            if (keyword[j] > keyword[j + 1]){
                //swaps the letters of the keyword and also the columns of table
                char temp = keyword[j];
                keyword[j] = keyword[j + 1];
                keyword[j + 1] = temp;

                char temparray[496] = {'\0'};
                for (int counter = 0; counter < encryptHeight; counter++){
                    temparray[counter] = table[counter][j];
                }
                for (int counter = 0; counter < encryptHeight; counter++){
                    table[counter][j] = table[counter][j + 1];
                }
                for (int counter = 0; counter < encryptHeight; counter++){
                    table[counter][j + 1] = temparray[counter];
                }

            }
        }
    }
}

//puts the message into a 2d array which can then be manipulated further
void putIntoTable(int messageLength, int encryptLength, int encryptHeight){
    //i = the horizontal part of the table, j = the vertical part of the table, k = current number of characters processed
    int i = 0, j = 0, k = 0;
    for (int counter = 0; counter < messageLength; counter++){
        //reset if the 'x' counter goes beyond bounds of array
        if (i == encryptLength){
            i = 0;
        }
        table[j][i] = message[counter];

        i++;
        k++;
        //only increase when a full row has been put into table
        if (k % encryptLength == 0) {
            j++;
        }
    }
}

//outputs the table to console
void printTable(int encryptLength, int encryptHeight, int messageLength){
    int i = 0, j = 0, k = 0;
    //same logic as putting into table
    for (int counter = 0; counter < messageLength; counter++){
        if (i == encryptLength){
            i = 0;
        }

        printf("%c", table[j][i]);

        i++;
        k++;

        if (k % encryptLength == 0){
            j++;
        }
    }
}

int main() {

    int messageLength = findSize(message);
    int encryptLength = findSize(keyword);
    int encryptHeight = messageLength / encryptLength;

    //provides initial padding by assigning every character the value 'X'
    for (int i = 0; i < encryptHeight; i++){
        for (int j = 0; j < encryptLength; j++){
            table[i][j] = 'X';
        }
    }

    putIntoTable(messageLength, encryptLength, encryptHeight);
    sortKeyword(encryptLength, encryptHeight);
    printTable(encryptLength, encryptHeight, messageLength);

    return 0;

}
