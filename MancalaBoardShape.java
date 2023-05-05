import java.awt.Image;
import java.awt.Shape;

/**
 * Team Project MancalaBoardShape.java File
 *
 * @author Dennis Shih, Umesh Singh, Aung Paing Soe
 * @version 1.0 5/5/2023
 */

/**
 * A Java interface the defines the requirements of mancala board shapes
 */
public interface MancalaBoardShape
{
	Shape mancalaBoardPitShape (Pit pit);
	Image mancalaBoardBGImage ();
}