import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * User interface for a human vs human tic tac toe game
 *
 * @author Andy
 */
public class MainGUI extends JFrame implements Constants {

    private JPanel contentPane;
    private JTextField enterName;
    private JTextArea statusField;
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
    private JButton btnRestartGame;

    private BufferedReader in;
    private PrintWriter out;

    /**
     * Create the frame.
     */
    public MainGUI(Socket aSocket) {
        try {
            this.in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            this.out = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        statusField.setText("Please enter your name and click START, Player X Starts!");
        statusField.setFont(new Font("Monospaced", Font.PLAIN, 20));
        statusField.setLineWrap(true);
        scrollPane.setViewportView(statusField);

        enterName = new JTextField();
        enterName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        enterName.setBounds(277, 637, 191, 34);
        contentPane.add(enterName);
        enterName.setColumns(10);

        btnTopLeft = new JButton("");
        btnTopLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnTopLeft.setBounds(29, 98, 150, 150);
        btnTopLeft.setVisible(true);
        contentPane.add(btnTopLeft);
        btnTopLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnTopLeft.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "1");
                }
            }
        });

        btnTopMid = new JButton("");
        btnTopMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnTopMid.setBounds(205, 98, 150, 150);
        btnTopMid.setVisible(true);
        contentPane.add(btnTopMid);
        btnTopMid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnTopMid.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "2");
                }
            }
        });

        btnTopRight = new JButton("");
        btnTopRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnTopRight.setBounds(384, 98, 150, 150);
        btnTopRight.setVisible(true);
        contentPane.add(btnTopRight);
        btnTopRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnTopRight.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "3");
                }
            }
        });

        btnMidLeft = new JButton("");
        btnMidLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnMidLeft.setBounds(29, 270, 150, 150);
        btnMidLeft.setVisible(true);
        contentPane.add(btnMidLeft);
        btnMidLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnMidLeft.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "4");
                }
            }
        });

        btnMidMid = new JButton("");
        btnMidMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnMidMid.setBounds(205, 270, 150, 150);
        btnMidMid.setVisible(true);
        contentPane.add(btnMidMid);
        btnMidMid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnMidMid.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "5");
                }
            }
        });

        btnMidRight = new JButton("");
        btnMidRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnMidRight.setBounds(384, 270, 150, 150);
        btnMidRight.setVisible(true);
        contentPane.add(btnMidRight);
        btnMidRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnMidRight.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "6");
                }
            }
        });

        btnBotLeft = new JButton("");
        btnBotLeft.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnBotLeft.setBounds(29, 443, 150, 150);
        btnBotLeft.setVisible(true);
        contentPane.add(btnBotLeft);
        btnBotLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnBotLeft.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "7");
                }
            }
        });

        btnBotMid = new JButton("");
        btnBotMid.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnBotMid.setBounds(205, 443, 150, 150);
        btnBotMid.setVisible(true);
        contentPane.add(btnBotMid);
        btnBotMid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnBotMid.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "8");
                }
            }
        });

        btnBotRight = new JButton("");
        btnBotRight.setFont(new Font("Tahoma", Font.PLAIN, 99));
        btnBotRight.setBounds(384, 443, 150, 150);
        btnBotRight.setVisible(true);
        contentPane.add(btnBotRight);
        btnBotRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnBotRight.getText().isEmpty() == false) {
                    statusField.append("\nThat spot is filled. Choose another spot!");
                } else {
                    out.println("PLAYERMOVE" + "9");
                }
            }
        });

        btnStartGame = new JButton("Start game!");
        btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 36));
        btnStartGame.setBounds(601, 637, 333, 126);
        contentPane.add(btnStartGame);
        btnStartGame.addActionListener(new ActionListener() {
            /**
             * Assigns values to players and ref, and receives user inputs
             * for player names. Also assigns the predefined constants
             * in the Constant class to each character to set up the game initially
             * @param
             * @throws IOException checks null value
             */
            public void actionPerformed(ActionEvent evt) {
                out.println("PLAYERNAME" + enterName.getText());
            }
        });

//		btnQuitGame = new JButton("Quit Game");
//		btnQuitGame.setFont(new Font("Tahoma", Font.PLAIN, 36));
//		btnQuitGame.setBounds(948, 710, 269, 53);
//		contentPane.add(btnQuitGame);
//			btnQuitGame.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					System.exit(1);
//				}
//			});
//
    }

    /**
     * Adds announcement text to the main text field
     *
     * @param text
     */
    void updateToStatusField(String text) {
        statusField.append("\n" + text);
    }

    void setBtnTopLeft(String letter) {
        btnTopLeft.setText(letter);
    }

    void setBtnTopMid(String letter) {
        btnTopMid.setText(letter);
    }

    void setBtnTopRight(String letter) {
        btnTopRight.setText(letter);
    }

    void setBtnMidLeft(String letter) {
        btnMidLeft.setText(letter);
    }

    void setBtnMidMid(String letter) {
        btnMidMid.setText(letter);
    }

    void setBtnMidRight(String letter) {
        btnMidRight.setText(letter);
    }

    void setBtnBotLeft(String letter) {
        btnBotLeft.setText(letter);
    }

    void setBtnBotMid(String letter) {
        btnBotMid.setText(letter);
    }

    void setBtnBotRight(String letter) {
        btnBotRight.setText(letter);
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
    void enableButtons() {
        btnTopLeft.setEnabled(true);
        btnTopMid.setEnabled(true);
        btnTopRight.setEnabled(true);
        btnMidLeft.setEnabled(true);
        btnMidMid.setEnabled(true);
        btnMidRight.setEnabled(true);
        btnBotLeft.setEnabled(true);
        btnBotMid.setEnabled(true);
        btnBotRight.setEnabled(true);
    }

    /**
     * Clears game board and internal console board array
     * Enables all buttons
     * Resets game with same players
     */
//	void restartGame() {
//		btnTopLeft.setEnabled(true);
//		btnTopMid.setEnabled(true);
//		btnTopRight.setEnabled(true);
//		btnMidLeft.setEnabled(true);
//		btnMidMid.setEnabled(true);
//		btnMidRight.setEnabled(true);
//		btnBotLeft.setEnabled(true);
//		btnBotMid.setEnabled(true);
//		btnBotRight.setEnabled(true);

//		btnTopLeft.setText("");
//		btnTopMid.setText("");
//		btnTopRight.setText("");
//		btnMidLeft.setText("");
//		btnMidMid.setText("");
//		btnMidRight.setText("");
//		btnBotLeft.setText("");
//		btnBotMid.setText("");
//		btnBotRight.setText("");
//
//		statusField.setText("Same players, NEW GAME!");
//	}

}

