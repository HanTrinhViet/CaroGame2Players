/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maingame.model;

/**
 *
 * @author admin
 */
public class CaroModel {
    public static final int ROW_SIZE = 20;
    public static final int COL_SIZE = 30;
    private boolean tick[][];
    private int xUndo[];
    private int yUndo[];
    private int undoSize;
    private CurrentPlayer currentPlayer;

    public CaroModel() {
        tick = new boolean[ROW_SIZE + 2][COL_SIZE + 2];
        xUndo = new int[ROW_SIZE * COL_SIZE];
        yUndo = new int[ROW_SIZE * COL_SIZE];
        undoSize = 0;
        currentPlayer = CurrentPlayer.X;
    }

    public boolean[][] getTick() {
        return tick;
    }

    public void setTick(boolean[][] tick) {
        this.tick = tick;
    }

    public int[] getxUndo() {
        return xUndo;
    }

    public void setxUndo(int[] xUndo) {
        this.xUndo = xUndo;
    }

    public int[] getyUndo() {
        return yUndo;
    }

    public void setyUndo(int[] yUndo) {
        this.yUndo = yUndo;
    }

    public int getUndoSize() {
        return undoSize;
    }

    public void setUndoSize(int undoSize) {
        this.undoSize = undoSize;
    }

    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(CurrentPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}

