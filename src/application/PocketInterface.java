package application;
/**
 * @author 		austinnorgaard
 * @version 	04/19/2023
 * @description Class interface of PocketInterface
 * @duedate		04/17/2023
 * @hwnumber	Final: Interface
 */
public interface PocketInterface {
	/**
	 * Returns the player in which the pocket belongs to
	 * @return The player in which the pocket belongs to
	 */
	public int getPlayer();
	
	/**
	 * Returns the number of stones in the pocket
	 * @return The number of stones in the pocket
	 */
	public int getStones();
	
	/**
	 * Add the number of stones by numStones
	 * @param numStones the number of stones to add
	 */
	public void addStones(int numStones);
	
	/**
	 * Removes all stones from the pocket
	 */
	public void removeStones();
	
	/**
	 * Public toString method for returning the pocket as a String
	 * @return The pocket as a String
	 */
	public String toString();
}
