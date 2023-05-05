import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

public class SunsetSkyMancalaBoard implements MancalaBoardShape 
{
	public Shape mancalaBoardPitShape(Pit pit) 
	{
		return new Ellipse2D.Double(pit.getXCoordinate(), pit.getYCoordinate(), pit.getPitWidth(), pit.getPitHeight());
	}

	public Image mancalaBoardBGImage() 
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SunsetSky.jpeg"));
	}
}