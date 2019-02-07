package TicTacToeClient;

/** This class consists of the referee that sets the opponent for each TicTacToe.Player and starts the game by passing
 * the first move to the 'X'TicTacToe.Player. An object of this class contains the following information: a TicTacToe.Player
 * object for the 'X' player, a TicTacToe.Player object for the 'O' player, and a TicTacToe.Board object. This class also
 * provides a default constructor to create a TicTacToe.Referee object, instance methods for running the game,
 * setting the board, and setting each 'X' and 'O' TicTacToe.Player as explained in the given comments, below.
 */
public class Referee {
	
	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	
	/** Default constructor to construct a TicTacToe.Referee object. */
	public Referee () {
	}
	
	
	/** Method responsible for running the game. It calls the setOpponent() method to set the opponent
	 * of each 'X' and 'O' TicTacToe.Player. Then it initiates the game by displaying the board and calling the
	 * play method for the X player who is always the first player. */
	public void runTheGame() {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		System.out.println("TicTacToe.Referee started the game...\n");
		board.display();
		xPlayer.play();
	}
	
	/** Setter method to set the board for the game. */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/** Setter method to set the 'O' player for the game. */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	/** Setter method to set the 'O' player for the game. */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}

	
}