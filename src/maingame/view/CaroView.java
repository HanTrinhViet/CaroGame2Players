/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maingame.view;

import init.login.LoginFrm;
import init.playerdata.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import maingame.control.CaroController;
import maingame.model.CaroModel;
import maingame.model.CurrentPlayer;

/**
 *
 * @author admin
 */
public class CaroView extends JFrame {

    private Player playerFirst;
    private Player playerSecond;
    private int row = CaroModel.ROW_SIZE;
    private int column = CaroModel.COL_SIZE;
    private LoginFrm loginFrm;
    private CaroModel caroModel;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color X_COLOR = Color.RED;
    private static final Color O_COLOR = Color.BLUE;
    private JPanel pnlBoard;
    private JPanel pnlFunction;
    private JLabel lblMessage;
    private JButton btnNewGame;
    private JButton btnUndo;
    private JButton btnExit;
    private JButton btnTiles[][];
    private JPanel pnlLeftSide;
    private JPanel pnlRightSide;
    private JLabel lblPlayerOneName;
    private JLabel lblPlayerTwoName;
    private JLabel lblScorePlyOne;
    private JLabel lblScorePlyTwo;
    private static int scorePlayerOne = 0;
    private static int scorePlayerTwo = 0;

    public CaroView() {
        loginFrm = new LoginFrm();
        caroModel = new CaroModel();
        playerFirst = new Player();
        playerSecond = new Player();
    }

    public CaroView(LoginFrm loginFrm, Player player1, Player player2) {
        this();
        this.loginFrm = loginFrm;
        setPlayerInGameInfo(player1, player2);
        this.setSize(1900, 1000);
        this.setTitle("GAME CAROOOOOOOOOOOOOOOO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        registerEventData();
        this.add(lblMessage, BorderLayout.NORTH);
        this.add(pnlBoard, BorderLayout.CENTER);
        this.add(pnlFunction, BorderLayout.SOUTH);
        this.add(pnlLeftSide, BorderLayout.WEST);
        this.add(pnlRightSide, BorderLayout.EAST);
        updateInfoInterface();
        this.setVisible(true);
    }

    private void initData() {
        Font btnFunctionFont = new Font("Roboto", Font.BOLD, 20);
        lblMessage = new JLabel("LƯỢT CỦA X", JLabel.CENTER);
        lblMessage.setFont(new Font("Roboto", Font.BOLD, 60));
//        lblMessage.setForeground(Color.BLACK);
        btnNewGame = new JButton("NEW GAME");
        btnNewGame.setFont(btnFunctionFont);
        btnUndo = new JButton("UNDO");
        btnUndo.setEnabled(false);
        btnUndo.setFont(btnFunctionFont);
        btnExit = new JButton("EXIT");
        btnExit.setForeground(Color.RED);
        btnExit.setFont(btnFunctionFont);
        btnTiles = new JButton[row + 2][column + 2];
        pnlBoard = new JPanel();
        pnlBoard.setLayout(new GridLayout(row, column));
        pnlFunction = new JPanel();
        pnlFunction.setLayout(new FlowLayout());
        pnlFunction.add(btnNewGame);
        pnlFunction.add(btnUndo);
        pnlFunction.add(btnExit);
//        lblScorePlyOne = new JLabel("", JLabel.CENTER);
//        lblScorePlyOne.setFont(btnFunctionFont);
//        lblScorePlyTwo = new JLabel("", JLabel.CENTER);
//        lblScorePlyTwo.setFont(btnFunctionFont);
        lblPlayerOneName = new JLabel("", JLabel.CENTER);
        lblPlayerOneName.setFont(btnFunctionFont);
        lblPlayerTwoName = new JLabel("", JLabel.CENTER);
        lblPlayerTwoName.setFont(btnFunctionFont);
        pnlLeftSide = new JPanel(new GridLayout(1, 1));
        pnlLeftSide.add(lblPlayerOneName);
        pnlRightSide = new JPanel(new GridLayout(1, 1));
        pnlRightSide.add(lblPlayerTwoName);
    }

    private void registerEventData() {
        initData();
        ActionListener caroController = new CaroController(this, caroModel);
        for (int i = 0; i <= row + 1; i++) {
            for (int j = 0; j <= column + 1; j++) {
                btnTiles[i][j] = new JButton(" ");
                btnTiles[i][j].setActionCommand(i + " " + j);
                btnTiles[i][j].setBackground(BACKGROUND_COLOR);
                btnTiles[i][j].addActionListener(caroController);
                caroModel.getTick()[i][j] = true;
            }
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                pnlBoard.add(btnTiles[i][j]);
            }
        }

        btnNewGame.addActionListener(caroController);
        btnUndo.addActionListener(caroController);
        btnExit.addActionListener(caroController);
    }

    public boolean isWinCondition(int i, int j) {
        return isVerticalWin(i, j) || isHorizontalWin(i, j)
                || isDiagonalWin(i, j) || isAntiDiagonalWin(i, j);
    }

    private boolean isVerticalWin(int i, int j) {
        int countCol = 0;
        int indexCol = i;
        while (btnTiles[indexCol][j].getText().
                compareTo(btnTiles[i][j].getText()) == 0) {
            countCol++;
            indexCol++;
        }

        indexCol = i - 1;

        while (btnTiles[indexCol][j].getText().
                compareTo(btnTiles[i][j].getText()) == 0) {
            countCol++;
            indexCol--;
        }

        if (countCol > 4) {
            return true;
        }

        return false;
    }

