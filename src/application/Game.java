package application;
/**
 * @author 		austinnorgaard
 * @version 	04/20/2023
 * @description Class declaration of Game
 * @duedate		04/21/2023
 * @hwnumber	Final: Interface
 * @implements  Board
 */
public class Game implements Board {
	/** The number of stones per pocket */
	public static int STONES_PER_POCKET = 4;
	
	/** The number of players for the game */
	public static int NUM_PLAYERS = 2;
	
	/** The number of pockets in the board */
	public static int NUM_POCKETS = 14;
	
	/** The pool pocket index */
	public static int POOL_INDEX = 6;
	
	/** Array of pockets for each player */
	private PocketInterface[][] pocketList;
	
	/** Which player is currently making a move */
	private int currentPlayer;
	
	/**
	 * Default constructor for game that initializes the game variables/settings
	 */
	public Game () {
		currentPlayer = 0;
		pocketList = new Pocket[NUM_PLAYERS][NUM_POCKETS/NUM_PLAYERS];
		for (int i = 0; i < pocketList[0].length; i++) {
			pocketList[0][i] = new Pocket(0, STONES_PER_POCKET);
			pocketList[1][i] = new Pocket(1, STONES_PER_POCKET);
		}
		pocketList[0][NUM_POCKETS/NUM_PLAYERS - 1].removeStones();
		pocketList[1][NUM_POCKETS/NUM_PLAYERS - 1].removeStones();
	}

	@Override
	public PocketInterface getPocket(int player, int index) {
		return pocketList[player][index];
	}

	@Override
	public void stealPocket(int index) {
		int stolenPocketStones = getPocket(getOppositePlayer(), (NUM_POCKETS/NUM_PLAYERS - NUM_PLAYERS) - index).getStones();
		if (stolenPocketStones > 0) {
			getPocket(getOppositePlayer(), (NUM_POCKETS/NUM_PLAYERS - NUM_PLAYERS) - index).removeStones();
			getPocket(getCurrentPlayer(), POOL_INDEX).addStones(stolenPocketStones + 1);
		}
	}

	@Override
	public void makeMove(int pocketID) {
		boolean secondTurn = false;
		int numStones = getPocket(getCurrentPlayer(), pocketID).getStones(), pocketNum = pocketID + 1;
		getPocket(getCurrentPlayer(), pocketID).removeStones();
		for (int i = 0; i < numStones; i++) {
			if (pocketNum >= NUM_POCKETS - 1) {
				pocketNum = 0;
			}
			if (pocketNum < POOL_INDEX) {
				if (checkIfStealPocket(pocketNum, numStones, i)) {
					stealPocket(pocketNum);
				}
				else {
					makeSameSideMove(pocketNum);
				}
			}
			else if (pocketNum == POOL_INDEX){
				addToPool();
				if (i + 1 >= numStones) {
					secondTurn = true;
				}
			}
			else if (pocketNum > POOL_INDEX) {
				makeOppositeSideMove(pocketNum);
			}
			pocketNum++;
		}
		if (!secondTurn) {
			updatePlayer();
		}
	}

	@Override
	public boolean gameOver() {
		boolean flag = false;
		int totalStones1 = 0, totalStones2 = 0;
		for (int i = 0; i <= NUM_POCKETS/NUM_PLAYERS - NUM_PLAYERS; i++) {
			totalStones1 += pocketList[0][i].getStones();
			totalStones2 += pocketList[1][i].getStones();
		}
		if (totalStones1 == 0 || totalStones2 == 0) {
			flag = true;
			cleanupBoard();
		}
		return flag;
	}

	@Override
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	@Override
	public int getOppositePlayer() {
		return (1 + getCurrentPlayer()) % 2;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += pocketList [0][POOL_INDEX].getStones() + "  ";
		for (int i = pocketList[0].length - 2; i >= 0; i--) {
			s += pocketList[0][i].getStones() + "  ";
		}
		s += "\n   ";
		for (int j = 0; j < pocketList[1].length; j++) {
			s += pocketList[1][j].getStones() + "  ";
		}
		return s;
	}
	
	@Override
	public void updatePlayer() {
		currentPlayer = ++currentPlayer % 2;
	}
	
	@Override
	public int getWinner() {
		int winner = 0;
		if (pocketList[1][POOL_INDEX].getStones() == pocketList[0][POOL_INDEX].getStones()) {
			winner = -1;
		}
		else if (pocketList[1][POOL_INDEX].getStones() > pocketList[0][POOL_INDEX].getStones()) {
			winner = 1;
		}
		return winner;
	}
	
	@Override
	public String getWinnerString(int winnerID) {
		String s = "";
		if (winnerID == -1) {
			s += "Tied Game!";
		}
		else {
			s += "Player " + (winnerID + 1);
		}
		return s;
	}
	
	@Override
	public void cleanupBoard() {
		for (int i = 0; i < NUM_POCKETS/NUM_PLAYERS - 1; i++) {
			getPocket(0, POOL_INDEX).addStones(getPocket(0, i).getStones());
			getPocket(0, i).removeStones();
			getPocket(1, POOL_INDEX).addStones(getPocket(1, i).getStones());
			getPocket(1, i).removeStones();
		}
	}
	
	/**
	 * Makes a move on the current player's side
	 * @param pocketIndex The current pocket's index
	 */
	private void makeSameSideMove(int pocketIndex) {
		getPocket(getCurrentPlayer(), pocketIndex).addStones(1);
	}
	
	/**
	 * Checks if the pocket landed in can steal from pocket of opposite player
	 * @param pocketIndex The current pocket's index
	 * @param totalStones The total stones used in the turn
	 * @param remainingStones The number of stones remaining
	 * @return True if pocket can steal, false otherwise
	 */
	private boolean checkIfStealPocket(int pocketIndex, int totalStones, int remainingStones) {
		return getPocket(getCurrentPlayer(), pocketIndex).getStones() == 0 &&
				remainingStones + 1 >= totalStones && 
				getPocket(getOppositePlayer(), (NUM_POCKETS/NUM_PLAYERS - NUM_PLAYERS) - pocketIndex).getStones() > 0;
	}
	
	/**
	 * Adds a marble to the current player's pool
	 */
	private void addToPool() {
		getPocket(getCurrentPlayer(), POOL_INDEX).addStones(1);
	}
	
	/**
	 * Adds marbles to the opposite player's side
	 * @param pocketIndex The index to add marbles to
	 */
	private void makeOppositeSideMove (int pocketIndex) {
		pocketIndex = pocketIndex % (NUM_POCKETS / NUM_PLAYERS);
		getPocket(getOppositePlayer(), pocketIndex).addStones(1);
	}
	
}
