package TicTacToeClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class contains all the necessary objects and logic to run a GUI version
 * of the tic tac toe game developed in Lab 3. It allows two human players to
 * play against each other. Each player enters their name and then one user
 * presses the start button to begin the game. Messages for each players turn
 * are displayed to the main frame text area. When a player wins or the game
 * ends in a tie, a message is displayed to the users and the program is
 * terminated.
 */
public class GUI implements Constants {

	private JFrame mainFrame;
	private JPanel gridPanel;
	private JPanel playerOneTextPanel;
	private JPanel playerTwoTextPanel;
	private String xPlayerName;
	private String oPlayerName;
	private Player xPlayer;
	private Referee theRef;
	private Game theGame;
	private Player oPlayer;
	private boolean xTurn;
	private String messageWindowString;

	public GUI() throws IOException {
		/**
		 * Create the main frame for running the tic tac toe game.
		 */
		mainFrame = new JFrame("Tic Tac Toe Game");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setSize(450, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

		/**
		 * Create a grid panel of buttons to represent the game board to the users, and
		 * add it to the main frame.
		 */
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(3, 3));
		JButton[] buttons = new JButton[9];
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton("");
			buttons[i].setPreferredSize(new Dimension(75, 75));
			;
			gridPanel.add(buttons[i]);
		}
		mainFrame.add(gridPanel);
		gridPanel.setVisible(false);

		/**
		 * Create a label and field for messages to be printed for the users.
		 */
		JPanel messageWindowPanel = new JPanel();
		JLabel messageWindowLabel = new JLabel("Messages:");
		messageWindowString = "Players, please type your names below and press \nthe 'ENTER' key.";
		JTextArea messageText = new JTextArea(messageWindowString);
		messageText.setPreferredSize(new Dimension(300, 100));
		messageText.setEditable(false);
		messageWindowLabel.setLabelFor(messageText);
		messageWindowPanel.add(messageWindowLabel);
		messageWindowPanel.add(messageText);
		mainFrame.add(messageWindowPanel);

		/**
		 * Create a label and field for the 'X' player to enter their name.
		 */
		playerOneTextPanel = new JPanel();
		JLabel playerOneNameLabel = new JLabel("Please enter the name of the \'X\' player:");
		JTextField enterPlayerOneName = new JTextField(10);
		playerOneNameLabel.setLabelFor(enterPlayerOneName);
		playerOneTextPanel.add(playerOneNameLabel);
		playerOneTextPanel.add(enterPlayerOneName);
		mainFrame.add(playerOneTextPanel);

