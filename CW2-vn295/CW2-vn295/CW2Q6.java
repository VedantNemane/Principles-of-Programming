import java.io.*;

//create a class for text files
class TextFile{
    //each text file should have an address and text that it contains
    private String fileAddress;
    private String text;
    //default constructor
    public TextFile(String fileAddress){
        this.fileAddress = fileAddress;
        File file = new File(fileAddress);
        BufferedReader br;
        try{
            //tries to read in the whole file, line by line and separates via the system's line spacing
            String line;
            StringBuilder builder = new StringBuilder();
            br = new BufferedReader(new FileReader(file));
            while( (line = br.readLine())!= null){
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            this.text = (builder).toString();
        } catch (Exception e){
            System.out.println("File not found.");
        }
    }

    public String getText(){
        return text;
    }

    public void setFileAddress(String address){
        this.fileAddress = address;
    }

    public String getFileAddress(){
        return fileAddress;
    }
}

public class CW2Q6{
//finds the size of a char array
    public static int sizeofString(char[] string){
        int size = 0;
        while (true){
            try{
                if (string[size] == 'V'){}
                size++;
            } catch (Exception e){
                break;
            }
        }
        return size;
    }
//checks if one char array is the same as another
    public static int matchString(char[] string1, char[] string2){
        if (sizeofString(string1) != sizeofString(string2)){
            return 0;
        }else{
            for (int i = 0; i < sizeofString(string1); i++){
                if (string1[i] != string2[i]){
                    return 0;
                }
            }
        }
        return 1;
    }
//function to add redacted words to a list and also takes care of duplicates
    public static void addToRedactedWords(String string){
        int addornot = 1;
        if (wordCounter > 499){
            wordCounter = 499;
        }
        for (int i = 0; i <= wordCounter; i++){
            if (redactedWords[i] == null){
                addornot = 1;
                break;
            }
            if (sizeofString(string.toCharArray()) == sizeofString(redactedWords[i].toCharArray())){
                if (matchString(string.toCharArray(), redactedWords[i].toCharArray()) == 1){
                    addornot = 0;
                    break;
                }
            }
        }
        if ((addornot == 1) && (wordCounter < 500)){
            redactedWords[wordCounter] = string;
            //increment word counter if a successful entry has been made
            wordCounter++;
        }
    }

    public static String[] redactedWords = new String[500];
    public static int wordCounter = 0;

