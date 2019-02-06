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
    private BufferedReader in;
    private PrintWriter out;
    private Socket aSocket;
    private BufferedReader stdIn;
    private MainGUI myGUI;

    /**
     * Constructor for initiating the server with a port number
     * @param serverName: Name of server
     * @param portNumber: Server port number
     */
    public TicTacToeClient(String serverName, int portNumber) {
        this.myGUI = new MainGUI(aSocket);
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
        try {
            myGUI.setVisible(true);
            myGUI.setSelectX();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String userIn = "";
        String response = "";
        String move = "";
        System.out.println("Player, please enter your name");
        try {
//            userIn = stdIn.readLine();
//            out.println(userIn);
            response = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(response.contains("Welcome"))
//            System.out.println(response);//writing the user input to the socket
        while(true) {
            int num_case = 0;
            String mark;
            try {
                num_case = Integer.parseInt(in.readLine().split(" ")[0]);
                mark = in.readLine().split(" ")[1];
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (num_case) {
                case 1:
                    myGUI.butnTopLeft.setText(mark);

                    break;
                case 2:
                    monthString = "February";
                    break;
                case 3:
                    monthString = "March";
                    break;
                case 4:
                    monthString = "April";
                    break;
                case 5:
                    monthString = "May";
                    break;
                case 6:
                    monthString = "June";
                    break;
                case 7:
                    //
                case 8:
                    //
                case 9:
                    //


//            try {
//                response = in.readLine();//reading server's response from the socket
//                if(response.contains("col 1")) {
//                    String board = response + "\n";
//                    for(int i = 0; i < 13; i++) {
//                        board += in.readLine() + "\n";
//                    }
//                    System.out.println(board);
//                }
//                if(response.contains("what")){
//                    System.out.println(response);
//                    move = stdIn.readLine();
//                    out.println(move);
//                }
//                if(response.contains("winner"))
//                    System.out.println(response);

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
