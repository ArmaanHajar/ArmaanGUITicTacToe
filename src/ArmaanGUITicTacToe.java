/*
 * This game is a simple TicTacToe game that you play in a separate window.
 * Author: Armaan Hajarizadeh
 * Date: 10/21/21
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ArmaanGUITicTacToe implements ActionListener{
	// creates every label, variable, button, text field, and container in the game
	
	JFrame frame = new JFrame();
	JButton[][] button = new JButton[3][3];
	int[][] board = new int [3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;
	Container center = new Container();
	JLabel xLabel = new JLabel("X wins: 0");
	JLabel oLabel = new JLabel("O wins: 0");
	JButton xChangeName = new JButton("Change X's Name");
	JButton oChangeName = new JButton("Change O's Name");
	JTextField xChangeField = new JTextField();
	JTextField oChangeField = new JTextField();
	Container north = new Container();
	String xPlayerName = "X";
	String oPlayerName = "O";
	int xwins = 0;
	int owins = 0;
	
	public ArmaanGUITicTacToe() { // sets the layout for the TicTacToe window
		frame.setSize(400, 400);
		// center grid container
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,3));
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				button[j][i] = new JButton(j+","+i);
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
			}
		}
		frame.add(center, BorderLayout.CENTER);
		// north container
		north.setLayout(new GridLayout(3,2));
		north.add(xLabel);
		north.add(oLabel);
		north.add(xChangeName);
		xChangeName.addActionListener(this);
		north.add(oChangeName);
		oChangeName.addActionListener(this);
		north.add(xChangeField);
		north.add(oChangeField);
		frame.add(north, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new ArmaanGUITicTacToe();
	}

	@Override
	public void actionPerformed(ActionEvent event) { // checks the game for wins or ties after every move using the tie and win conditions
		JButton current;
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if (event.getSource().equals(button[j][i])) {
					gridButton = true;
					current = button[j][i];
					// sets the turn to X turn before the game starts
					if (board[j][i] == BLANK) {
						// sets the turn to O turn after an X turn
						if (turn == X_TURN) {
							current.setText("X");
							current.setEnabled(false);
							board [j][i] = X_MOVE;
							turn = O_TURN;
						}
						// sets the turn to X turn after an O turn
						else {
							current.setText("O");
							current.setEnabled(false);
							board[j][i] = O_MOVE;
							turn = X_TURN;
						}
						// check for wins and ties
						if (checkWin(X_MOVE) == true) {
							// x wins
							xwins++;
							xLabel.setText(xPlayerName + " wins!: " + xwins);
							System.out.println(xPlayerName + " wins!: " + xwins);
							clearBoard();
						}
						else if (checkWin(O_MOVE)== true) {
							// o wins
							owins++;
							oLabel.setText(oPlayerName + " wins!: " + owins);
							System.out.println(oPlayerName + " wins!: " + owins);
							clearBoard();
						}
						else if (checkTie(X_MOVE) == true) {
							// tie on an X move
							System.out.println("It's a tie!");
							clearBoard();
						}
						else if (checkTie(O_MOVE) == true) {
							// tie on an O turn
							System.out.println("It's a tie!");
							clearBoard();
						}
					}
				}
			}
		}
		if (gridButton == false) {
			// creates a print message for an X win, this function doesn't actually print a win message, only creates the message
			if (event.getSource().equals(xChangeName) == true) {
				xPlayerName = xChangeField.getText();
				xLabel.setText(xPlayerName + " wins!: " + xwins);
			}
			// creates a print message for an O win
			else if (event.getSource().equals(oChangeName) == true) {
				oPlayerName = oChangeField.getText();
				oLabel.setText(oPlayerName + " wins!: " + owins);
			}
		}
	}
	
		public boolean checkWin(int player) { // list of win conditions for program to check
			if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {
				return true;
			}
			if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {
				return true;
			}	
			if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {
				return true;
			}	
			if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {
				return true;
			}	
			if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
				return true;
			}	
			if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {
				return true;
			}	
			if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
				return true;
			}	
			if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
				return true;
			}	
			
			return false;
		}
		
		public boolean checkTie(int player) { // tie condition
			// once every space has been used and a win hasn't been detected, a tie is declared
			for (int row = 0; row < board.length; row++) {
				for (int column = 0; column < board[0].length; column++) {
					if (board[row][column] == BLANK) {
						return false;
					}
				}
			}
			return true;
		}
		
		public void clearBoard() { // clears the board when called
			// sets every space to blank
			for (int a = 0; a < board.length; a++) {
				for (int b = 0; b < board[0].length; b++) {
					board[a][b] = BLANK;
					button[a][b].setText("");
					button[a][b].setEnabled(true);
				}
			}
			turn = X_TURN;
		}
	}