    public static void removeWords(char[] arrFileText){
        //create string builder to record words being redacted
        StringBuilder str = new StringBuilder();
        int counter = 0;
        //boolean for turning redacting on and off
        int redacting = 0;
        //boolean for checking whether a new sentence has started
        int startOfSentence = 1;
        //while the counter does not reach the final character
        while (counter < sizeofString(arrFileText)){
            //if the character is a capital letter and it is in the middle of the sentence, start removing immediately
            if ((startOfSentence == 0) && (arrFileText[counter] >= 65) && (arrFileText[counter] <= 90)){
                try {
                    //if an 'I' has a space or a new line following it, then it's just an 'I' and shouldn't be redacted.
                    if ((arrFileText[counter] == 'I') && ((arrFileText[counter + 1] == ' ') || (arrFileText[counter + 1] == '\r') || (arrFileText[counter + 1] == '\n') || (arrFileText[counter + 1] == '’') || (arrFileText[counter] == '.') || (arrFileText[counter] == '!') || (arrFileText[counter] == '?') || (arrFileText[counter] == '”') || (arrFileText[counter] == '“'))){
                        redacting = 0;
                    } else{
                        //otherwise start redacting
                        redacting = 1;
                    }
                    //technically, this catch is never triggered, so this exception is OK not being handled
                } catch (ArrayIndexOutOfBoundsException e){}
                //in any case, if a capital letter exists, then it is not the start of the sentence any more
                startOfSentence = 0;
                //if the character is a capitalising punctuation, then assume a new sentence has started
            } else if ((arrFileText[counter] == '.') || (arrFileText[counter] == '!') || (arrFileText[counter] == '?') || (arrFileText[counter] == '”') || (arrFileText[counter] == '“')){
                startOfSentence = 1;
                redacting = 0;
                if (str.length() != 0){
                    addToRedactedWords(str.toString());
                    str.delete(0, str.length());
                }
                //if the character is a space, carry the current state of startOfSentence forwards
            } else if ((arrFileText[counter] == ' ') || (arrFileText[counter] == '\r') || (arrFileText[counter] == '\n')){
                redacting = 0;
                if (str.length() != 0){
                    addToRedactedWords(str.toString());
                    str.delete(0, str.length());
                }
                //if character is not a letter, then turn off redacting
            } else if (((arrFileText[counter] < 65) || (arrFileText[counter] > 90)) && ((arrFileText[counter] < 97) || (arrFileText[counter] > 122))){
                startOfSentence = 0;
                redacting = 0;
                if (str.length() != 0){
                    addToRedactedWords(str.toString());
                    str.delete(0, str.length());
                }
            } else {
                startOfSentence = 0;
            }

            if (redacting == 1){
                str.append(arrFileText[counter]);
                arrFileText[counter] = '*';
            }

            counter++;
        }
        counter = 0;
        str.delete(0, str.length());
        //subcounter is the length of the word recorded in string builder
        int subcounter = 0;
        //runs through the whole file character by character again
        while (counter < sizeofString(arrFileText)){
            //if the word starts with a capital letter, start checking for matches in the table.
            if (((arrFileText[counter] >= 65) && (arrFileText[counter] <= 90))){
                //if 'I' is followed by punctuation or a space or a new line, ignore it
                if ((arrFileText[counter] == 'I') && ((arrFileText[counter + 1] == ' ') || (arrFileText[counter + 1] == '\r') || (arrFileText[counter + 1] == '\n') || (arrFileText[counter + 1] == '’') || (arrFileText[counter] == '.') || (arrFileText[counter] == '!') || (arrFileText[counter] == '?') || (arrFileText[counter] == '”') || (arrFileText[counter] == '“'))){

                } else {
                    //if anything else though, add it to the stringbuilder and increase subcounter
                    str.append(arrFileText[counter]);
                    subcounter++;
                }
                //if the character is a letter then add it to the stringbuilder and increase subcounter
            } else if ((arrFileText[counter] > 96) && (arrFileText[counter] < 122)){
                str.append(arrFileText[counter]);
                subcounter++;
                //else compile the stringbuilder and check for any matches- if there are then go back and delete the word
            } else if (((arrFileText[counter] < 96) || (arrFileText[counter] > 122))){
                char[] temp = str.toString().toCharArray();
                for (String redactedWord : redactedWords) {
                    if ((matchString(temp, redactedWord.toCharArray())) == 1) {
                        subcounter = counter - subcounter;
                        for (int j = subcounter; j < counter; j++) {
                            arrFileText[j] = '*';
                        }
                    }
                }
                //reset the variables
                subcounter = 0;
                str.delete(0, str.length());
            }
            counter++;
        }

    }

    public static void main(String[] args) {

        System.out.println("Enter warandpeace (program adds \"\\warandpeace.txt\" automatically) address: ");
        BufferedReader br;

        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            String address = br.readLine();

            TextFile file = new TextFile(address + "\\warandpeace.txt");

            char[] arrFileText = new char[file.getText().length()];
            //puts the string from the text file into a char array manually
            for (int count = 0; count < file.getText().length(); count++){
                arrFileText[count] = file.getText().charAt(count);
            }

            //call function to redact words
            removeWords(arrFileText);

            //create a new text file
            File redactedText = new File(address + "\\warandpeace_redacted_.txt");
            try {
                redactedText.createNewFile();
            } catch (Exception e){
                System.out.println("Program doesn't have permission to create a file.");
            }

            //write output to a new file
            FileWriter fw = new FileWriter(address + "\\warandpeace_redacted_.txt");

            for (char c : arrFileText){
                fw.write(c);
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }

    }
}
