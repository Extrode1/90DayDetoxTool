package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.event.*; 
import java.util.ArrayList; 

public class MainGUI implements ActionListener, ListSelectionListener
{
	//declare JFrame
	private JFrame frame; 
	
	//declare buttons
	private JButton entryCreation = new JButton("Create a new journal entry"); 
	private JButton editEntries = new JButton("Edit current journal entries");
	private JButton back = new JButton("Back"); 
	private JButton save = new JButton("Save Entry");
	private JButton deleteEntry = new JButton("Delete Entry"); 
	private JButton editEntry = new JButton("Edit Entry"); 
	private JTextField day; 
	private JTextField month;
	private JTextField year;
	private JTextField title; 
	private JTextField fullDate; 
	private JTextArea journalEntry;
	private JPanel inFieldPane; 
	private JPanel otherPane; 
	private JPanel savePane; 
	private JPanel entryPane; 
	private JPanel crudPane; 
	
	//create additional JLabel
	private JLabel mainMenuLabel; 
	private JPanel mainPane; 
	private JLabel mainMenuLabel2; 
	//declare JList
	private DefaultListModel listModel; 
	private JList list; 
	
	private JTextArea fullJournalEntry = new JTextArea(15, 30); 
	int counter = 90; //90 day detox should have 90 entries max
	
	//list
	private String fullString; 
	private JTextField entireDate; 
	private String entryFullString; 
	private String titleString; 
	private String dayString; 
	private String monthString; 
	private String yearString; 
	private JScrollPane entryList; 
	private JLabel entryLabel; 
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
		title = new JTextField(15); 
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
		
		//create user input in GUI
		inFieldPane.add(new JLabel("Entry Title")); 
		inFieldPane.add(title); 
		title.addActionListener(this); 
		
		
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
		
		//create the list
		listModel = new DefaultListModel();
		list = new JList(listModel); 
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		
		//put list into JScrollPane
		entryList = new JScrollPane(list); 
		entryList.setPreferredSize(new Dimension(200, 600)); 
		entryPane = new JPanel(new BorderLayout()); 
		entryLabel = new JLabel("Entries"); 
		entryPane.add(entryLabel, BorderLayout.NORTH);
		entryPane.add(entryList, BorderLayout.SOUTH); 
		frame.add(entryPane); 
		
		//create crudpane
		crudPane = new JPanel(new BorderLayout()); 
		crudPane.add(deleteEntry, BorderLayout.NORTH);
		crudPane.add(editEntry, BorderLayout.SOUTH); 
		frame.add(crudPane); 
		
		//hide entry creation when JFrame opens
		entryList.setVisible(false);
		entryLabel.setVisible(false);
		inFieldPane.setVisible(false); 
		otherPane.setVisible(false);
		savePane.setVisible(false);
		
		
		//implement button that deletes journal entries
		deleteEntry.setVisible(false);
		deleteEntry.addActionListener(this); 
		deleteEntry.setEnabled(false); 
		
		//implement button that allows journal entries to be edited
		editEntry.setVisible(false);
		editEntry.addActionListener(this);
		editEntry.setEnabled(false);
	}
	//required by ListSelectionListener
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting() == false)
		{
			if (list.getSelectedIndex() == -1)
			{
			//no selection, disable delete button
				deleteEntry.setEnabled(false); 
				editEntry.setEnabled(false);
			}
			else
			{
			//selection, enable fire button
				deleteEntry.setEnabled(true);
				editEntry.setEnabled(true);
			}
		}
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
			mainPane.setVisible(false);
			entryList.setVisible(true); 
			entryLabel.setVisible(true); 
			deleteEntry.setVisible(true);
			editEntry.setVisible(true);

			

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
			entryList.setVisible(false); 
			entryLabel.setVisible(false);
			deleteEntry.setVisible(false);
			editEntry.setVisible(false);

		}
		else if (e.getSource() == save)
		{
			fullString = month.getText().trim() + "/" + day.getText().trim() + "/" + year.getText().trim();
			entireDate = new JTextField(fullString); 
			entryFullString = journalEntry.getText().trim(); 
			titleString = title.getText();
			dayString = day.getText();
			monthString = month.getText(); 
			yearString = year.getText(); 


			listModel.addElement(new JournalEntry(dayString, monthString, yearString, entryFullString, titleString)); 
			day.setText(""); 
			month.setText("");
			year.setText(""); 
			journalEntry.setText(""); 
			title.setText(""); 
			back.setEnabled(true); 
			
			//original entry editing shows
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			mainPane.setVisible(false);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false); 
			entryList.setVisible(true); 
			entryLabel.setVisible(true); 
			deleteEntry.setVisible(true);
			editEntry.setVisible(true);
			//make entry editing information hidden
			
		}
		else if (e.getSource() == deleteEntry)
		{
			int index = list.getSelectedIndex();
			listModel.remove(index); 

			//consider edge case - nothing left
			int size = listModel.getSize(); 
			
			if (size == 0)
			{
				deleteEntry.setEnabled(false); 
			}
			else
			{
				if (index == listModel.getSize())
				{
					//last index removed
					index--; 
				}
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			}

			
		}	
		else if (e.getSource() == editEntry)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			back.setVisible(true);
			inFieldPane.setVisible(true);
			otherPane.setVisible(true); 
			savePane.setVisible(true);
			mainPane.setVisible(false);
			//remove list from view
			entryList.setVisible(false); 
			entryLabel.setVisible(false);
			deleteEntry.setVisible(false);
			editEntry.setVisible(false);
			//convert JTextField to string
			int entryIndex = list.getSelectedIndex();
			if (entryIndex >= 0)
			{
				JournalEntry index = (JournalEntry) listModel.get(entryIndex);
				
				day.setText(index.getDay()); 
				month.setText(index.getMonth());
				year.setText(index.getYear()); 
				journalEntry.setText(index.getJournalEntry());
				title.setText(index.getTitleString()); 
				//cannot go back unless entry is saved
				back.setEnabled(false); 
				listModel.remove(entryIndex); 
			}
		}
			
	}
	
}
