import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

/**
 * Team Project OceanMancalaBoard.java file
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java specific strategy class that sets the style of the baord
 */
public class OceanMancalaBoard implements MancalaBoardShape 
{
	/**
	 * Sets the shape of this baord
	 *
	 * @param pit the pit to set the shape to
	 * @return a Rectangle that represents a pit
	 */
	public Shape mancalaBoardPitShape(Pit pit) 
	{
		return new Rectangle2D.Double(pit.getXCoordinate(), pit.getYCoordinate(), pit.getPitWidth(), pit.getPitHeight());
	}

	/**
	 * Return the image of the background for the application, which in this case is the picture of the ocean
	 *
	 * @return the image of the background
	 */
	public Image mancalaBoardBGImage() 
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Ocean.jpeg"));
	}
}