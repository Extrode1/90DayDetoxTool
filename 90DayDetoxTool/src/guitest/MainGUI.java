package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 

public class MainGUI implements ActionListener 
{
	private JButton entryCreation = new JButton("Create a new journal entry"); 
	private JButton editEntries = new JButton("Edit current journal entries");
	private JButton back = new JButton("Back"); 

	private ArrayList <JournalEntry> entries = new ArrayList<>(); 
	private JTextField day; 
	private JTextField month;
	private JTextField year;
	private JTextField journalEntry;
	
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
		frame.add(back); 
		back.setVisible(false);
		
		
		//add action listener
		entryCreation.addActionListener(this);
		editEntries.addActionListener(this);
		back.addActionListener(this); 
		
		//create JTextFields
		day = new JTextField (15); 
		month = new JTextField (15); 
		year = new JTextField (15); 
		journalEntry = new JTextField (30); 
		
		//create labelled fields
		JPanel inFieldPane = new JPanel(); 
		frame.add(inFieldPane); 

		inFieldPane.setLayout(new GridLayout(0, 2)); 
		inFieldPane.add(new JLabel("Day")); 
		inFieldPane.add(day); 
		
		inFieldPane.add(new JLabel("Month")); 
		inFieldPane.add(month); 
		
		inFieldPane.add(new JLabel("Year")); 
		inFieldPane.add(year); 
//		inFieldPane.setVisible(false);
	
//		inFieldPane.addComponentListener(null);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainGUI();
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == entryCreation)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			back.setVisible(true);
				
			
		}
		else if (e.getSource() == editEntries)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			back.setVisible(true);
		}
		else if (e.getSource() == back)
		{
			entryCreation.setVisible(true);
			editEntries.setVisible(true);
			back.setVisible(false);
		}
		else
			; 
	}

		
	

}
