import java.util.*;
import javax.swing.event.*;

/**
 * @author Aung Paing Soe, Dennis Shih
 *
 */
public class MancalaBoard {
	private Player p1;
	private Player p2;
	private ArrayList<ChangeListener> listeners;
	final int MAX_UNDOS =3;
	private int turn;

	public MancalaBoard() {
		p1 = new Player();
		p2 = new Player();
		setTurn(p1);
		listeners = new ArrayList<>();
	}

	private void setTurn(Player p) {
		if (p.equals(p1)) {
			turn=1;
		} else turn=2;	
	}
	
	private Player getTurn() {
		if (turn==1) return p1;
		else return p2;
	}

	public void setStone(int numOfStones) {
		if (numOfStones == 3 || numOfStones == 4) {
			p1.setStone(numOfStones);
			p2.setStone(numOfStones);
		} else {
			throw new IllegalArgumentException("The number of stones must either be 3 or 4 stones in each pit!");
		}
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}

	public void addStone(int startingPit, Player p) {
		if (p.equals(p1)) {
			int returnStones = p1.addStone(startingPit, p1.getPits().get(startingPit));
			if (returnStones > 0) {
				p2.addStone(0, p2.getPits().get(0));
			}
		} else if (p.equals(p2)) {
			int returnStones = p2.addStone(startingPit, p2.getPits().get(startingPit));
			if (returnStones > 0) {
				p1.addStone(0, p2.getPits().get(0));
			}
		}
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}

	public boolean finishRound() {
		int remaining1 = 0;
		int remaining2 = 0;
		for (int stones : p1.getPits()) {
			remaining1 += stones;
		}
		for (int stones2 : p2.getPits()) {
			remaining2 += stones2;
		}
		if (remaining1 == 0) {
			p2.addScore(remaining2);
			return true;
		} else if (remaining2 == 0) {
			p1.addScore(remaining1);
			return true;
		}
		return false;
	}

	public String declareWinner() {
		int finalScore1 = p1.getFinalScore();
		int finalScore2 = p2.getFinalScore();
		if (finalScore1 > finalScore2) {
			return "Player 1 wins the game.";
		} else if (finalScore1 < finalScore2) {
			return "Player 2 wins the game.";
		} else {
			return "The match ends in a tie.";
		}
	}

	/**
	 * Undos a player selection. A player can undo at most 3 times in his turn, once
	 * per selection.
	 */
	public void undo() {
		if (getTurn().getNumUndos() < 3 && getTurn().getSelectedPit() != -1) {
			//undo the selection
		} else {
			//tell player they can't undo
		}
	}

}
