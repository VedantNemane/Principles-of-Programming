

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;
import java.util.HashSet;

//useful for converting from string to integer for reading in port values from terminal
import static java.lang.Integer.parseInt;

/**
 * Defines the ChatServer class and behaviour of server and client handler.
 * @version 1.0
 * @author Vedant Nemane
 * @release 04/03/2020
 */
public class ChatServer {
    // Instantiates a HashSet of writers so that it can be cycled through when outputting what the server receives
    private static Set<PrintWriter> writers = new HashSet<>();
    private static ServerSocket server;

    /**
     * Main thread. Constantly accepts new connections, waits for new connections.
     * @param args
     *      Arguments passed by user for custom port server-side.
     */

    public static void main(String[] args){
        try{
            // Notifies user if custom port value was accepted, otherwise uses default port
            server = new ServerSocket(parseInt(args[1]));
            System.out.println("Custom server port in use.");
        }catch(Exception e) {
            try {
                server = new ServerSocket(14001);
            } catch (Exception ex) {}
        }
        System.out.println("Server is running...");

        try{
            // setReuseAddress is useful for spamming run and stop because normally the port is not instantly closed
            server.setReuseAddress(true);
            // Figured that the main thread keeps waiting for new connections, so need another thread to run
            // to read in what the user types into the console for the server
            CheckTerminalInput newCheck = new CheckTerminalInput();
            new Thread(newCheck).start();

            ChatBotHandler botHandler = new ChatBotHandler();

            // The main thread is always listening for clients
            while(true){
                Socket client;
                try{
                    client = server.accept();
                } catch (SocketException e){
                    break;
                }
                System.out.println("New client connected at: " + client.getInetAddress().getHostAddress());
                // Create a new handler for the new client
                ClientHandler clientSock = new ClientHandler(client);
                // Give the handler its own thread so that concurrency isn't an issue
                // It is possible to have the server host a large number of clients
                new Thread(clientSock).start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a runnable to keep reading console input at terminal. It is given its own thread.
     * @Exception IOException can be thrown under normal usage.
     */
    private static class CheckTerminalInput implements Runnable{
        private BufferedReader inFromUser;
        public void run(){
            try{
                inFromUser = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while (true){
                    // If input from server is "EXIT", then all clients are broadcast this, and they shut down
                    if ((line = inFromUser.readLine()).equals("EXIT")){
                        for (PrintWriter writer : writers){
                            writer.println("EXIT");
                        }
                        System.out.println("Server shutdown successfully.");
                        System.exit(1);
                    } else {
                        // server user can broadcast message to all clients
                        for (PrintWriter writer : writers){
                            writer.println("Server user says: " + line);
                        }
                        // Note: server user CANNOT see client messages
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * A runnable created for each client and given its own thread.
     * Since every client is independent, and input is not stored, concurrency errors cannot occur.
     * The same applies for atomic access.
     * @Exception IOException
     */
    private static class ClientHandler implements Runnable{
        private Socket clientSocket;
        private BufferedReader inFromClient;
        private PrintWriter outToClient;

        // Gives this instance of ClientHandler the socket passed to it
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        // Method called by "start()"
        public void run() {
            try{
                inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                // If this code runs, then a client has successfully connected, so notify all clients:
                // since the server already knows, it is not notified
                for (PrintWriter writer : writers){
                    if (writer != outToClient){
                        writer.println("A client has joined.");
                    }
                }
                // Add the new PrintWriter to the set of all PrintWriters for broadcasting
                writers.add(outToClient);
                String input = null;

                while (true){
                    input = inFromClient.readLine();
                    // Send these lines to every client apart from the one who wrote it
                    for (PrintWriter writer : writers){
                        if ((writer != outToClient) && (input != null)){
                            writer.println(input);
                        }
                    }
                    input = null;
                }
            } catch (Exception e){
                System.out.println(e);
            } finally {
                if (outToClient != null){
                    writers.remove(outToClient);
                }
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * Implements a handler class for the chatbot.
     * @Exception IOException
     */
    private static class ChatBotHandler implements Runnable{
        private Socket botSocket;
        private BufferedReader inFromBot;
        private PrintWriter outToBot;

        public void run(){
            try{
                outToBot = new PrintWriter(botSocket.getOutputStream());
                inFromBot = new BufferedReader(new InputStreamReader(botSocket.getInputStream()));
                // The bot still counts as a "writer" and has to receive messages too
                writers.add(outToBot);

                String input = null;

                while(true){
                    input = inFromBot.readLine();
                    for (PrintWriter writer : writers){
                        if ((writer != outToBot) && (input != null)){
                            writer.println(input);
                        }
                    }
                    input = null;
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (outToBot != null){
                    writers.remove(outToBot);
                }
                try {
                    botSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }
}
