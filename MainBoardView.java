import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Team Project MainBoardView.java File
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java class that draws the mancala board of the game
 */
public class MainBoardView extends JFrame
{
	private boolean isBoardVisible;
	private MancalaView view;
	private MancalaModel model;

	/**
	 * Constructor that draws the mancala board of the game according to the number of stones and the
	 * specific background of the board, which is given by the user
	 */
	public MainBoardView()
	{
		isBoardVisible = true;
		model = new MancalaModel();
		view = new MancalaView(new OceanMancalaBoard(), model);
		
		JPanel bottom = new JPanel();
		JButton undo = new JButton("UNDO");
		undo.addActionListener((e1) -> {model.makeUndoMove();});
		bottom.add(undo);
		
		JPanel top = new JPanel();
		
		JLabel topLabel = new JLabel("Select the number of stones and the type of board for the game.");
		
		JComboBox styleCombo = new JComboBox(new String[]{"Choose BG", "Ocean", "Plants", "Rain", "Sunset Sky", "Wood"});
		JComboBox numberOfStonesCombo = new JComboBox(new String[]{"Choose Stones", "3", "4"});
		
		JButton createBoard = new JButton("Create Board");
		createBoard.addActionListener((e1) -> 
		{
			String style = (String) styleCombo.getSelectedItem();
			String numberOfStones = (String) numberOfStonesCombo.getSelectedItem();
			styleCombo.setEnabled(false);
			numberOfStonesCombo.setEnabled(false);
			
			if(numberOfStones.equals("3") && style.equals("Ocean"))
			{
				isBoardVisible = false;
				model.setInitialStones(3);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new OceanMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("4") && style.equals("Ocean"))
			{
				isBoardVisible = false;
				model.setInitialStones(4);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new OceanMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("3") && style.equals("Plants"))
			{
				isBoardVisible = false;
				model.setInitialStones(3);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new PlantsMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("4") && style.equals("Plants"))
			{
				isBoardVisible = false;
				model.setInitialStones(4);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new PlantsMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("3") && style.equals("Rain"))
			{
				isBoardVisible = false;
				model.setInitialStones(3);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new RainMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("4") && style.equals("Rain"))
			{
				isBoardVisible = false;
				model.setInitialStones(4);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new RainMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("3") && style.equals("Sunset Sky"))
			{
				isBoardVisible = false;
				model.setInitialStones(3);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new SunsetSkyMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("4") && style.equals("Sunset Sky"))
			{
				isBoardVisible = false;
				model.setInitialStones(4);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new SunsetSkyMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("3") && style.equals("Wood"))
			{
				isBoardVisible = false;
				model.setInitialStones(3);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new WoodMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else if(numberOfStones.equals("4") && style.equals("Wood"))
			{
				isBoardVisible = false;
				model.setInitialStones(4);
				model.setStatus(GameStatus.STARTED);
				this.setElementVisible(top, false);
				view.setBoard(new WoodMancalaBoard());
				this.setElementVisible(bottom, true);
			}
			
			else
			{
				styleCombo.setEnabled(true);
				numberOfStonesCombo.setEnabled(true);
			}
		});
		
		top.add(topLabel);
		top.add(numberOfStonesCombo);
		top.add(styleCombo);
		top.add(createBoard);
		
		this.add(top, BorderLayout.NORTH);
		this.add(view, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		this.setElementVisible(bottom, false);
	}

	/**
	 * Sets certain elements of GUI visible on the application based on the user input
	 *
	 * @param element the element to make it visible
	 * @param isVisible a boolean variable to set the element visible
	 */
	public void setElementVisible(JPanel element, boolean isVisible)
	{
		element.setVisible(isVisible);
		if(isBoardVisible == false)
		{
			view.startGame();
		}
	}
}
