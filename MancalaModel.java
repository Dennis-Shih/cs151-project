import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Team Project MancalaModel.java file
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java class the models the functions of a mancala board game
 */
public class MancalaModel 
{
	private ArrayList<ChangeListener> listeners;
	private MancalaPlayers gamePlayer;
	private int player1Undos;
	private int player2Undos;
	private GameStatus status;
	private int currentStonesinPits[];
	private boolean lastStoneEndedInMancala;
	private int stonesInPitsBeforePreviousMove[];
	private boolean canBeUndoed;

	/**
	 * Constructs the MancalaModel object by initializing underlying data structures and instance variables
	 */
	public MancalaModel()
	{
		listeners = new ArrayList<ChangeListener>();
		gamePlayer = MancalaPlayers.P1;
		player1Undos = 0;
		player2Undos = 0;
		status = GameStatus.STARTED;
		currentStonesinPits = new int[14];
		lastStoneEndedInMancala = false;
		stonesInPitsBeforePreviousMove = new int[14];
		canBeUndoed = false;
	}

	/**
	 *
	 * @param pit
	 */
	public void makeMove(int pit)
	{
		saveCurrentMove();
		canBeUndoed = true;
		
		if(gamePlayer == MancalaPlayers.P1)
		{
			player2Undos = 0;
		}
		else if(gamePlayer == MancalaPlayers.P2)
		{
			player1Undos = 0;
		}
		else
		{
		}
		
		int numberOfStones = currentStonesinPits[pit];
		currentStonesinPits[pit] = 0;
		
		while(numberOfStones > 0)
		{
			pit = pit + 1;
			if(pit > 13)
			{
				pit = 0;
			}
			
			if((whichPlayersPit(pit) != gamePlayer) && (isThisPitMancala(pit) == true))
			{
				continue;
			}
			
			currentStonesinPits[pit]++;
			numberOfStones--;
		}
		
		this.considerLastStonePosition(pit);
		
		if(this.isGameOver() == true)
		{
			this.placeRemainingStonesWhenTheGameEnds();
			status = GameStatus.FINISHED;
		}
		
		Notify();
	}

	/**
	 *
	 * @param lastPit
	 */
	public void considerLastStonePosition(int lastPit)
	{
		if((isThisPitMancala(lastPit) == true) && (whichPlayersPit(lastPit) == gamePlayer))
		{
			lastStoneEndedInMancala = true;
		}
		
		else if((currentStonesinPits[lastPit] == 1) && (currentStonesinPits[pitDirectlyOppostiteToAPit(lastPit)] > 0) && (whichPlayersPit(lastPit) == gamePlayer))
		{
			if(gamePlayer == MancalaPlayers.P1)
			{
				currentStonesinPits[6] = currentStonesinPits[6] + currentStonesinPits[pitDirectlyOppostiteToAPit(lastPit)] + 1;
				currentStonesinPits[lastPit] = currentStonesinPits[pitDirectlyOppostiteToAPit(lastPit)] = 0;
			}
			else if(gamePlayer == MancalaPlayers.P2)
			{
				currentStonesinPits[13] = currentStonesinPits[13] + currentStonesinPits[pitDirectlyOppostiteToAPit(lastPit)] + 1;
				currentStonesinPits[lastPit] = currentStonesinPits[pitDirectlyOppostiteToAPit(lastPit)] = 0;
			}
			else
			{
				return;
			}
			lastStoneEndedInMancala = false;
			this.switchPlayer();
		}
		
		else
		{
			lastStoneEndedInMancala = false;
			this.switchPlayer();
		}
	}

	/**
	 * Saves a player's current turn so that an undo can be used if a player wishes to do so
	 */
	public void saveCurrentMove()
	{
		stonesInPitsBeforePreviousMove = currentStonesinPits.clone();
	}

	/**
	 * Undoes a player's moves when a player clicks the UNDO button
	 */
	public void makeUndoMove()
	{
		if(canBeUndoed == false)
		{
			return;
		}
		
		boolean flag = true;
		
		if(gamePlayer == MancalaPlayers.P1)
		{
			if((player1Undos < 3) && (lastStoneEndedInMancala == true))
			{
				player1Undos++;
				flag = false;
			}
			else if((player2Undos < 3) && (lastStoneEndedInMancala == false))
			{
				player2Undos++;
				gamePlayer = MancalaPlayers.P2;
				flag = false;
			}
			else
			{
			}
		}
		
		else if(gamePlayer == MancalaPlayers.P2)
		{
			if((player1Undos < 3) && (lastStoneEndedInMancala == true))
			{
				player2Undos++;
				flag = false;
			}
			else if((player1Undos < 3) && (lastStoneEndedInMancala == false))
			{
				player1Undos++;
				gamePlayer = MancalaPlayers.P1;
				flag = false;
			}
			else
			{
			}
		}
		
		else
		{
		}
		
		if((status == GameStatus.FINISHED && player1Undos < 3) || (status == GameStatus.FINISHED && player2Undos < 3))
		{
			status = GameStatus.IN_PROGRESS;
		}
		
		if(flag == false)
		{
			currentStonesinPits = stonesInPitsBeforePreviousMove.clone();
			canBeUndoed = false;
			this.Notify();
		}
	}

