package application;
/**
 * @author 		austinnorgaard
 * @version 	04/20/2023
 * @description Class declaration of GamePeg
 * @duedate		04/21/2023
 * @hwnumber	Final: Interface
 * @implements  Pocket
 */
public class Pocket implements PocketInterface {
	/** Number of stones in a pocket */
	private int stones;
	
	/** Which player the pocket belongs to */
	private int player;
	
	/**
	 * Default constructor for GamePeg
	 * @param playerID The id to assign the pocket
	 * @param numStones The number of stones to start with
	 */
	public Pocket (int playerID, int numStones) {
		player = playerID;
		stones = numStones;
	}

	@Override
	public int getPlayer() {
		return player;
	}

	@Override
	public int getStones() {
		return stones;
	}

	@Override
	public void addStones(int numStones) {
		stones += numStones;
	}

	@Override
	public void removeStones() {
		stones = 0;
	}
	
	@Override
	public String toString() {
		String s = "";
		return s;
	}

}
