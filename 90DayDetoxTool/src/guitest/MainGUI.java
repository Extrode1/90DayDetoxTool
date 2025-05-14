package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 

public class MainGUI implements ActionListener 
{
	//declare JFrame
	private JFrame frame; 
	
	//declare buttons
	private JButton entryCreation = new JButton("Create a new journal entry"); 
	private JButton editEntries = new JButton("Edit current journal entries");
	private JButton back = new JButton("Back"); 
	private JButton save = new JButton("Save Entry");
	private ArrayList <JournalEntry> entries = new ArrayList<>(); 
	private JTextField day; 
	private JTextField month;
	private JTextField year;
	private JTextField fullDate; 
	private JTextArea journalEntry;
	private JPanel inFieldPane; 
	private JPanel otherPane; 
	private JPanel savePane; 
	
	//create additional JLabel
	private JLabel mainMenuLabel; 
	private JPanel mainPane; 
	private JLabel mainMenuLabel2; 
	
	
	//declare JList
	private DefaultListModel<JournalEntry> listModel; 
	private JList<JournalEntry> jList; 
	
	private JTextArea fullJournalEntry = new JTextArea(15, 30); 

	
	public MainGUI()
	{
		frame = new JFrame ("90 Day Detox Tool"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(1000, 800); 
		frame.setVisible(true); 
		frame.setLayout(new FlowLayout()); 
		//create JButtons		
		 
		back.setVisible(false);
		
		//create a main menu label 
		mainPane = new JPanel(new BorderLayout()); 
		mainMenuLabel = new JLabel("Welcome to the 90 Day Detox Tool!", SwingConstants.CENTER); 
		mainMenuLabel.setFont(new Font("Calibri",  Font.BOLD, 40)); 
		mainMenuLabel2 = new JLabel("This tool will allow you to create journal entries to keep track of your progress and record your thoughts and feelings when you are doing the 90 day detox. ");
		mainMenuLabel2.setFont(new Font ("Calibri", Font.ITALIC, 15)); 
		
		mainPane.add(mainMenuLabel, BorderLayout.NORTH); 
		mainPane.add(mainMenuLabel2, BorderLayout.SOUTH);
		
		//change font of button
		entryCreation.setFont(new Font("Calibri", Font.BOLD, 30));
		editEntries.setFont(new Font("Calibri", Font.BOLD, 30)); 
		
		//add buttons to JFrame
		frame.add(mainPane); 
		frame.add(entryCreation); 
		frame.add(editEntries); 
		frame.add(back);
		
		//add action listener
		entryCreation.addActionListener(this);
		editEntries.addActionListener(this);
		back.addActionListener(this); 
		
		//create JTextFields
		inFieldPane = new JPanel();
		otherPane = new JPanel(); 
		day = new JTextField (15); 
		month = new JTextField (15); 
		year = new JTextField (15); 
		fullDate = new JTextField (15); 
		fullDate.setEditable(false); 
		journalEntry = new JTextArea (15, 30);
		fullJournalEntry.setEditable(false);
		journalEntry.setLineWrap(true); 
		fullJournalEntry.setLineWrap(true);
		
		otherPane.setLayout(new BorderLayout());

		//create labelled fields
		
		frame.add(inFieldPane); 
		frame.add(otherPane); 

		

		inFieldPane.setLayout(new GridLayout(0, 2)); 
		inFieldPane.add(new JLabel("Day")); 
		inFieldPane.add(day); 
		day.addActionListener(this);
		
		inFieldPane.add(new JLabel("Month")); 
		inFieldPane.add(month); 
		month.addActionListener(this);
		
		inFieldPane.add(new JLabel("Year")); 
		inFieldPane.add(year); 
		year.addActionListener(this); 
		
		JScrollPane scrollPane = new JScrollPane(journalEntry); 

		otherPane.add(new JLabel("Journal Entry")); 
		otherPane.add(scrollPane, BorderLayout.SOUTH); 
		
		//add save button for journal entry
		savePane = new JPanel(); 
		savePane.setLayout(new FlowLayout()); 
		save.addActionListener(this); 		
		savePane.add(save); 
		frame.add(savePane, BorderLayout.SOUTH);
		

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
			inFieldPane.setVisible(true);
			otherPane.setVisible(true); 
			savePane.setVisible(true);
			mainPane.setVisible(false);
				
			
		}
		else if (e.getSource() == editEntries)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			back.setVisible(true);
			inFieldPane.setVisible(true);
			otherPane.setVisible(true); 
			savePane.setVisible(true);
			mainPane.setVisible(false);

		}
		else if (e.getSource() == back)
		{
			entryCreation.setVisible(true);
			editEntries.setVisible(true);
			back.setVisible(false);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false);
			mainPane.setVisible(true);
			

		}
		else if (e.getSource() == save)
		{
			String fullString = month.getText().trim() + "/" + day.getText().trim() + "/" + year.getText().trim();
			String entryFullString = journalEntry.getText().trim(); 
			//create JTextArea
			JTextArea message = new JTextArea(entryFullString); 
			message.setLineWrap(true); 
			message.setWrapStyleWord(true); 
			
			JScrollPane scrollPane = new JScrollPane(message); 
			scrollPane.setPreferredSize(new Dimension(500, 500)); 
			int choice = JOptionPane.showConfirmDialog(frame, scrollPane, "Is this the entry you want to enter? ", JOptionPane.YES_NO_OPTION); 
			if (choice == JOptionPane.YES_OPTION)
			{
				JournalEntry newObject = new JournalEntry(day, month, year, message); 
				entries.add(newObject); 						
			}
			else if (choice == JOptionPane.NO_OPTION)
			{
				day.setText(""); 
				month.setText("");
				year.setText(""); 
				journalEntry.setText(""); 
			}
			else
				;
		}
			
	}

		
	

}
