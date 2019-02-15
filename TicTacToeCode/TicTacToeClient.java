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
    private BufferedReader in;

    private MainGUI frame;

    /**
     * Constructor for initiating the server with a port number
     *
     * @param serverName: Name of server
     * @param portNumber: TicTacToe.Server port number
     */
    public TicTacToeClient(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
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
    public void communicate() throws Exception {
        String response = "";
        if (response.startsWith("ANNOUNCE")){
            response = response.replace("ANNOUNCE","");
            frame.updateToStatusField(response);
        }
            try {
                while(true) {
                    response = in.readLine();
//                    if(response.contains("RESET")){
//                        frame.restartGame();
//                    }
                    if (response.startsWith("ANNOUNCE")) {
                        response = response.replace("ANNOUNCE", "");
                        frame.updateToStatusField(response);
                    }
                    if(response.startsWith("END")){
                        frame.disableButtons();
                    }
                    if (response.startsWith("UPDATEMARK")) {
                        String letter = response.split(" ")[1];
                        int num_square = Integer.parseInt(response.split(" ")[2]);
                        switch(num_square){
                            case 1:
                                frame.setBtnTopLeft(letter);
                                break;
                            case 2:
                                frame.setBtnTopMid(letter);
                                break;
                            case 3:
                                frame.setBtnTopRight(letter);
                                break;
                            case 4:
                                frame.setBtnMidLeft(letter);
                                break;
                            case 5:
                                frame.setBtnMidMid(letter);
                                break;
                            case 6:
                                frame.setBtnMidRight(letter);
                                break;
                            case 7:
                                frame.setBtnBotLeft(letter);
                                break;
                            case 8:
                                frame.setBtnBotMid(letter);
                                break;
                            case 9:
                                frame.setBtnBotRight(letter);
                                break;
                        }
                    }
                    if (response.startsWith("DISABLE")){
                        frame.disableButtons();
                    }
                    if(response.startsWith("ENABLE")){
                        frame.enableButtons();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    System.out.println("closing error: " + e.getMessage());
                }



        }

    }

        /**
         * Main method for running client
         * @param args
         * @throws IOException
         */
        public static void main (String[]args) throws IOException {
            //TicTacToeClient aClient = new TicTacToeClient("localhost", 9899);
            TicTacToeClient aClient = new TicTacToeClient("10.13.115.140", 9899);
            aClient.frame = new MainGUI(aClient.aSocket);
            aClient.frame.setVisible(true);
            try {
                aClient.communicate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

