import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	
	public void saveCurrentMove()
	{
		stonesInPitsBeforePreviousMove = currentStonesinPits.clone();
	}
	
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
	
	public boolean isThisPitMancala(int p)
	{
		if((p == 6) || (p == 13))
		{
			return true;
		}
		return false;
	}
	
	public void attachListener(ChangeListener cl)
	{
		listeners.add(cl);
	}
	
	public void Notify()
	{
		for(ChangeListener cl : listeners)
		{
			cl.stateChanged(new ChangeEvent(this));
		}
	}
	
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
	
	public GameStatus getStatus()
	{
		return status;
	}
	
	public int pitDirectlyOppostiteToAPit(int p)
	{
		if(p == 6 || p == 13 || p < 0 || p > 13)
		{
			return -1;
		}
		return (12 - p);
	}
	
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
	
	public MancalaPlayers getGamePlayer()
	{
		return gamePlayer;
	}
	
	public int[] getCurrentStonesinPits()
	{
		return currentStonesinPits;
	}
	
	public void setStatus(GameStatus status)
	{
		if(status == GameStatus.STARTED || status == GameStatus.IN_PROGRESS || status == GameStatus.FINISHED)
		{
			this.status = status;
		}
	}
	
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