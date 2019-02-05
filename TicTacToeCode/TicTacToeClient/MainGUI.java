package TicTacToeClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * User interface for a human vs human tic tac toe game
 * @author Andy
 *
 */
public class MainGUI extends JFrame implements Constants{

	private JPanel contentPane;
	private JTextField enterXName;
	private JTextField enterOName;
	private JTextArea statusField;
	private JRadioButton selectX;
	private JRadioButton selectO;
	private JButton btnTopLeft;
	private JButton btnTopMid;
	private JButton btnTopRight;
	private JButton btnMidLeft;
	private JButton btnMidMid;
	private JButton btnMidRight;
	private JButton btnBotLeft;
	private JButton btnBotMid;
	private JButton btnBotRight;
	private JButton btnStartGame;
	private JButton btnQuitGame;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private String nameXPlayer;
	private String nameOPlayer;
	private JButton btnRestartGame;
	private Board board = new Board();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1267, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Ultimate Tic-Tac-Toe Game");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblTitle.setBounds(284, 24, 661, 34);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(591, 95, 626, 524);
		contentPane.add(scrollPane);
		
		statusField = new JTextArea();
		statusField.setText("Please enter a name for Player X and Player O...");
		statusField.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(statusField);
		
		enterXName = new JTextField();
		enterXName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		enterXName.setBounds(277, 637, 191, 34);
		contentPane.add(enterXName);
		enterXName.setColumns(10);
		
		enterOName = new JTextField();
		enterOName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		enterOName.setColumns(10);
		enterOName.setBounds(277, 695, 191, 34);
		contentPane.add(enterOName);
		
		selectX = new JRadioButton("Player X");
		buttonGroup_1.add(selectX);
		selectX.setFont(new Font("Tahoma", Font.PLAIN, 36));
		selectX.setBounds(68, 634, 201, 35);
		contentPane.add(selectX);
		
		selectO = new JRadioButton("Player O");
		buttonGroup_1.add(selectO);
		selectO.setFont(new Font("Tahoma", Font.PLAIN, 36));
		selectO.setBounds(68, 691, 201, 35);
		contentPane.add(selectO);
		
