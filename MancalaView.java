import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Team Project MancalaView.java file
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java class that draws the whole mancala board game application
 */
public class MancalaView extends JComponent implements ChangeListener
{
	private ArrayList<Pit> pits;
	private int stones[];
	private MancalaBoardShape board;
	private MancalaModel model;

	/**
	 * Constructor that draws the whole mancala game application
	 *
	 * @param board the board object that draws the board of the mancala game
	 * @param model the model object that manages the functions of the game
	 */
	public MancalaView(MancalaBoardShape board, MancalaModel model)
	{
		this.pits = new ArrayList<Pit>();
		this.stones = new int[14];
		this.board = board;
		this.model = model;
		model.attachListener(this);
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				for(int i = 0; i <= pits.size()-1; i++)
				{
					if(pits.get(i).contains(me.getPoint()) && model.isThePitPlayable(i))
					{
						model.makeMove(i);
						return;
					}
				}
			}
		});
	}

	/**
	 * Starts the game and notifies the model of the status
	 */
	public void startGame()
	{
		this.setVisible(true);
		model.setStatus(GameStatus.IN_PROGRESS);
	}

	/**
	 * Sets the board to a particular style
	 *
	 * @param board the board to set the style to
	 */
	public void setBoard(MancalaBoardShape board)
	{
		this.board = board;
		this.refresh();
	}

	/**
	 * Draws the main frame of the application
	 *
	 * @param g the <code>Graphics</code> object to protect
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D graphic = (Graphics2D) g;
		graphic.drawImage(board.mancalaBoardBGImage(), 0, 0, this);
		
		String final_msg;
		String turn_msg;
		
		for(Pit pit : pits)
		{
			pit.drawPit(graphic);
		}
		
		if(model.getStatus() == GameStatus.IN_PROGRESS)
		{
			if(model.getGamePlayer() == MancalaPlayers.P1)
			{
				turn_msg = "Turn: PLAYER 1";
				graphic.setColor(Color.BLACK);
				graphic.drawString(turn_msg, 300, 225);
			}
			else if(model.getGamePlayer() == MancalaPlayers.P2)
			{
				turn_msg = "Turn: PLAYER 2";
				graphic.setColor(Color.BLACK);
				graphic.drawString(turn_msg, 300, 225);
			}
			else
			{
			}
		}
		
		else if(model.getStatus() == GameStatus.FINISHED)
		{
			final_msg = "GAME ENDED!" + "     " + "Final Scores: " + "   " + "Player 1: " + model.score(MancalaPlayers.P1) + "     " + "Player 2: " + model.score(MancalaPlayers.P2);
			graphic.setColor(Color.BLACK);
			graphic.drawString(final_msg, 100, 225);
		}
		
		else
		{
		}
	}

	/**
	 * Refreshes the board of the game when a new turn has occurred
	 */
	public void refresh()
	{
		pits = new ArrayList<Pit>();
		Pit p0 = new Pit(100, 40, 150, 60);
		Pit p1 = new Pit(200, 40, 150, 60);
		Pit p2 = new Pit(300, 40, 150, 60);
		Pit p3 = new Pit(400, 40, 150, 60);
		Pit p4 = new Pit(500, 40, 150, 60);
		Pit p5 = new Pit(600, 40, 150, 60);
		Pit p6 = new Pit(10, 85, 270, 60);
		Pit p7 = new Pit(100, 250, 150, 60);
		Pit p8 = new Pit(200, 250, 150, 60);
		Pit p9 = new Pit(300, 250, 150, 60);
		Pit p10 = new Pit(400, 250, 150, 60);
		Pit p11 = new Pit(500, 250, 150, 60);
		Pit p12 = new Pit(600, 250, 150, 60);
		Pit p13 = new Pit(690, 85, 270, 60);
		
		p0.setPitShape(board.mancalaBoardPitShape(p0));
		p0.setStones(stones[0]);
		p1.setPitShape(board.mancalaBoardPitShape(p1));
		p1.setStones(stones[1]);
		p2.setPitShape(board.mancalaBoardPitShape(p2));
		p2.setStones(stones[2]);
		p3.setPitShape(board.mancalaBoardPitShape(p3));
		p3.setStones(stones[3]);
		p4.setPitShape(board.mancalaBoardPitShape(p4));
		p4.setStones(stones[4]);
		p5.setPitShape(board.mancalaBoardPitShape(p5));
		p5.setStones(stones[5]);
		p6.setPitShape(board.mancalaBoardPitShape(p6));
		p6.setStones(stones[6]);
		p7.setPitShape(board.mancalaBoardPitShape(p7));
		p7.setStones(stones[7]);
		p8.setPitShape(board.mancalaBoardPitShape(p8));
		p8.setStones(stones[8]);
		p9.setPitShape(board.mancalaBoardPitShape(p9));
		p9.setStones(stones[9]);
		p10.setPitShape(board.mancalaBoardPitShape(p10));
		p10.setStones(stones[10]);
		p11.setPitShape(board.mancalaBoardPitShape(p11));
		p11.setStones(stones[11]);
		p12.setPitShape(board.mancalaBoardPitShape(p12));
		p12.setStones(stones[12]);
		p13.setPitShape(board.mancalaBoardPitShape(p13));
		p13.setStones(stones[13]);
		
		this.addPit(p7);
		this.addPit(p8);
		this.addPit(p9);
		this.addPit(p10);
		this.addPit(p11);
		this.addPit(p12);
		this.addPit(p13);
		this.addPit(p5);
		this.addPit(p4);
		this.addPit(p3);
		this.addPit(p2);
		this.addPit(p1);
		this.addPit(p0);
		this.addPit(p6);
	}

	/**
	 * Adds a new pit to the game
	 *
	 * @param pit the new pit to add to
	 */
	public void addPit(Pit pit)
	{
		this.pits.add(pit);
	}

	/**
	 * Sets the stones in each of the pit to a new number when a turn has occurred
	 *
	 * @param e the ChangeEvent object that is created when a user moves the stones
	 */
	public void stateChanged(ChangeEvent e) 
	{
		this.refresh();
		stones = model.getCurrentStonesinPits();
		for(int i = 0; i <= pits.size()-1; i++)
		{
			pits.get(i).setStones(stones[i]);
		}
		this.repaint();
	}
}