	/**
	 * Sets the number of stones in each pit before the game begins
	 *
	 * @param numberOfStones the number of stones in each pit to start the game off
	 */
	public void setInitialStones(int numberOfStones)
	{
		for(int i = 0; i <= 12; i++)
		{
			if(i == 6)
			{
				continue;
			}
			currentStonesinPits[i] = numberOfStones;
		}
		Notify();
	}

	/**
	 * Checks whether a certain pit is the mancala of either player
	 *
	 * @param p the pit index
	 * @return a boolean value of true if it is mancala, otherwise false
	 */
	public boolean isThisPitMancala(int p)
	{
		if((p == 6) || (p == 13))
		{
			return true;
		}
		return false;
	}

	/**
	 * Attaches ChangeListener to this MancalaModel object to connect the model with the view
	 *
	 * @param cl the ChangeListener to add
	 */
	public void attachListener(ChangeListener cl)
	{
		listeners.add(cl);
	}

	/**
	 * Notifies the ChangeListeners after a mutated event has occurred in the model
	 */
	public void Notify()
	{
		for(ChangeListener cl : listeners)
		{
			cl.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Return the scores of the player
	 *
	 * @param p the player of the game
	 * @return the score of that particular player
	 */
	public int score(MancalaPlayers p)
	{
		if(p == MancalaPlayers.P1)
		{
			return currentStonesinPits[6];
		}
		
		else if(p == MancalaPlayers.P2)
		{
			return currentStonesinPits[13];
		}
		
		else
		{
			return 0;
		}
	}

	/**
	 * Checks whether the pit is a playable pit
	 *
	 * @param p the pit index
	 * @return a boolean value of true if it can be played; false otherwise
	 */
	public boolean isThePitPlayable(int p)
	{
		if(status == GameStatus.STARTED || status == GameStatus.FINISHED)
		{
			return false;
		}
		
		if(currentStonesinPits[p] == 0)
		{
			return false;
		}
		
		if(whichPlayersPit(p) != gamePlayer)
		{
			return false;
		}
		
		if((p < 0) || (p > 13) || (p == 6) || (p == 13))
		{
			return false;
		}
		
		return true;
	}

	/**
	 * Returns the status of the game
	 *
	 * @return the status of the game
	 */
	public GameStatus getStatus()
	{
		return status;
	}

	/**
	 * Returns the pit that is exactly opposite of a certain pit
	 *
	 * @param p the pit index to check its opposite
	 * @return the pit index that is directly opposite
	 */
	public int pitDirectlyOppostiteToAPit(int p)
	{
		if(p == 6 || p == 13 || p < 0 || p > 13)
		{
			return -1;
		}
		return (12 - p);
	}

	/**
	 * Determines which player's pit a certain pit index is
	 *
	 * @param p the pit index
	 * @return The player that corresponds to that certian pit index
	 */
	public MancalaPlayers whichPlayersPit(int p)
	{
		if((p >= 0) && (p <= 6))
		{
			return MancalaPlayers.P1;
		}
		
		else if((p >= 7) && (p <= 13))
		{
			return MancalaPlayers.P2;
		}
		
		else
		{
			return null;
		}
	}

	/**
	 * Returns the players of this game
	 *
	 * @return The players of this game
	 */
	public MancalaPlayers getGamePlayer()
	{
		return gamePlayer;
	}

	/**
	 * Returns the number stones in each pit for all the pits in the game
	 *
	 * @return An array that has the number of stones in each pit
	 */
	public int[] getCurrentStonesinPits()
	{
		return currentStonesinPits;
	}

	/**
	 * Sets the status of the game
	 *
	 * @param status the new status to set the game to
	 */
	public void setStatus(GameStatus status)
	{
		if(status == GameStatus.STARTED || status == GameStatus.IN_PROGRESS || status == GameStatus.FINISHED)
		{
			this.status = status;
		}
	}

	/**
	 * Switches the turn to the next player after a player's turn is over
	 */
	public void switchPlayer()
	{
		if(gamePlayer == MancalaPlayers.P1)
		{
			gamePlayer = MancalaPlayers.P2;
		}
		else if(gamePlayer == MancalaPlayers.P2)
		{
			gamePlayer = MancalaPlayers.P1;
		}
		else
		{
			return;
		}
	}

	/**
	 * Places the remaining stones in the pit to the player's mancala at the end of the game
	 */
	public void placeRemainingStonesWhenTheGameEnds()
	{
		for(int i = 0; i <= 5; i++)
		{
			currentStonesinPits[6] = currentStonesinPits[6] + currentStonesinPits[i];
			currentStonesinPits[i] = 0;
		}
		for(int j = 7; j <= 12; j++)
		{
			currentStonesinPits[13] = currentStonesinPits[13] + currentStonesinPits[j];
			currentStonesinPits[j] = 0;
		}
	}

	/**
	 * Checks whether the game is over
	 *
	 * @return a boolean value of true if it is over; false otherwise
	 */
	public boolean isGameOver()
	{
		int stonesInP1Pits = 0;
		int stonesInP2Pits = 0;
		for(int i = 0; i <= 5; i++)
		{
			stonesInP1Pits = stonesInP1Pits + currentStonesinPits[i];
		}
		for(int j = 7; j <= 12; j++)
		{
			stonesInP2Pits = stonesInP2Pits + currentStonesinPits[j];
		}
		
		if(stonesInP1Pits == 0 || stonesInP2Pits == 0)
		{
			return true;
		}
		return false;
	}
}