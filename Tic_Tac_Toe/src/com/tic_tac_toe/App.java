package com.tic_tac_toe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class App extends JFrame {
	private Users player1;
    private Users player2;
    private Users currentPlayer;
    private JButton[][] buttons = new JButton[3][3];
    private Matrix mat = new Matrix();
    private JLabel scoreLabel;
    private JLabel turnLabel;

    public App() {
        setTitle("Tic Tac Toe");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize Players
        initializePlayers();

        // Score and Turn Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        scoreLabel = new JLabel(getScoreText());
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(scoreLabel, gbc);

        turnLabel = new JLabel(getTurnText());
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(turnLabel, gbc);

        add(infoPanel, BorderLayout.NORTH);

        // Game Board Panel
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                board.add(button);
            }
        }
        add(board, BorderLayout.CENTER);
    }

    private void initializePlayers() {
        String player1Name = JOptionPane.showInputDialog("Enter name for Player 1:");
        String[] options = {"X", "O"};
        int player1Team = JOptionPane.showOptionDialog(null, "Choose your team:", "Team Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String player2Name = JOptionPane.showInputDialog("Enter name for Player 2:");
        int player2Team = 1 - player1Team;  // The opposite team

        player1 = new Users(player1Name, options[player1Team].charAt(0));
        player2 = new Users(player2Name, options[player2Team].charAt(0));
        currentPlayer = player1;
    }

    private String getScoreText() {
        return String.format("%s (%c): %d    %s (%c): %d", player1.getName(), player1.getTeam(), player1.getScore(), player2.getName(), player2.getTeam(), player2.getScore());
    }

    private String getTurnText() {
        return "Current Turn: " + currentPlayer.getName();
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && mat.hasPlace()) {
                buttons[row][col].setText(String.valueOf(currentPlayer.getTeam()));
                boolean flag = mat.fillMatrix(currentPlayer.getTeam(), row, col);
                if (flag) {
                    if (mat.checkWinner(row, col, currentPlayer.getTeam()) == currentPlayer.getTeam()) {
                        currentPlayer.incrementScore();
                        JOptionPane.showMessageDialog(null, "The Winner is: " + currentPlayer.getName());
                        resetBoard();
                    } else if (!mat.hasPlace()) {
                        JOptionPane.showMessageDialog(null, "It's a Draw!");
                        resetBoard();
                    } else {
                        currentPlayer = (currentPlayer == player1) ? player2 : player1;
                        turnLabel.setText(getTurnText());
                    }
                }
                scoreLabel.setText(getScoreText());
            }
        }
    }

    private void resetBoard() {
        mat = new Matrix();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        turnLabel.setText(getTurnText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App frame = new App();
            frame.setVisible(true);
        });
    }
}
