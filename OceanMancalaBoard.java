import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

public class OceanMancalaBoard implements MancalaBoardShape 
{
	public Shape mancalaBoardPitShape(Pit pit) 
	{
		return new Rectangle2D.Double(pit.getXCoordinate(), pit.getYCoordinate(), pit.getPitWidth(), pit.getPitHeight());
	}

	public Image mancalaBoardBGImage() 
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Ocean.jpeg"));
	}
}