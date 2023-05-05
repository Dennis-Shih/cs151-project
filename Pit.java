import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Team Project Pit.java file
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java class that draws the pits of the mancala board game
 */
public class Pit 
{
	private Shape pit_shape;
	private int x_coordinate;
	private int y_coordinate;
	private int pit_height;
	private int pit_width;
	private int stones;

	/**
	 * Constructs the Pit object by initializing the coordinates, the width, and the height of the pit
	 * @param x_coordinate the x-coordinate of this pit
	 * @param y_coordinate the y-coordinate of this pit
	 * @param pit_height the height of this pit
	 * @param pit_width the width of this pit
	 */
	public Pit(int x_coordinate, int y_coordinate, int pit_height, int pit_width)
	{
		this.x_coordinate = x_coordinate;
		this.y_coordinate = y_coordinate;
		this.pit_height = pit_height;
		this.pit_width = pit_width;
		this.stones = 0;
	}

	/**
	 * Draws the pit for the game using the graphic context
	 *
	 * @param graphic the graphic context to draw the pit
	 */
	public void drawPit (Graphics2D graphic)
	{
		graphic.setColor(Color.BLACK);
		graphic.setStroke(new BasicStroke(13));
		graphic.draw(pit_shape);
		
		graphic.setStroke(new BasicStroke(4));
		graphic.setFont(new Font("TrueType", Font.BOLD, 20));
		graphic.drawString("" + stones, x_coordinate + 25, y_coordinate + 25);
		
		int flag = 1;
		float x,y;
		for(int i = 0; i <= stones -1; i++)
		{
			x = y = 0.0f;
			if(flag % 3 == 1)
			{
				x = x_coordinate + 26;
				y = y_coordinate + 25 + (flag * 5);
			}
			
			else if(flag % 3 == 2)
			{
				x = x_coordinate + 39;
				y = y_coordinate + 25 + (flag * 5);
			}
			
			else
			{
				x = x_coordinate + 13;
				y = y_coordinate + 25 + (flag * 5);
			}
			
			flag++;
			
			Ellipse2D.Double stoneShape = new Ellipse2D.Double(x, y, 13, 10);
			graphic.setColor(Color.WHITE);
			graphic.fill(stoneShape);
			graphic.setColor(Color.BLACK);
			graphic.setStroke(new BasicStroke(2));
			graphic.draw(stoneShape);
		}
	}

	/**
	 * Checks whether a point is within this pit
	 *
	 * @param point the point to check
	 * @return a boolean value of true if it contains; false otherwise
	 */
	public boolean contains (Point2D point)
	{
		if(pit_shape.contains(point))
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns the width of this pit
	 *
	 * @return the width of this pit
	 */
	public int getPitWidth()
	{
		return pit_width;
	}

	/**
	 * Returns the height of this pit
	 *
	 * @return the height of this pit
	 */
	public int getPitHeight()
	{
		return pit_height;
	}

	/**
	 * Returns the x-coordinate of this pit
	 *
	 * @return the x-coordinate of this pit
	 */
	public int getXCoordinate()
	{
		return x_coordinate;
	}

	/**
	 * Returns the y-coordinate of this pit
	 *
	 * @return the y-coordinate of this pit
	 */
	public int getYCoordinate()
	{
		return y_coordinate;
	}

	/**
	 * Sets the shape of this pit to a certain shape
	 *
	 * @param pit_shape the shape of this pit
	 */
	public void setPitShape(Shape pit_shape)
	{
		this.pit_shape = pit_shape;
	}

	/**
	 * Sets the number of stones of this pit
	 *
	 * @param newNumberOfStones the number of stones to set this pit to
	 */
	public void setStones(int newNumberOfStones)
	{
		stones = newNumberOfStones;
	}
}
