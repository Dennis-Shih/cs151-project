import java.util.*;
import javax.swing.event.*;
public class MancalaBoard {
    private Player p1;
    private Player p2;
    private ArrayList<ChangeListener> listeners;

    public MancalaBoard(){
        p1 = new Player();
        p2 = new Player();
        listeners = new ArrayList<>();
    }

    public void setStone(int numOfStones){
        if (numOfStones == 3 || numOfStones == 4){
            p1.setStone(numOfStones);
            p2.setStone(numOfStones);
        }
        else {
            throw new IllegalArgumentException("The number of stones must either be 3 or 4 stones in each pit");
        }
        ChangeEvent e = new ChangeEvent(this);
        for(ChangeListener l: listeners){
            l.stateChanged(e);
        }
    }

    public void addStone(int startingPit){

    }

    public String declareWinner() {
        int finalScore1 = p1.getFinalScore();
        int finalScore2 = p2.getFinalScore();
        if (finalScore1 > finalScore2){
            return "Player 1 wins the game.";
        }
        else if (finalScore1 < finalScore2){
            return "Player 2 wins the game.";
        }
        else {
            return "The match is a tie-match.";
        }
    }

    public void undo(){

    }

}
