

/**
 * Class that creates and populates the tic tac toe board on which the game
 * runs. Contains methods to check for win conditions for each player, 
 * add marks to the board, and to display the board after each player turn
 */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;
	/**
	 * Method to populate the board with empty spaces
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	public void clearBoard() {
		markCount = 0;
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	 * Method to return the mark at the specified row/column
	 * @param row: represents the user selected row number
	 * @param col: represents the user selected column number
	 * @return: returns the position of the board where mark is found
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	/**
	 * Method to check if board is fully populated with X/O values
	 * @return: returns true if counter is 9 (9 moves), false otherwise
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	/**
	 * Method to check player X win condition
	 * @return: returns true if x player wins and false if they lose
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	/** 
	 * Method to check player O win condition
	 * @return: returns true of o player wins and false if they lose
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	/**
	 * Method to print display of board to console after each player turn
	 */
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
	/**
	 * Method to add X/O mark to board based on player input
	 * @param row: represents row number to add mark
	 * @param col: represents column number to add mark
	 * @param mark: represents mark to add (O/X)
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
	/**
	 * Method to fully clear the tic tac toe board 
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	/**
	 *  * Method to check for winners on the current instance of the board
	 * (ie 3 in a row of the same symbol)
	 * @param mark: indicates mark of winner to check conditions
	 * @return: returns 1 if winner or 0 if loser
	 */
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
	/**
	 * Method to print the game board column headers
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}
	/** 
	 * Method to help construct the structure of the game board (hyphens)
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	/** 
	 * Method to help construct the structure of the game board (spaces)
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
