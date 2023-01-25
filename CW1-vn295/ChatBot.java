

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * Contains the code for reading and replying with some automated outputs.
 * @version 1.0
 * @author Vedant Nemane
 * @release 06/03/2020
 */
public class ChatBot {

    private String serverAddress;
    BufferedReader inFromServer;
    PrintWriter outToServer;

    private static int port;

    public ChatBot(String serverAddress){ this.serverAddress = serverAddress; }

    private void run(){
        try{
            // define the input output variables
            Socket socket = new Socket(serverAddress, port);
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToServer = new PrintWriter(socket.getOutputStream(), true);
            // Lists store basic responses
            List<String> greetings = new ArrayList<String>();
            greetings.add("Bot: Hello to you!");
            greetings.add("Bot: Good evening- is it really that late?");
            greetings.add("Bot: Good afternoon to you too!");
            greetings.add("Bot: Hi to you too!");
            greetings.add("Bot: Good morning! Let's start the day well!");

            List<String> questions = new ArrayList<String>();
            questions.add("Bot: Really?");
            questions.add("Bot: Wait you think so?");
            questions.add("Bot: No way seriously?");
            questions.add("Bot: Maybe?");

            List<String> response = new ArrayList<String>();
            response.add("Bot: I think so too.");
            response.add("Bot: Yeah sure.");
            response.add("Bot: Go ahead with that then.");
            response.add("Bot: I don't think so.");
            response.add("Bot: Hmmm yeah no.");
            response.add("Bot: Perhaps not.");

            // Let the user running chatbot know that it is running
            System.out.println("ChatBot is running.");

            while(true){
                while(inFromServer.ready()){
                    String output = null;
                    String lineFromServer = inFromServer.readLine();
                    Random rand = null;
                    if ((lineFromServer.toLowerCase().contains("hello")) || (lineFromServer.toLowerCase().contains("hi"))){
                        rand = new Random();
                        int randomnumber = rand.nextInt(5);
                        output = greetings.get(randomnumber);
                    } else if(lineFromServer.contains("?")){
                        rand = new Random();
                        int randomnumber = rand.nextInt(6);
                        output = response.get(randomnumber);
                    } else if(lineFromServer.equals("EXIT")){
                        System.out.println("Bot shutdown successfully.");
                        System.exit(1);
                    } else {
                        rand = new Random();
                        int randomnumber = rand.nextInt(4);
                        output = questions.get(randomnumber);
                    }
                    outToServer.println(output);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Sets up the address and port for the chat bot.
     * @param args:
     *            Contains list of parameters, cycled through with a for loop.
     * @throws IOException for BufferedReader and other input.
     * @Exception ArrayIndexOutOfBoundsException thrown when for loop tries to read ahead and finds null/hits max length for array.
     */
    public static void main(String args[]){
        ChatBot bot = null;
        port = 14001;
        bot = new ChatBot("localhost");
        for (int counter = 0; counter < args.length; counter = counter + 1){
            if (args[counter].equals("-cca")){
                // Since the program tries to find the next element in args[], it must be in a try-catch statement
                // We also know that an index error would be common, so we can account for that
                try {
                    bot = new ChatBot(args[counter + 1]);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Found nothing after -cca, you sure there isn't a typo?");
                } catch (Exception ex){
                    System.out.println("Something went wrong.");
                }
            }
            if (args[counter].equals("-ccp")){
                // Same principle applies as for the other half of the for loop
                try{
                    port = parseInt(args[counter + 1]);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Found nothing after -ccp, you sure there isn't a typo?");
                } catch (Exception ex){
                    System.out.println("Something went wrong.");
                }
            }
        }
        bot.run();
    }
}
