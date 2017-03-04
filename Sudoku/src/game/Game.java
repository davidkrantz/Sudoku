package game;

public class Game {
	private int[][] board;
	private boolean wrongInput;

	/** Create an empty sudoku board, which is defined as a filled board with -1. */
	public Game() {
		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = -1;
			}
		}
		wrongInput = false;
	}

	/** Return the value at the row i and column j. */
	public int getNbr(int i, int j) {
		return board[i][j];
	}
	
	/** Set value at the row i and column j. */
	public void setNbr(int i, int j, int value) {
		board[i][j] = value;
	}

	/** Check if the cell is empty. */
	public boolean isEmpty(int i, int j) {
		return board[i][j] == -1;
	}

	/** Checks each cell recursive and solves the sudoku. */
	public boolean solve(int i, int j) {
		if (i > 8) { // om vi har nått slutet
			return true;
		} else if (j > 8) { // om vi är i slutet av en rad
			return solve(i + 1, 0);
		} else {
			if (isEmpty(i, j)) { // om rutan är tom
				for (int value = 1; value <= 9; value++) {
					if (checkRow(i, value) && checkCol(j, value) && checkBox(i, j, value)) {
						setNbr(i, j, value);
						if (solve(i, j + 1)) {
							return true;
						} else {
							if (wrongInput) {
								return false;
							} else {
								setNbr(i, j, -1);
							}
						}
					}
				}
				return false;
			} else {
				int value = getNbr(i, j);
				setNbr(i, j, -1);
				if (checkRow(i, value) && checkCol(j, value) && checkBox(i, j, value)) {
					setNbr(i, j, value);
					if (solve(i, j + 1)) {
						return true;
					} else {
						return false;
					}
				} else {
					wrongInput = true;
					return false;
				}
			}
		}
	}

	/** Check if the row i contains value. */
	private boolean checkRow(int i, int value) {
		for (int j = 0; j < 9; j++) {
			if (getNbr(i, j) == value) {
				return false;
			}
		}
		return true;
	}

	/** Check if the column j contains value. */
	private boolean checkCol(int j, int value) {
		for (int i = 0; i < 9; i++) {
			if (getNbr(i, j) == value) {
				return false;
			}
		}
		return true;
	}

	/** Check if the 3x3 box that the cell is in contains value. */
	private boolean checkBox(int i, int j, int value) {
		int row = i / 3;
		int col = j / 3;
		for (int k = 0; k < 3; k++) {
			for (int t = 0; t < 3; t++) {
				if (value == getNbr(k + 3 * row, t + 3 * col)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String arg[]) {
		Game g = new Game();
		g.setNbr(0, 0, 1);
		g.setNbr(0, 1, 2);
		g.setNbr(0, 2, 3);
		g.setNbr(1, 0, 4);
		g.setNbr(1, 1, 5);
		g.setNbr(1, 2, 6);
		g.setNbr(2, 3, 7);
		// g.setNbr(2, 0, 5);
		// g.setNbr(7, 0, 5);
		if (g.solve(0, 0)) {
			System.out.println("JA");
		} else {
			System.out.println("NEJ");
		}

	}
}