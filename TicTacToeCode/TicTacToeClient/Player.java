package TicTacToeClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/** This class consists of the players that make the moves in the tic-tac-toe game.
 * An object of this class contains the following information: a name, a Board object, a mark 
 * ('X' or 'O'), and a Player object. This class also provides a constructor to create a Player object, 
 * instance methods for getting and setting Players, setting the  opponent and board, and
 * playing the game as explained in the given comments, below. */
public class Player {
	private String name;
	private Board board;
	private int row;
	private int column;
	private char mark;
	private Player opponent;

	private BufferedReader in;
	private PrintWriter out;

	
	/** Constructor that creates a new Player from input arguments of name and character ('X' or 'O'). */
	public Player(String name, char mark, BufferedReader in, PrintWriter out) {
		this.name = name;
		this.mark = mark;
		this.out = out;
		this.in = in;
	}


	/** Method that is responsible for playing the game. As long as neither player has won the game
	 * or the board is not full it calls the Player method makeMove() then displays the board. 
	 * It checks if the X or the O player have won the game or if the board is full. 
	 * If no one has won or the board is not full it passes the move to the
	 * opponent and allows them to make a move. It then displays the board and checks the board again, 
	 * and the whole method will repeat until one player wins or the board is full and the game is a tie. */
	public void play() {
		while(board.xWins() == false && board.oWins() == false && board.isFull() == false) {
			System.out.println("play() is working");
			makeMove();
			board.display();
			if (board.xWins()) {
				System.out.println("THE GAME IS OVER: " + name + " is the winner!");
				board.showWinner(name);
				break;
			} else if (board.oWins()) {
				System.out.println("THE GAME IS OVER: " + name + " is the winner!");
				board.showWinner(name);
				break;
			} else if (board.isFull()) {
				System.out.println("THE GAME IS OVER: It is a tie!");
				board.showWinner("Nobody");
				break;
			}
			opponent.play();
		}
		
	}

	/** This method asks the player to make a move by entering the row and column numbers, 
	 * and puts an 'X' or 'O' mark on the board by calling addMark() in class Board. */
	public void makeMove(int row, int column, char mark) {
		board.addMark(row, column, mark);

	}

	/** Setter method to set the opponent of the current player. */
	public void setOpponent(Player p) {
		opponent = p;
	}

	/** Setter method to set the board for the game. */
	public void setBoard(Board theBoard) {
		this.board = theBoard;
	}
}
