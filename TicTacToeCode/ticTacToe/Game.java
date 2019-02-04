package ticTacToe;

import java.io.*;

/**
 * Game class that catches null exceptions during the declaration of
 * player names at the start of the game and appoints the referee class
 * to run the game
 */
public class Game implements Constants {

	private Board theBoard;
	private Referee theRef;
	/**
	 * Constructor that initializes an instance of the game board
	 */
    public Game( ) {
        setTheBoard(new Board());
	}
    /**
     * Method to appoint referee class, and run the game through
     * that class
     * @param r: represents referee object
     * @throws IOException checks null value
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
   
	/**
	 * Assigns values to players and ref, and receives user inputs
	 * for player names. Also assigns the predefined constants
	 * in the Constant class to each character to set up the game initially
	 * @param args: includes arguments like referee, players, and game state
	 * @throws IOException checks null value
	 */
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.getTheBoard());
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.getTheBoard());
		
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
