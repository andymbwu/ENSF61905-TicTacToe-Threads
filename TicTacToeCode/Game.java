import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class consists of the game requirements to play a tic-tac-toe game.
 * An object of this class contains the following information: a TicTacToe.Board object
 * and a TicTacToe.Referee object. This class also provides a constructor to create a TicTacToe.Board object,
 * and an instance method to appoint the referee as explained in the given
 * comments, below. This class implements the interface TicTacToe.Constants to access
 * constant characters 'X' and 'O' and ' '.
 */
public class Game implements Constants, Runnable {

    private Board theBoard;
    private Referee theRef;
    private Player oPlayer;
    private Player xPlayer;

    private Socket xSocket;
    private PrintWriter xSocketOut;
    private BufferedReader xSocketIn;

    private Socket oSocket;
    private PrintWriter oSocketOut;
    private BufferedReader oSocketIn;


    /**
     * Default constructor for class TicTacToe.Game that creates a new TicTacToe.Board and assigns it to the
     * member object theBoard in the class TicTacToe.Game.
     */
    public Game(Socket xSocket, Socket oSocket) {
        this.xSocket = xSocket;
        this.oSocket = oSocket;

        try {
            xSocketIn = new BufferedReader((new InputStreamReader(xSocket.getInputStream())));
            oSocketIn = new BufferedReader((new InputStreamReader(oSocket.getInputStream())));
            xSocketOut = new PrintWriter(xSocket.getOutputStream(), true);
            oSocketOut = new PrintWriter(oSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTheBoard(new Board(xSocket,oSocket));
//        System.out.println("TicTacToe.Game constructor initialized");
    }

    public Game() { }

    /**
     * Method that takes a referee as an input argument, assigns it to the member object theRef
     * in class TicTacToe.Game, and calls the member method runTheGame() of class TicTacToe.Referee. Contains an exception
     * handler that will avoid crashing the program if an I/O exception occurs.
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.runTheGame();
    }

    @Override
    public void run() {

//		Referee theRef;
        String xInput = "";
        String oInput = "";
        try {
            while (true) {
                xInput = xSocketIn.readLine();
                if (xInput.startsWith("PLAYERNAME")) {
                    xInput = xInput.replace("PLAYERNAME", "");
                    xPlayer = new Player(xInput, LETTER_X, xSocketIn, xSocketOut);
                    xPlayer.setOpponent(oPlayer);
                    xPlayer.setBoard(this.getTheBoard());
                    theBoard.showToAllPlayers("ANNOUNCE" + xPlayer.getName() + "(Player X) has entered the game");
                    System.out.println(xPlayer.getName() + " has entered the game");
                } else if (xInput.startsWith("PLAYERMOVE")) {
                    int num_square = Integer.parseInt(xInput.replace("PLAYERMOVE", ""));
                    switch (num_square) {
                        case 1:
                            theBoard.addMark(0, 0, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 2:
                            theBoard.addMark(0, 1, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 3:
                            theBoard.addMark(0, 2, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 4:
                            theBoard.addMark(1, 0, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 5:
                            theBoard.addMark(1, 1, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 6:
                            theBoard.addMark(1, 2, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 7:
                            theBoard.addMark(2, 0, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 8:
                            theBoard.addMark(2, 1, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                        case 9:
                            theBoard.addMark(2, 2, LETTER_X);
                            theBoard.updateMarks(LETTER_X, num_square);
                            break;
                    }
                    if(theBoard.xWins()) {
                        theBoard.showToAllPlayers(xPlayer.getName() + " is the Winner!");
                        theBoard.endGame();
                    } else if (theBoard.isFull()) {
                        theBoard.showToAllPlayers("The game is a TIE!");
                        theBoard.endGame();
                    } else {
                        theBoard.disableXButtons();
                        theBoard.enableOButtons();
                    }
                }

                    oInput = oSocketIn.readLine();
                    if (oInput.startsWith("PLAYERNAME")) {
                        oInput = oInput.replace("PLAYERNAME", "");
                        oPlayer = new Player(oInput, LETTER_O, xSocketIn, oSocketOut);
                        oPlayer.setOpponent(xPlayer);
                        oPlayer.setBoard(this.getTheBoard());
                        theBoard.showToAllPlayers("ANNOUNCE" + oPlayer.getName() + "(Player O) has entered the game");
                        System.out.println(oPlayer.getName() + " has entered the game");
                        theBoard.disableOButtons();
                    } else if (oInput.startsWith("PLAYERMOVE")) {
                        int num_square = Integer.parseInt(oInput.replace("PLAYERMOVE", ""));
                        switch (num_square) {
                            case 1:
                                theBoard.addMark(0, 0, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 2:
                                theBoard.addMark(0, 1, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 3:
                                theBoard.addMark(0, 2, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 4:
                                theBoard.addMark(1, 0, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 5:
                                theBoard.addMark(1, 1, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 6:
                                theBoard.addMark(1, 2, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 7:
                                theBoard.addMark(2, 0, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 8:
                                theBoard.addMark(2, 1, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                            case 9:
                                theBoard.addMark(2, 2, LETTER_O);
                                theBoard.updateMarks(LETTER_O, num_square);
                                break;
                        }
                        if(theBoard.oWins()) {
                            theBoard.showToAllPlayers(oPlayer.getName() + " is the Winner!");
                            theBoard.endGame();
                        }
                        else if (theBoard.isFull()) {
                            theBoard.showToAllPlayers("The game is a TIE!");
                            theBoard.endGame();
                        }
                        else {
                            theBoard.disableOButtons();
                            theBoard.enableXButtons();
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }


//		Player xPlayer = new Player(xLine, LETTER_X, xSocketIn, xSocketOut);
//		Player oPlayer = new Player(oLine, LETTER_O, oSocketIn, oSocketOut);


//		xPlayer.setBoard(this.getTheBoard());
//		oPlayer.setBoard(this.getTheBoard());

//		theRef = new Referee();
//		theRef.setBoard(this.getTheBoard());
//		theRef.setoPlayer(oPlayer);
//		theRef.setxPlayer(xPlayer);

//        try {
//            this.appointReferee(theRef);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    /**
     * Main function of the class TicTacToe.Game. It is responsible for creating two new TicTacToe.Player objects for X and O,
     * receiving user input to assign names to the TicTacToe.Player objects, setting the board and setting the referee.
     * It then calls a member method of class TicTacToe.Game to run the game. Contains an exception handler that will
     * avoid crashing the program if an I/O exception occurs.
     */
    public static void main(String[] args) throws IOException {
        Referee theRef;
        Player xPlayer, oPlayer;
        BufferedReader stdin;
//		TicTacToe.Game theGame = new TicTacToe.Game();
//		stdin = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("\nPlease enter the name of the \'X\' player: ");
//		String name= stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}

//		xPlayer = new TicTacToe.Player(name, LETTER_X);
//		xPlayer.setBoard(theGame.getTheBoard());

//		System.out.print("\nPlease enter the name of the \'O\' player: ");
//		name = stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}

//		oPlayer = new TicTacToe.Player(name, LETTER_O);
//		oPlayer.setBoard(theGame.getTheBoard());
//
//		theRef = new TicTacToe.Referee();
//		theRef.setBoard(theGame.getTheBoard());
//		theRef.setoPlayer(oPlayer);
//		theRef.setxPlayer(xPlayer);
//
//        theGame.appointReferee(theRef);
    }

    public Board getTheBoard() {
        return theBoard;
    }

    public void setTheBoard(Board theBoard) {
        this.theBoard = theBoard;
    }


}
