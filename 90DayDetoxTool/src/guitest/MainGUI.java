package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 

public class MainGUI implements ActionListener 
{
	JButton entryCreation = new JButton("Create a new journal entry"); 
	JButton editEntries = new JButton("Edit current journal entries"); 
	ArrayList <JournalEntry> entries = new ArrayList<>();  
	
	public MainGUI()
	{
		JFrame frame = new JFrame ("90 Day Detox Tool"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(800, 600); 
		frame.setVisible(true); 
		frame.setLayout(new FlowLayout()); 
		//create JButtons
		
		
		frame.add(entryCreation); 
		frame.add(editEntries); 
		
		//add action listener
		entryCreation.addActionListener(this);
		editEntries.addActionListener(this); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainGUI();
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == entryCreation)
		{
			
		}
		else if (e.getSource() == editEntries)
		{
			
		}
		else
			; 
	}

		
	

}
