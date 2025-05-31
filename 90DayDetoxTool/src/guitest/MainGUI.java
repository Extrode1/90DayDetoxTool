package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*; 
import java.util.*;


public class MainGUI implements ActionListener, ListSelectionListener, WindowListener
{
	//declare JFrame
	private JFrame frame; 
	
	//declare buttons
	private JButton entryCreation = new JButton("Create a new journal entry"); 
	private JButton editEntries = new JButton("Edit current journal entries");
	private JButton back = new JButton("Back"); 
	private JButton save = new JButton("Save New");
	private JButton saveExisting = new JButton ("Save Existing"); 
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
	private static DefaultListModel<JournalEntry> listModel = new DefaultListModel <>(); 
	private static JList<JournalEntry> list = new JList <>(); 
	
	//list
	private String entryFullString; 
	private String titleString; 
	private String dayString; 
	private String monthString; 
	private String yearString; 
	private JScrollPane entryList; 
	private JLabel entryLabel; 
	
	//entry index
	private int entryIndex; 
	
	//Java IO to save file
	private String fileName = "JournalEntries"; 
	
	//try to fix nullpointerexception
	ArrayList<JournalEntry> dataList; 
	public MainGUI()
	{
		frame = new JFrame ("90 Day Detox Tool"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(1000, 800); 
		frame.setVisible(true); 
		frame.setLayout(new FlowLayout()); 
		frame.addWindowListener(this); 
		frame.setLocationRelativeTo(null);
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
		journalEntry.setLineWrap(true);
		journalEntry.setWrapStyleWord(true); 
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
		savePane.setLayout(new BorderLayout()); 
		save.addActionListener(this); 	
		saveExisting.addActionListener(this); 
		savePane.add(saveExisting, BorderLayout.SOUTH); 
		savePane.add(save, BorderLayout.NORTH); 
		frame.add(savePane, BorderLayout.SOUTH);
		
		//create the list
		list = new JList<>(listModel); 
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
		
		//disable saveExisting
		saveExisting.setEnabled(false);
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
				saveExisting.setEnabled(false);
			}
			else
			{
			//selection, enable fire button
				deleteEntry.setEnabled(true);
				editEntry.setEnabled(true);
				saveExisting.setEnabled(true);
			}
		}
	}
	public static void main(String[] args)  {
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
			entryFullString = journalEntry.getText().trim(); 
			titleString = title.getText();
			dayString = day.getText();
			monthString = month.getText(); 
			yearString = year.getText(); 
			if (titleString.equals(""))
			{
				titleString = "no title entered"; 
			}
				
			//add element to list
			listModel.addElement(new JournalEntry(dayString, monthString, yearString, entryFullString, titleString));
			saveExisting.setEnabled(true); 
			//reset fields to blank
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
		else if (e.getSource() == saveExisting)
		{
			
			entryFullString = journalEntry.getText().trim(); 
			titleString = title.getText();
			dayString = day.getText();
			monthString = month.getText(); 
			yearString = year.getText(); 

			//add element to list
			
			entryFullString = journalEntry.getText().trim(); 
			titleString = title.getText();
			dayString = day.getText();
			monthString = month.getText(); 
			yearString = year.getText(); 
			if (titleString.equals(""))
			{
				titleString = "no title entered"; 
			}
			//edit element in list
			listModel.remove(entryIndex); 
			listModel.add(entryIndex, new JournalEntry(dayString, monthString, yearString, entryFullString, titleString));
			//disable existing if there is nothing in the list
		
			//reset fields to blank
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
			//reset fields to blank
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
		}
		else if (e.getSource() == deleteEntry)
		{
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Confirm deletion", JOptionPane.YES_NO_OPTION); 
			if (choice == JOptionPane.YES_OPTION)
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
			entryIndex = list.getSelectedIndex();
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
				
			}
		}
			
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		try 
		{
			//read file
			FileInputStream fin = new FileInputStream(fileName); 
			ObjectInputStream ois = new ObjectInputStream(fin); 
			//deserialize object
			dataList = (ArrayList<JournalEntry>) ois.readObject(); 
			for (int i = 0; i < dataList.size(); i++)
			{
				listModel.addElement((JournalEntry) dataList.get(i));
			}
			fin.close(); 
			ois.close(); 
			
		}
		catch(IOException | ClassNotFoundException i)
		{
			i.printStackTrace();
		}
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try
		{
			//save Object in a file
			FileOutputStream fos = new FileOutputStream(fileName); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//serialization
			//convert to arraylist of objects
			dataList = new ArrayList<>(); 

			for (int i = 0; i < listModel.size(); i++)
			{
				dataList.add(listModel.elementAt(i)); 
			}
			oos.writeObject(dataList);
			
			oos.close();
			fos.close(); 
			
			
		}
		catch(IOException i) 
		{
			i.printStackTrace(); 
		}
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
