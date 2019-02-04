package ticTacToe;

/**
 * Referee class for overseeing the tic tac toe game, and initializing
 * the start of the game (setting player 1 and player 2) and displaying board
 */
public class Referee {

	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	private MainGUI gui;
	
	/**
	 * default constructor for Referee Class
	 */
	public Referee() {}
	
	/**
	 * Method for starting the first run of the year
	 */
	void runTheGame() {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
	}
	/**
	 * setter method for game board
	 * @param board: represents state of board
	 */
	void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * setter method for player o
	 * @param oPlayer: represents player with mark O
	 */
	void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	/**
	 * setter method for player x
	 * @param xPlayer: represents player with mark X
	 */
	void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
}
