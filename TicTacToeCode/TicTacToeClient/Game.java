package TicTacToeClient;

import java.io.*;
import java.net.Socket;

/** This class consists of the game requirements to play a tic-tac-toe game.
 * An object of this class contains the following information: a Board object 
 * and a Referee object. This class also provides a constructor to create a Board object, 
 * and an instance method to appoint the referee as explained in the given  
 * comments, below. This class implements the interface Constants to access
 * constant characters 'X' and 'O' and ' '. */
public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;
	
	private Socket xSocket;
	private Socket oSocket;
	
	
	/** Default constructor for class Game that creates a new Board and assigns it to the 
	 * member object theBoard in the class Game. */
    public Game(Socket x, Socket o) {
    	
        this.xSocket = x;
        this.oSocket = o;
        run();
	}
    
    public Game() { 
    	setTheBoard(new Board());
    }
    
    /** Method that takes a referee as an input argument, assigns it to the member object theRef  
	 * in class Game, and calls the member method runTheGame() of class Referee. Contains an exception
	 * handler that will avoid crashing the program if an I/O exception occurs. */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }    
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Referee theRef;
		Player xPlayer, oPlayer;
		Game theGame = new Game();
		

		xPlayer = new Player(name, LETTER_O);
		oPlayer = new Player(name, LETTER_O);
		
		
		xPlayer.setBoard(theGame.getTheBoard());
		oPlayer.setBoard(theGame.getTheBoard());
		
		
		theRef = new Referee();
		theRef.setBoard(theGame.getTheBoard());
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
		
	}
    
    /** Main function of the class Game. It is responsible for creating two new Player objects for X and O, 
     * receiving user input to assign names to the Player objects, setting the board and setting the referee.
     * It then calls a member method of class Game to run the game. Contains an exception handler that will 
     * avoid crashing the program if an I/O exception occurs.*/
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
//		Game theGame = new Game();
//		stdin = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("\nPlease enter the name of the \'X\' player: ");
//		String name= stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}

//		xPlayer = new Player(name, LETTER_X);
//		xPlayer.setBoard(theGame.getTheBoard());
		
//		System.out.print("\nPlease enter the name of the \'O\' player: ");
//		name = stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}
		
//		oPlayer = new Player(name, LETTER_O);
//		oPlayer.setBoard(theGame.getTheBoard());
		
		theRef = new Referee();
		theRef.setBoard(theGame.getTheBoard());
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}

	public Board getTheBoard() {
		return theBoard;
	}

	public void setTheBoard(Board theBoard) {
		this.theBoard = theBoard;
	}


}
