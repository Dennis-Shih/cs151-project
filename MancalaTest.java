
/**
 * Group Project MancalaTest.java File
 *
 * @author Aung Paing Soe, Dennis Shih, Umesh Singh
 * @version 1.0 5/5/2023
 */

/**
 * A Java class that tests all the methods created in the application of a mancala game.
 * 
 * Game starts with 4 stones in each pit.
 * 
 * On their turn, Player A chooses a pit, and takes all the stones from it.
 * * Player A moves counterclockwise on the board, placing one stone in each pit (skip opponent's Mancala).
 * 
 * * If the last stone player A drops is in an empty pit on their side, they capture the opponent's stones 
 *   in the opponent's opposite pit. (Captured stones go in the player A's Mancala).
 *   
 * * Repeat with Player B's turn.
 *   
 * Game ends when all 6 pits on one side are empty.
 * 
 * 
 * 
 */
public class MancalaTest {

	/**
	 * Tests all parts of the mancala board game
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		MancalaBoard board = new MancalaBoard();
		//board.attach(new Pit());

	}

}
