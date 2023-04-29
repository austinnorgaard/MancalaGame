package application;
/**
 * @author 		austinnorgaard
 * @version 	04/19/2023
 * @description Class interface of Board
 * @duedate		04/17/2023
 * @hwnumber	Final: Interface
 */
public interface Board {
	/**
	 * Returns the current pocket as a pocket
	 * @param The index of the pocket
	 * @param The player owning the pocket
	 * @return The current pocket
	 */
	public PocketInterface getPocket(int player, int index);
	
	/**
	 * Adds the stones of the indexed pocket into the current pocket
	 * @param index The index of the pocket being stolen from
	 */
	public void stealPocket (int index);
	
	/**
	 * The handler of the moves made by players, uses a pocketID passed in 
	 * to determine which pocket is chosen
	 * @param pocketID The id/index of the pocket chosen by the player
	 */
	public void makeMove (int pocketID);
	
	/**
	 * Determines if the game is over
	 * @return True if game is over, false otherwise
	 */
	public boolean gameOver();
	
	/**
	 * Public toString method that returns the board as a string
	 * @return The board as a string
	 */
	public String toString();
	
	/**
	 * Returns the current player's ID
	 * @return The current player's ID
	 */
	public int getCurrentPlayer();
	
	/**
	 * Returns the opposite player's ID
	 * @return The opposite player's ID
	 */
	public int getOppositePlayer();
	
	/**
	 * Updates the current player
	 */
	public void updatePlayer();
	
	/**
	 * Returns the Winner's ID
	 * @return The winner's ID
	 */
	public int getWinner();
	
	/**
	 * Returns the Winner of the game as a String
	 * @param The winner as a String
	 * @return The game winner as a String
	 */
	public String getWinnerString(int winnerID);
	
	/**
	 * Empties all remaining marbles into each player's pool
	 */
	public void cleanupBoard();
}
