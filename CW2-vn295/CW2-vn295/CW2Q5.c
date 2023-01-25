#include <stdio.h>

//declare functions to avoid implicit declaration error
int arrLength(char string[]);
int numWords(char string[]);
char toLower(char character);

//debate text
char debate[] = "Huw Merriman (Bexhill and Battle) (Con)\n"
                "\n"
                "What steps his Department is taking to support the broadcasting of sport on terrestrial television channels.\n"
                "\n"
                "The Parliamentary Under-Secretary of State for Digital, Culture, Media and Sport (Tracey Crouch)\n"
                "\n"
                "Sport is a key element of our national identity and the Government are committed to promoting sport and ensuring its coverage is made available to as many television viewers as possible. The listed events regime operates to make sure that sports events with a national significance can be viewed on free-to-air channels, and the Government are committed to safeguarding the regime.\n"
                "\n"
                "\n"
                "Huw Merriman\n"
                "     \n"
                "This week it was an absolute privilege to host in Parliament Dame Katherine Grainger, our most decorated female Olympian and now head of UK Sport. She came with the BBC Sport team as we all launched its new platform that will allow more sports to feature on the BBC website, acting as that platform. Does the Minister agree that this is a way to inspire more people to take up more sport and become Olympians in the future?\n"
                "\n"
                "Tracey Crouch\n"
                "\n"
                "I very much agree and congratulate my hon. Friend on his interest in this area and on hosting the launch of the BBC initiative, which I welcome. It will stream over 1,000 hours of extra sport a year, and along with the BBC connected sport app, this scheme will widen access to sports fans across the country. Colleagues who have not yet seen the live guide on the BBC Sport app should definitely check it out.\n"
                "\n"
                "Mr Philip Hollobone (Kettering) (Con)\n"
                "\n"
                "Is enough women's sport broadcast on terrestrial TV, and if not, what can the Government do about it?\n"
                "\n"
                "Tracey Crouch\n"
                "\n"
                "There can never be enough women's sport broadcast on TV, and I would always encourage more women's sport to be on TV. May I take this opportunity to congratulate Manchester United football club, which has finally dragged itself into the 21st century and announced that it will have a women's football team?\n"
                "\n"
                "Mr Speaker\n"
                "\n"
                "They are light years behind Arsenal.\n"
                "\n"
                "Contains Parliamentary information licensed under the Open Parliament Licence v3.0.";
//redacted words
char redact[] = "Arsenal\n"
                "terrestrial\n"
                "television\n"
                "events\n"
                "Manchester\n"
                "United\n"
                "app\n"
                "national\n"
                "Government";
//checks if two strings (one from the redactTable and one given) are the same
int matchString(char redactTable[][20], int index, char string[]){
    //if the strings are not the same length, do not bother making a comparison
    if (arrLength(string) != arrLength(redactTable[index])){
        return 0;
    }
    for (int counter = 0; counter < arrLength(string); counter++){
        if (toLower(redactTable[index][counter]) != toLower(string[counter])){
            return 0;
        }
    }
    return 1;
}
//converts a character to lower case equivalent
char toLower(char character){
    if ((character <= 90) && (character >= 65)){
        character = character + 32;
    }
    return character;
}
//removes the redacted words by calling other functions
void removeWords(char redactTable[][20]){
    int counter = 0;
    int subcounter = 0;
    char temp[30] = {'\0'};
    while(debate[counter] != '\0'){
        //if the character is a letter, add it to temp string
        if ((toLower(debate[counter]) >= 97) && toLower(debate[counter]) <=122) {
            temp[subcounter] = debate[counter];
            subcounter++;
        //else compare the temp string to all values in the redact table
        } else{
            subcounter = (counter - subcounter);
            for(int i = 0; i < 15; i++){
                //if there is a match, then go into debate and replace the characters with *
                if (matchString(redactTable, i, temp)){
                    while (subcounter < counter){
                        debate[subcounter] = '*';
                        subcounter++;
                    }
                }
            }
            //reset value of temp

            for (int j = arrLength(temp); j > -1; j--){
                temp[j] = '\0';
            }
            //reset the value of subcounter
            subcounter = 0;
        }
        counter++;
    }
}

//finds the length of an array
int arrLength(char string[]){
    int counter = 0;
    while (string[counter] != '\0'){
        counter++;
    }
    return counter;
}

//a function that creates the redactTable
void redacter(){
    char redactTable[15][20] = {'\0'};
    int subcounter = 0;
    int word = 0;
    int counter = 0;
    //while counter reaches the end of redact
    while(redact[counter] != '\0'){
        if (redact[counter] == '\n'){
            word++;
            subcounter = 0;
            counter++;
        }
        //if the subcounter is above 20, then reset it back
        if (subcounter > 19){
            subcounter = 0;
        }
        redactTable[word][subcounter] = redact[counter];
        subcounter++;
        counter++;
    }
    //removes the redacted words and updates debate array
    removeWords(redactTable);
    //prints out new debate array
    printf("%s", debate);
    printf("\n");
}

//calls the redacting function
int main() {
    redacter();
    return 1;
}
