package com.tic_tac_toe;

public class Matrix {
	private char[][] Xo_Matrix;
	private static final int ROWS = 3, COLS = 3;
	
	public Matrix() {
		this.Xo_Matrix = new char[ROWS][COLS];
	}
	public boolean hasPlace() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(this.Xo_Matrix[i][j] != 'X' && this.Xo_Matrix[i][j] != 'O' ) return true;
			}
		}
		return false;
	}
	public boolean fillMatrix(final char team, int row, int col) {
		if(this.Xo_Matrix[row][col] != 'X' && this.Xo_Matrix[row][col] != 'O') {
			this.Xo_Matrix[row][col] = team;
			return true;
		}
		return false;
	}
	
	public char checkWinner(int changedRow, int changedCol, char team) {
		int i;
		// In this case we will check diagonals
		if(changedRow + changedCol == 2 || changedRow + changedCol == 0 || changedRow + changedCol == 4) {
			// check this diagonal "\"
			for(i = 0; i < ROWS; i++) {
				if(this.Xo_Matrix[i][i] != team) break;
			}
			if(i == ROWS) return team;
			// check the other diagonal "/"
			for(i = COLS - 1; i >= 0; i--) {
				if(this.Xo_Matrix[COLS-1-i][i] != team) break;
			}
			if(i == -1) return team;
		}
		// Now checking changed row
		for(i = 0; i < COLS; i++) {
			if(this.Xo_Matrix[changedRow][i] != team) break;
		}
		if(i == COLS) return team;
		// Now checking changed col
		for(i = 0; i < ROWS; i++) {
			if(this.Xo_Matrix[i][changedCol] != team) break;
		}
		if(i == ROWS) return team;
		
		return '\0';
	}
	public void printMatrix() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				System.out.printf("%c ", this.Xo_Matrix[i][j]);
			}
			System.out.println();
		}
	}
}
