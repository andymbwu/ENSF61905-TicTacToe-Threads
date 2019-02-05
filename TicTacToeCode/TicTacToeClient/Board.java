package TicTacToeClient;

/** This class consists of the board required to play a tic-tac-toe game.
 * An object of this class contains the following information: a 2D array of characters
 * to represent the board, and an integer to count the total number of marks on the board. 
 * This class also provides a constructor to create a Board object and initialize all values
 * to the ' ' constant character and instance methods to check if the 'X' or 'O' players are the winner,
 * to check if the board is full, to display the board to the console, to add a mark to the board and to clear
 * the board as explained in the given comments, below. */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;

	/** Default constructor that creates a new 2D array of characters of length 3x3 and sets the value
	 * of all characters to ' '. */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/** Getter method to retrieve the character in an element of the 2D array from an input row and an
	 * input column. */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/** A method that checks if the board is full. The board contains 9 spaces (3x3) so if the 
	 * member variable markCount is equal to 9 then the board is full. */
	public boolean isFull() {
		return markCount == 9;
	}

	/** A method that checks if the 'X' player has won the game by calling the checkWinner() method. */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/** A method that checks if the 'O' player has won the game by calling the checkWinner() method. */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	
	/** The method used to display the game board. It calls displayColumnHeaders(), addHyphens(), and 
	 * addSpaces() to display the column labels, and the rectangular shape of the board, and prints 
	 * the row labels also. */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/** This method adds a mark to the 2D character array. It has input parameters for the row and 
	 * column to add the mark too, and an input parameter for the mark character to be added. */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/** This method loops through the 2D character array and sets each elements value to ' '. */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/** This method is used to check the board for a winner. It checks for all possible winning combinations
	 * using a character as an input parameter. Row by row it will check if all columns contain the same input 
	 * character, then column by column if all rows contain the same character, then check if both diagonals
	 * contain the same character. If any of these are true, a value of 1 is returned and used in the 
	 * xWins() or oWins() method that called it to declare a winner. */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/** Method called by display() to print the columns of the board to the console. */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/** Method called by display() to print the lines of the board to the console. */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	
	/** Method called by display() to print the spaces between the lines of the board to the console. */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
