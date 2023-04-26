import java.util.*;

public class Player{
    public static final int PIT_SIZE = 6;
    private ArrayList<Integer> pits;
    private int finalScore;

    public Player() {
        pits = new ArrayList<>();
        finalScore = 0;
    }
    public int getFinalScore(){
        return finalScore;
    }

    public void addScore(int scoreToAdd){
        finalScore += scoreToAdd;
    }

    public ArrayList<Integer> getPits(){
        return pits;
    }
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

    // Done at the beginning of the game
    public void setStone(int stones){
        for (int i = 0; i < PIT_SIZE; i++){
            pits.add(stones);
        }
    }
}
