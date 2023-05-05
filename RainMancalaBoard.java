import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

public class RainMancalaBoard implements MancalaBoardShape 
{
	public Shape mancalaBoardPitShape(Pit pit) 
	{
		return new RoundRectangle2D.Double(pit.getXCoordinate(), pit.getYCoordinate(), pit.getPitWidth(), pit.getPitHeight(), 10, 10);
	}

	public Image mancalaBoardBGImage() 
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Rain.jpeg"));
	}
}