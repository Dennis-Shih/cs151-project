import java.util.*;

/**
 * @author Dennis Shih, Aung Paing Soe
 *
 */
public class Player {
	public static final int PIT_SIZE = 6;
	private ArrayList<Integer> pits;
	private int finalScore;

	public Player() {
		pits = new ArrayList<>();
		finalScore = 0;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void addScore(int scoreToAdd) {
		finalScore += scoreToAdd;
	}

	public void addStone(int stoneToAdd, int startingPitNum) {
		int temp = 1;
		for (int i = startingPitNum; i < pits.size(); i++) {
			pits.set(i, temp);
			temp++;
		}
		if (temp > 0) {
			addScore(1);
			temp--;
		}
	}

	
	/**
	 * @param stones
	 * 
	 * Sets the initial stones. Done at the beginning of the game.
	 */
	public void setStone(int stones) {
		for (int i = 0; i < PIT_SIZE; i++) {
			pits.add(stones);
		}
	}
}