		btnTopLeft = new JButton("");
		btnTopLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnTopLeft.setBounds(29, 98, 150, 150);
		btnTopLeft.setVisible(false);
		contentPane.add(btnTopLeft);
			btnTopLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnTopLeft.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnTopLeft.setText("X");
						board.addMark(0, 0, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnTopLeft.setText("O");
						board.addMark(0, 0, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
					
				}
			});
		
		btnTopMid = new JButton("");
		btnTopMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnTopMid.setBounds(205, 98, 150, 150);
		btnTopMid.setVisible(false);
		contentPane.add(btnTopMid);
			btnTopMid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				if(btnTopMid.getText().isEmpty()==false) {
					statusField.append("\nThat spot is filled. Choose another spot!");
				}
				else if(selectX.isSelected()==true){
					btnTopMid.setText("X");
					board.addMark(0, 1, LETTER_X);
					selectX.setSelected(false);
					selectO.setSelected(true);
				}
				else if(selectO.isSelected()==true){
					btnTopMid.setText("O");
					board.addMark(0, 1, LETTER_O);
					selectX.setSelected(true);
					selectO.setSelected(false);
				}
				
				if(board.xWins()) {
					statusField.append("\n"+ nameXPlayer + " is the winner!");
					disableButtons();
				}
				else if(board.oWins()) {
					statusField.append("\n"+ nameOPlayer + " is the winner!");
					disableButtons();
				}
				else if(board.isFull()) {
					statusField.append("\nThe board is full. This game is a draw!");
					disableButtons();
				}
			}
			});
		
		btnTopRight = new JButton("");
		btnTopRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnTopRight.setBounds(384, 98, 150, 150);
		btnTopRight.setVisible(false);
		contentPane.add(btnTopRight);
			btnTopRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnTopRight.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnTopRight.setText("X");
						board.addMark(0, 2, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnTopRight.setText("O");
						board.addMark(0, 2, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnMidLeft = new JButton("");
		btnMidLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnMidLeft.setBounds(29, 270, 150, 150);
		btnMidLeft.setVisible(false);
		contentPane.add(btnMidLeft);
			btnMidLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnMidLeft.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnMidLeft.setText("X");
						board.addMark(1, 0, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnMidLeft.setText("O");
						board.addMark(1, 0, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnMidMid = new JButton("");
		btnMidMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnMidMid.setBounds(205, 270, 150, 150);
		btnMidMid.setVisible(false);
		contentPane.add(btnMidMid);
			btnMidMid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnMidMid.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnMidMid.setText("X");
						board.addMark(1, 1, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnMidMid.setText("O");
						board.addMark(1, 1, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnMidRight = new JButton("");
		btnMidRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnMidRight.setBounds(384, 270, 150, 150);
		btnMidRight.setVisible(false);
		contentPane.add(btnMidRight);
			btnMidRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnMidRight.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnMidRight.setText("X");
						board.addMark(1, 2, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnMidRight.setText("O");
						board.addMark(1, 2, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnBotLeft = new JButton("");
		btnBotLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnBotLeft.setBounds(29, 443, 150, 150);
		btnBotLeft.setVisible(false);
		contentPane.add(btnBotLeft);
			btnBotLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnBotLeft.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnBotLeft.setText("X");
						board.addMark(2, 0, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnBotLeft.setText("O");
						board.addMark(2, 0, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnBotMid = new JButton("");
		btnBotMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnBotMid.setBounds(205, 443, 150, 150);
		btnBotMid.setVisible(false);
		contentPane.add(btnBotMid);
			btnBotMid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnBotMid.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnBotMid.setText("X");
						board.addMark(2, 1, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnBotMid.setText("O");
						board.addMark(2, 1, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnBotRight = new JButton("");
		btnBotRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btnBotRight.setBounds(384, 443, 150, 150);
		btnBotRight.setVisible(false);
		contentPane.add(btnBotRight);
			btnBotRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnBotRight.getText().isEmpty()==false) {
						statusField.append("\nThat spot is filled. Choose another spot!");
					}
					else if(selectX.isSelected()==true){
						btnBotRight.setText("X");
						board.addMark(2, 2, LETTER_X);
						selectX.setSelected(false);
						selectO.setSelected(true);
					}
					else if(selectO.isSelected()==true){
						btnBotRight.setText("O");
						board.addMark(2, 2, LETTER_O);
						selectX.setSelected(true);
						selectO.setSelected(false);
					}
					
					if(board.xWins()) {
						statusField.append("\n"+ nameXPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.oWins()) {
						statusField.append("\n"+ nameOPlayer + " is the winner!");
						disableButtons();
					}
					else if(board.isFull()) {
						statusField.append("\nThe board is full. This game is a draw!");
						disableButtons();
					}
				}
			});
		
		btnStartGame = new JButton("Start game!");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 36));
		btnStartGame.setBounds(601, 637, 333, 126);
		contentPane.add(btnStartGame);
			btnStartGame.addActionListener(new ActionListener()
			{
				/**
				 * Assigns values to players and ref, and receives user inputs
				 * for player names. Also assigns the predefined constants
				 * in the Constant class to each character to set up the game initially
				 * @param args: includes arguments like referee, players, and game state
				 * @throws IOException checks null value
				 */
				public void actionPerformed(ActionEvent evt)
				{
					Referee theRef;
					Player xPlayer, oPlayer;
					Game theGame = new Game();
					
					nameXPlayer = enterXName.getText();
					nameOPlayer = enterOName.getText();
					if (enterXName.getText().equals("")|| enterOName.getText().equals("")) {
						statusField.append("\nPlease enter a name for the players and try again");
						return;
					}
					else if (nameXPlayer != null & nameOPlayer != null)
					{
						statusField.append("\n"+ nameXPlayer + " is Player X");
						statusField.append("\n"+nameOPlayer + " is Player O");
						
						xPlayer = new Player(nameXPlayer, LETTER_X);
						xPlayer.setBoard(theGame.getTheBoard());
						
						oPlayer = new Player(nameOPlayer, LETTER_O);
						oPlayer.setBoard(theGame.getTheBoard());
						
						theRef = new Referee();
						theRef.setBoard(theGame.getTheBoard());
						theRef.setoPlayer(oPlayer);
						theRef.setxPlayer(xPlayer);
						
						btnTopLeft.setVisible(true);
						btnTopMid.setVisible(true);
						btnTopRight.setVisible(true);
						btnMidLeft.setVisible(true);
						btnMidMid.setVisible(true);
						btnMidRight.setVisible(true);
						btnBotLeft.setVisible(true);
						btnBotMid.setVisible(true);
						btnBotRight.setVisible(true);
						
						selectX.setSelected(true);
					}
					
				}
			});

		btnQuitGame = new JButton("Quit Game");
		btnQuitGame.setFont(new Font("Tahoma", Font.PLAIN, 36));
		btnQuitGame.setBounds(948, 710, 269, 53);
		contentPane.add(btnQuitGame);
			btnQuitGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(1);
				}
			});
		
		btnRestartGame = new JButton("Restart Game");
		btnRestartGame.setFont(new Font("Tahoma", Font.PLAIN, 36));
		btnRestartGame.setBounds(948, 643, 269, 53);
		contentPane.add(btnRestartGame);
			btnRestartGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					restartGame();
				}
			});
		
	}
	/**
	 * Disables all game buttons from user interaction
	 */
	void disableButtons() {
		btnTopLeft.setEnabled(false);
		btnTopMid.setEnabled(false);
		btnTopRight.setEnabled(false);
		btnMidLeft.setEnabled(false);
		btnMidMid.setEnabled(false);
		btnMidRight.setEnabled(false);
		btnBotLeft.setEnabled(false);
		btnBotMid.setEnabled(false);
		btnBotRight.setEnabled(false);
	}
	
	/**
	 * Clears game board and internal console board array
	 * Enables all buttons
	 * Resets game with same players
	 */
	void restartGame() {
		btnTopLeft.setEnabled(true);
		btnTopMid.setEnabled(true);
		btnTopRight.setEnabled(true);
		btnMidLeft.setEnabled(true);
		btnMidMid.setEnabled(true);
		btnMidRight.setEnabled(true);
		btnBotLeft.setEnabled(true);
		btnBotMid.setEnabled(true);
		btnBotRight.setEnabled(true);
		
		btnTopLeft.setText("");
		btnTopMid.setText("");
		btnTopRight.setText("");
		btnMidLeft.setText("");
		btnMidMid.setText("");
		btnMidRight.setText("");
		btnBotLeft.setText("");
		btnBotMid.setText("");
		btnBotRight.setText("");
		
		board.clearBoard();
		
		selectX.setSelected(true);
		
		statusField.setText("Same players, NEW GAME!");
	}
	
}
