

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static java.lang.Integer.parseInt;

/**
 * Defines ChatClient class and relevant methods and procedures.
 * @version 1.0
 * @author Vedant Nemane
 * @release 04/03/2020
 */
public class ChatClient {
    String serverAddress;
    BufferedReader inFromUser, inFromServer;
    PrintWriter outToServer;

    // port declared as static since port for server is singular
    private static int port;

    public ChatClient(String serverAddress){
        this.serverAddress = serverAddress;
    }

    /**
     * Method run sets up I/O variables for each client/thread and processes I/O from and to server.
     * @Exception IOException
     */
    private void run(){
        try{
            Socket socket  = new Socket(serverAddress, port);
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            inFromUser = new BufferedReader(new InputStreamReader(System.in));
            outToServer = new PrintWriter(socket.getOutputStream(), true);
            String line;
            System.out.println("Enter /quit to exit.");
            while(true) {
                // If input from server not equal to null
                while (inFromServer.ready()) {
                    String lineFromServer = inFromServer.readLine();
                    if (lineFromServer.equals("EXIT")){
                        System.out.println("Client shutdown successfully.");
                        System.exit(1);
                    }
                    // Under normal input, print line received from server
                    System.out.println(lineFromServer);
                }
                // If user input not equal to null
                if (inFromUser.ready()) {
                    line = inFromUser.readLine();
                    // If a sole client wants to leave, then "/quit" allows this
                    if (line.equals("/quit")) {
                        System.out.println("Client shutdown successfully.");
                        outToServer.println("A client left.");
                        // breaks out of while loop and hence stops the client running
                        break;
                    }
                    // If a client messaged "EXIT", then ALL other clients would shut down- that's not allowed
                    // So a client can never send "EXIT" as a message, and it is not broadcast
                    if (line.equals("EXIT")){
                        System.out.println("A client cannot shutdown the server-");
                        System.out.println("write this in the terminal that has the server running!");
                        System.out.println("The message was NOT sent.");
                    } else{
                        // If the line is not program breaking, then let it be sent to the server
                        outToServer.println(line);
                    }
                }
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Sets up port and address for each client.
     * @param args:
     *            Contains list of parameters, cycled through with a for loop.
     * @throws IOException for BufferedReader and other input.
     * @Exception ArrayIndexOutOfBoundsException thrown when for loop tries to read ahead and finds null/hits max length for array.
     */
    public static void main(String[] args) throws IOException{
        // Default values given to address and port initially
        ChatClient client = null;
        port = 14001;
        client = new ChatClient("localhost");

        // For loop goes through all the arguments, as long as there are arguments
        for (int counter = 0; counter < args.length; counter = counter + 1){
            if (args[counter].equals("-cca")){
                // Since the program tries to find the next element in args[], it must be in a try-catch statement
                // We also know that an index error would be common, so we can account for that
                try {
                    client = new ChatClient(args[counter + 1]);
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
        // Finally, the client must be run (not start, since this is not a runnable) with the values given
        client.run();
    }
}
