import java.util.Scanner;
/**
 * Player class that sets the opponents for the game, and contains methods
 * for receiving user inputs for each turn of the game, helps start
 * and end the game.
 */
public class Player {
	private String name;
	private char mark;
	private Player opponent;
	private Board board;
	/**
	 * Player constructor that assigns the name and associated (O/X)
	 * mark for a player
	 * @param name: name of player
	 * @param mark: mark (O/X) of player
	 */
	public Player(String name, char mark) {
		this.name = name;
		this.mark = mark;

	}
	/**
	 * Method that keeps the game running as long as the win or game
	 * over conditions aren't met after each turn. When these conditions are
	 * met, an output message will be printed
	 */
	public void play() {
		if(board.xWins() == false && board.oWins() == false && board.isFull() == false){
			makeMove();
			board.display();
		}
		else if(board.xWins()) {
			System.out.println(opponent.name + " is the winner!");
			System.exit(0);
		}
		else if(board.oWins()) {
			System.out.println(opponent.name + " is the winner!");
			System.exit(0);
		}
		else if(board.isFull()) {
			System.out.println("The board is full. This game is a draw!");
			System.exit(0);
		}
		
		opponent.play();
	}
	/**
	 * Method that receives user inputs for each turn from each player
	 */
	public void makeMove() {
		
		
		
		Scanner reader = new Scanner(System.in);
		System.out.println(name + ", what row should your next " + mark + " be placed in?");
		int row = reader.nextInt();
		System.out.println(name + ", what column should your next " + mark + " be placed in?");
		int column = reader.nextInt();
		board.addMark(row, column, mark);
	}
	/**
	 * setter method for setting the opposing player
	 * @param opponent: represents opposing player
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	/**
	 * setter method for setting the initial board
	 * @param theBoard: parameter representing the board set up
	 */
	public void setBoard(Board theBoard) {
		this.board = theBoard;
	}

}
