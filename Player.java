import java.util.*;

/**
 * Group Project Player.java File
 *
 * @author Aung Paing Soe, Dennis Shih
 * @version 1.0 5/5/2023
 */

/**
 * A Java class that models a Player in the Mancala Game
 */
public class Player {
    public static final int PIT_SIZE = 6;
    private ArrayList<Integer> pits;
    private int finalScore;
    private Integer selectedPit;
    
    private int numUndos;


    /**
     * Constructs the Player object by initializing the pit ArrayList and setting the final score to 0
     */
    public Player() {
        pits = new ArrayList<>();
        finalScore = 0;
    }

    /**
     * Returns the final score of this player
     *
     * @return the final score of this player
     */
    public int getFinalScore(){
        return finalScore;
    }

    /**
     * Adds the score of this player, either when a stone is added to the player's mancala or if there are
     * any remaining stones on this player's pits at the end of the game
     *
     * @param scoreToAdd the score to add to this player's final score
     */
    public void addScore(int scoreToAdd){
        finalScore += scoreToAdd;
    }

    /**
     * Returns the pits of this player
     *
     * @return the pits of this player
     */
    public ArrayList<Integer> getPits(){
        return pits;
    }

    /**
     * Adds the stone to this player's pit, starting at the pit the player picks up the stones from
     *
     * @param startingPitNum the starting pit the player picks up the stones from
     * @param numOfStones the number of stones in that pit
     * @return the number of stones remaining after adding to the player's pit
     */
    public int addStone(int startingPitNum, int numOfStones){
        int stones = 1;
        for(int i = startingPitNum; i < pits.size(); i++){
            pits.set(i, stones);
            stones++;
        }
        if (stones > 0 && stones < numOfStones){
            addScore(1);
            stones--;
        }
        return stones;
    }

    /**
     * Returns the selected pit of this player
     * @return
     */
    public Integer getSelectedPit() {
		return selectedPit;
    	
    }

    /**
     * Sets the number of stones to put in the pits at the beginning of the game
     *
     * @param stones the number of stones to put in the pits at the beginning of the game
     */
    public void setStone(int stones){
        for (int i = 0; i < PIT_SIZE; i++){
            pits.add(stones);
        }
    }

    /**
     * Gets the number of undos this player has
     *
     * @return the number of undos this player has
     */
	public int getNumUndos() {
		return numUndos;
	}

    /**
     * Sets the number of undos the player can have
     *
     * @param numUndos the number of undos
     */
	public void setNumUndos(int numUndos) {
		this.numUndos = numUndos;
	}
	
}
