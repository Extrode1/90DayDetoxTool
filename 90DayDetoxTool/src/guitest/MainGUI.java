package guitest;
import javax.swing.*; 
import java.awt.*; 

public class MainGUI {

	public MainGUI()
	{
		JFrame frame = new JFrame ("90 Day Detox Tool"); 
		frame.setSize(400, 100); 
		frame.add(new Drawing()); 
		frame.setVisible(true); 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainGUI();
	}
	class Drawing extends JComponent 
	{
		public void paint(Graphics g)
		{
			g.drawString("Welcome to the 90 Day Detox Tool", 150, 50); 
		}
	}
	

}
