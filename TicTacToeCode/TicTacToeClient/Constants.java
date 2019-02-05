package TicTacToeClient;

/** This interface contains the constants for use by the game. Each time the game is played a new board
 * (2D array of characters) is created and initialized with spaces, the SPACE_CHAR constant. The X player
 * and O player are only allowed to mark the board with the LETTER_X ('X') and LETTER_O ('O') respectively. */
public interface Constants {
	static final char SPACE_CHAR = ' ';
	static final char LETTER_O = 'O';
	static final char LETTER_X = 'X';
}
