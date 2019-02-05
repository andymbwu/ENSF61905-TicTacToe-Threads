package TicTacToeClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client class that receives a user input and reads it into a socket which writes to a server
 */
public class TicTacToeClient {
    private PrintWriter out;
    private Socket aSocket;
    private BufferedReader stdIn;
    private BufferedReader in;

    /**
     * Constructor for initiating the server with a port number
     * @param serverName: Name of server
     * @param portNumber: Server port number
     */
    public TicTacToeClient(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            out = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Method that receives a word from the user and writes to the socket
     * and returns a response from the server through the socket
     */
    public void communicate() {
        String userIn = "";
        String response = "";
        while (!userIn.equals("QUIT")) {
            try {
                System.out.println("Player, please enter your name");
                userIn = stdIn.readLine();
                out.println(userIn);//writing the user input to the socket
                response = in.readLine();//reading server's response from the socket
                System.out.println(response);
            } catch (IOException e) {
                System.out.println("Sending error: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("what is going on " + e.getMessage());
                break;
            }
        }
        try {
            stdIn.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("closing error: " + e.getMessage());
        }
    }

    /**
     * Main method for running client
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TicTacToeClient aClient = new TicTacToeClient("localhost", 9898);
        aClient.communicate();
    }
}
