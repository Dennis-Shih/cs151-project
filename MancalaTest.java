import javax.swing.JFrame;

public class MancalaTest 
{
	public static void main(String args[])
	{
		MainBoardView mbv = new MainBoardView();
		mbv.setVisible(true);
		mbv.setTitle("MANCALA GAME");
		mbv.setSize(800, 500);
		mbv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