		/**
		 * Add an action listener to the 'X' Player name text field so that it is stored
		 * in memory when the 'Enter' button is pressed on the keyboard.
		 */
		enterPlayerOneName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				xPlayerName = enterPlayerOneName.getText();
				JOptionPane.showMessageDialog(null, "'X' player name recorded!");
			}
		});

		/**
		 * Create a label and field for the 'O' player to enter their name.
		 */
		playerTwoTextPanel = new JPanel();
		JLabel playerTwoNameLabel = new JLabel("Please enter the name of the \'O\' player:");
		JTextField enterPlayerTwoName = new JTextField(10);
		playerTwoNameLabel.setLabelFor(enterPlayerTwoName);
		playerTwoTextPanel.add(playerTwoNameLabel);
		playerTwoTextPanel.add(enterPlayerTwoName);
		mainFrame.add(playerTwoTextPanel);

		/**
		 * Add an action listener to the 'O' Player name text field so that it is stored
		 * in memory when the 'Enter' button is pressed on the keyboard.
		 */
		enterPlayerTwoName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				oPlayerName = enterPlayerTwoName.getText();
				JOptionPane.showMessageDialog(null, "'O' player name recorded!");
			}
		});

		/**
		 * A listener is added to the "Start Game" button. When it is clicked a message
		 * will be displayed in the text area prompting the 'X' player to begin.
		 */
		JButton startGame = new JButton("Start Game");
		mainFrame.add(startGame);

		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					playGame();
					messageWindowString = (xPlayerName + ", please click an empty box to place your mark.");
					messageText.setText(messageWindowString);
					startGame.setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * A listener is added to the [0][0] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[0].getText() == "") {
					buttons[0].setText("X");
					theGame.getTheBoard().addMark(0, 0, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[0].getText() == "") {
					buttons[0].setText("O");
					theGame.getTheBoard().addMark(0, 0, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [0][1] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[1].getText() == "") {
					buttons[1].setText("X");
					theGame.getTheBoard().addMark(0, 1, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[1].getText() == "") {
					buttons[1].setText("O");
					theGame.getTheBoard().addMark(0, 1, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [0][2] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[2].getText() == "") {
					buttons[2].setText("X");
					theGame.getTheBoard().addMark(0, 2, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[2].getText() == "") {
					buttons[2].setText("O");
					theGame.getTheBoard().addMark(0, 2, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [1][0] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[3].getText() == "") {
					buttons[3].setText("X");
					theGame.getTheBoard().addMark(1, 0, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[3].getText() == "") {
					buttons[3].setText("O");
					theGame.getTheBoard().addMark(1, 0, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [1][1] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[4].getText() == "") {
					buttons[4].setText("X");
					theGame.getTheBoard().addMark(1, 1, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[4].getText() == "") {
					buttons[4].setText("O");
					theGame.getTheBoard().addMark(1, 1, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [1][2] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[5].getText() == "") {
					buttons[5].setText("X");
					theGame.getTheBoard().addMark(1, 2, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[5].getText() == "") {
					buttons[5].setText("O");
					theGame.getTheBoard().addMark(1, 2, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [2][0] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[6].getText() == "") {
					buttons[6].setText("X");
					theGame.getTheBoard().addMark(2, 0, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[6].getText() == "") {
					buttons[6].setText("O");
					theGame.getTheBoard().addMark(2, 0, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [2][1] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[7].getText() == "") {
					buttons[7].setText("X");
					theGame.getTheBoard().addMark(2, 1, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[7].getText() == "") {
					buttons[7].setText("O");
					theGame.getTheBoard().addMark(2, 1, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

		/**
		 * A listener is added to the [2][2] button. When clicked it first checks which
		 * players turn it currently is. If this button is currently empty it will place
		 * a mark on the button according to which players turn it is. Once the button
		 * is marked, the actual game board in memory is marked in that same spot. Next
		 * it will check if there is a winner or if the board is full. If there is a
		 * winner a message dialog will appear on the screen indicating the game has
		 * ended and which player won. If it is a tie the message dialog box will
		 * indicate that the game has ended in a tie. After the message dialog box is
		 * shown indicating the game is over, the program will be terminated when the
		 * user clicks the "OK" button.
		 */
		buttons[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xTurn == true && buttons[8].getText() == "") {
					buttons[8].setText("X");
					theGame.getTheBoard().addMark(2, 2, LETTER_X);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(oPlayerName + ", please click an empty box to place your mark.");
					xTurn = false;
				}
				if (xTurn == false && buttons[8].getText() == "") {
					buttons[8].setText("O");
					theGame.getTheBoard().addMark(2, 2, LETTER_O);
					theGame.getTheBoard().display();
					if (theGame.getTheBoard().xWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + xPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().oWins()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + oPlayerName + " wins!");
						System.exit(0);
					} else if (theGame.getTheBoard().isFull()) {
						JOptionPane.showMessageDialog(null, "The game is over! " + "It is a tie!");
						System.exit(0);
					}
					messageText.setText(xPlayerName + ", please click an empty box to place your mark.");
					xTurn = true;
				}
			}
		});

	}

	/**
	 * This method is responsible for running the game. It sets the players, the
	 * referee, and the board. Then it will set the turn for the 'X' player, show
	 * the grid panel with buttons for the users to click, remove the labels and
	 * text fields the users input their names, and a message is prompted to tell
	 * the users that the game has begun.
	 */
	public void playGame() throws IOException, NullPointerException {
		theGame = new Game();

//		xPlayer = new Player(xPlayerName, LETTER_X);
////		xPlayer.setBoard(theGame.getTheBoard());
////
////		oPlayer = new Player(oPlayerName, LETTER_O);
////		oPlayer.setBoard(theGame.getTheBoard());

		theRef = new Referee();
		theRef.setBoard(theGame.getTheBoard());
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);

		xTurn = true;
		gridPanel.setVisible(true);
		playerOneTextPanel.setVisible(false);
		playerTwoTextPanel.setVisible(false);
		JOptionPane.showMessageDialog(null, "The game has started!");

	}

	/**
	 * The main method creates the main frame (GUI) for the game.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try {
			GUI playTheGame = new GUI();
		} catch (IOException e) {
			e.getMessage();
		} catch (NullPointerException npe) {
			npe.getMessage();
		}
	}
}
