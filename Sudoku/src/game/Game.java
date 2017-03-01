package game;

public class Game {
	int[][] board;

	public Game() {
		board = new int[9][9];
	}

	/** Return the value at the row i and column j. */
	public int getNbr(int i, int j) {
		return board[i][j];
	}

	private boolean solve(int i, int j) {
		if (getNbr(i, j) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Try and set a number in the square and checks if it complies with the
	 * rules. True if it worked, else false
	 */
	private boolean setNbr(int value, int i, int j) {
		if (getNbr(i, j) == 0) {
			board[i][j] = value;
			while (j < 8) {
				if (board[i][j + 1] == value) {
					return false;
				} else {
					j++;
				}
			}
			return true;
		}
		return true;
	}
}