    private boolean isHorizontalWin(int i, int j) {
        int countRow = 0;
        int indexRow = j;
        while (btnTiles[i][indexRow].getText().
                compareTo(btnTiles[i][j].getText()) == 0) {
            countRow++;
            indexRow++;
        }

        indexRow = j - 1;

        while (btnTiles[i][indexRow].getText().
                compareTo(btnTiles[i][j].getText()) == 0) {
            countRow++;
            indexRow--;
        }

        if (countRow > 4) {
            return true;
        }

        return false;
    }

    private boolean isDiagonalWin(int i, int j) {
        int count = 0;
        int indexCol = i;
        int indexRow = j;
        while (btnTiles[i][j].getText().compareTo(btnTiles[indexCol][indexRow].getText()) == 0) {
            count++;
            indexCol++;
            indexRow++;
        }

        indexCol = i - 1;
        indexRow = j - 1;

        while (btnTiles[i][j].getText().compareTo(btnTiles[indexCol][indexRow].getText()) == 0) {
            count++;
            indexCol--;
            indexRow--;
        }

        if (count > 4) {
            return true;
        }

        return false;
    }

    private boolean isAntiDiagonalWin(int i, int j) {
        int count = 0;
        int indexCol = i;
        int indexRow = j;
        while (btnTiles[i][j].getText().compareTo(btnTiles[indexCol][indexRow].getText()) == 0) {
            count++;
            indexCol++;
            indexRow--;
        }

        indexCol = i - 1;
        indexRow = j + 1;

        while (btnTiles[i][j].getText().compareTo(btnTiles[indexCol][indexRow].getText()) == 0) {
            count++;
            indexCol--;
            indexRow++;
        }

        if (count > 4) {
            return true;
        }

        return false;
    }

    public void updateBoard(int i, int j) {
        int undoSize = caroModel.getUndoSize();
        var xUndo = caroModel.getxUndo();
        var yUndo = caroModel.getyUndo();
        var currentPlayer = caroModel.getCurrentPlayer();
        xUndo[undoSize] = i;
        yUndo[undoSize] = j;
        caroModel.setUndoSize(undoSize + 1);
        if (currentPlayer == CurrentPlayer.X) {
            btnTiles[i][j].setText("X");
            btnTiles[i][j].setForeground(X_COLOR);
            btnTiles[i][j].setFont(new Font("Roboto", Font.BOLD, 20));
            lblMessage.setText("LƯỢT CỦA O");
            caroModel.setCurrentPlayer(CurrentPlayer.O);
        } else {
            btnTiles[i][j].setText("O");
            btnTiles[i][j].setForeground(O_COLOR);
            btnTiles[i][j].setFont(new Font("Roboto", Font.BOLD, 20));
            lblMessage.setText("LƯỢT CỦA X");
            caroModel.setCurrentPlayer(CurrentPlayer.X);
        }
        caroModel.getTick()[i][j] = false;
        btnUndo.setEnabled(true);
    }

    public void undo() {
        int undoSize = caroModel.getUndoSize();
        var xUndo = caroModel.getxUndo();
        var yUndo = caroModel.getyUndo();
        var currentPlayer = caroModel.getCurrentPlayer();
        if (undoSize > 0) {
            btnTiles[xUndo[undoSize - 1]][yUndo[undoSize - 1]].setText(" ");
            btnTiles[xUndo[undoSize - 1]][yUndo[undoSize - 1]].
                    setActionCommand(xUndo[undoSize - 1] + " " + yUndo[undoSize - 1]);
            caroModel.getTick()[xUndo[undoSize - 1]][yUndo[undoSize - 1]] = true;
            caroModel.setCurrentPlayer(CurrentPlayer.X);
            if (currentPlayer != CurrentPlayer.X) {
                lblMessage.setText("Lượt Của X");
            } else {
                lblMessage.setText("Lượt Của O");
                caroModel.setCurrentPlayer(CurrentPlayer.O);
            }
            caroModel.setUndoSize(undoSize - 1);
            if (caroModel.getUndoSize() == 0) {
                btnUndo.setEnabled(false);
            }
        }
    }

    public void exit() {
        this.dispose();
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }

    public JButton getBtnUndo() {
        return btnUndo;
    }

    public JButton[][] getBtnTiles() {
        return btnTiles;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public LoginFrm getLoginFrm() {
        return loginFrm;
    }

    public Player getPlayerFirst() {
        return playerFirst;
    }

    public Player getPlayerSecond() {
        return playerSecond;
    }

    private void setPlayerInGameInfo(Player player1, Player player2) {
        this.playerFirst = findPlayerInQuery(player1);
        this.playerSecond = findPlayerInQuery(player2);
    }

    private Player findPlayerInQuery(Player player) {
        var playersSize = loginFrm.getPlayerQuery().getPlayers().size();
        var players = loginFrm.getPlayerQuery().getPlayers();
//        System.out.println(playersSize);
//        players.forEach(e -> {
//            System.out.println(e);
//        });
        for (int i = 0; i < playersSize; i++) {
            if (player.getUserName().equals(players.get(i).getUserName())) {
                player = players.get(i);
                break;
            }
        }
        return player;
    }

    public void updateInfoInterface() {
        lblPlayerOneName.setText("<html>-- " + this.playerFirst.getFullName()
                + " --<br><br>" + "---------- " + scorePlayerOne + " ------------" + "</html>");
        lblPlayerTwoName.setText("<html>-- " + this.playerSecond.getFullName()
                + " --<br><br>" + "---------- " + scorePlayerTwo + " ------------" + "</html>");
    }

    public void addScoreplayerOne() {
        this.scorePlayerOne = this.scorePlayerOne + 1;
    }

    public void addScoreplayerTwo() {
        this.scorePlayerTwo = this.scorePlayerTwo + 1;
    }

}
