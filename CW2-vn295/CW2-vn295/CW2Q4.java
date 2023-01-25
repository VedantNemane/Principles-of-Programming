import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//previousAddress = address of next ^ current npx

public class Main {
    //declare a new class called node that creates objects which store both data and npx value
    public class Node{
        String data;
        int npx;

        public Node(String data){
            this.data = data;
        }
    }

    public static Node[] array = new Node[500];

    public static int[] addressesGenerated = new int[array.length];

    public int addressesGeneratedContains(int number){
        for (int i = 0; i < addressesGenerated.length; i++){
            if (addressesGenerated[i] == number){
                return 1;
            }
        }
        return 0;
    }

    //value used to keep track of random number so that they don't repeat
    int previous;
    //value for initial seed for random number
    public void seeding(){
        previous = 397;
    }
    //generates random number between the interval to serve as the "memory location" (they're actually memory addresses for the array)
    public int generateRandomNumber(){
        int a = 25214907;
        int c = 11;
        int r = a * previous + c;
        previous = r;
        r = r % array.length;
        while ((addressesGeneratedContains((int)r) == 1)){
            generateRandomNumber();
        }
        if (r < 0){
            return -r;
        } else {
            return r;
        }
    }

    Node head = new Node("null");

    int prevAddress, nextAddress, currAddress;
    //finds the size of a string
    public int sizeofString(String string){
        int size = 0;
        while (true){
            try {
                if (string.charAt(size) == 'V');
                size++;
            } catch (Exception e){
                break;
            }
        }
        return size;
    }
    //checks if the two provided strings are the same
    public int matchString(String string1, String string2){
        if (sizeofString(string1) != sizeofString(string2)){
            return 0;
        }
        for (int counter = 0; counter < sizeofString(string1); counter++){
            if (string1.charAt(counter) != string2.charAt(counter)){
                return 0;
            }
        }
        return 1;
    }

    //inserts a string after a string provided
    public void insertAfter(String after, String newObj){
        prevAddress = 0;
        array[currAddress] = head;

        for (int i = 0; i < 500; i++){
            nextAddress = array[currAddress].npx ^ prevAddress;
            if (array[nextAddress] == null){
                array[nextAddress] = new Node("");
            }
            if (matchString(array[currAddress].data, after) == 1){
                int newAddress = generateRandomNumber();
                Node newNode = new Node(after);
                array[newAddress] = newNode;

                array[currAddress].npx = prevAddress ^ newAddress;
                array[nextAddress].npx = newAddress ^ (currAddress ^ array[nextAddress].npx);
                array[newAddress].npx = currAddress ^ nextAddress;
            }
            prevAddress = currAddress;
            currAddress = nextAddress;
        }

        /*while (currAddress != 0){

            try{
                nextAddress = array[currAddress].npx ^ prevAddress;
                if (matchString(array[currAddress].data, after) == 1){

                    array[nextAddress] = new Node(array[nextAddress].data);

                    int newAddress = generateRandomNumber();
                    array[newAddress] = newNode;

                    array[currAddress].npx = prevAddress ^ newAddress;
                    array[nextAddress].npx = array[newAddress].npx ^ array[nextAddress].npx;
                    array[newAddress].npx = currAddress ^ nextAddress;

                    break;
                }
                prevAddress = currAddress;
                currAddress = nextAddress;

            } catch (ArrayIndexOutOfBoundsException e){
                break;
            }
        }*/
    }

    public void insertBefore ( String before , String newObj ){

    }

    /*
    public String removeAfter ( String after ){}
    public String removeBefore ( String before ){}
    */

    public void start(){
        seeding();

        int running = 1;

        BufferedReader br;

        int firstNode = 1;
        //set up initial previous address, current address, next address
        prevAddress = 0;
        currAddress = 1;
        nextAddress = generateRandomNumber();
        head.npx = prevAddress ^ nextAddress;
        array[currAddress] = head;

        while (running == 1){
            System.out.println("Select a number:\n1 : insertAfter\n2 : insertBefore\n3 : removeAfter\n4 : removeBefore\n5 : exit");
            br = new BufferedReader(new InputStreamReader(System.in));
            try{
                switch (br.readLine()){
                    case ("1"):
                        if (firstNode == 1){
                            System.out.println("Enter a string: ");
                            String newObj = br.readLine();
                            Node newNode = new Node(newObj);
                            insertAfter("null", newObj);
                            firstNode = 0;
                        } else{
                            System.out.println("After which string:");
                            String after = br.readLine();
                            System.out.println("Enter a string: ");
                            String newObj = br.readLine();
                            insertAfter(after, newObj);
                        }
                        break;
                    case ("5"):
                        running = 0;
                    default:
                        break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
