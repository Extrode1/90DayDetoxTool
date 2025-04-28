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
	private JButton save = new JButton("Save Entry");
	

	private ArrayList <JournalEntry> entries = new ArrayList<>(); 
	private JTextField day; 
	private JTextField month;
	private JTextField year;
	private JTextField fullDate; 
	private JTextArea journalEntry;
	private JTextArea fullJournalEntry; 
	private JPanel inFieldPane; 
	private JPanel otherPane; 
	private JPanel savePane; 
	private JScrollPane scrollPane; 
	private JPanel outFieldPane; 
	private JPanel outFieldPaneTwo; 
	
	
	
	
	
	
	
	public MainGUI()
	{
		JFrame frame = new JFrame ("90 Day Detox Tool"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(700, 800); 
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
		inFieldPane = new JPanel();
		otherPane = new JPanel(); 
		day = new JTextField (15); 
		month = new JTextField (15); 
		year = new JTextField (15); 
		fullDate = new JTextField (15); 
		fullDate.setEditable(false); 
		journalEntry = new JTextArea (15, 30);
		fullJournalEntry = new JTextArea(15, 30); 
		fullJournalEntry.setEditable(false);
		journalEntry.setLineWrap(true); 
		fullJournalEntry.setLineWrap(true);
		scrollPane = new JScrollPane(otherPane); 
		
		
		//create labelled fields
		
		frame.add(inFieldPane); 
		frame.add(otherPane); 
		frame.add(scrollPane); 
	

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
		
		
		otherPane.setLayout(new FlowLayout());
		otherPane.add(new JLabel("Journal Entry")); 
		otherPane.add(journalEntry); 
//		journalEntry.addActionListener(this); 
		
		//add save button for journal entry
		savePane = new JPanel(); 
		savePane.setLayout(new FlowLayout()); 
		save.addActionListener(this); 		
		savePane.add(save); 
		frame.add(savePane, BorderLayout.SOUTH);
		
		//display date
	
		outFieldPane = new JPanel(); 
		outFieldPane.setLayout(new GridLayout(1, 2));
		outFieldPane.add(new JLabel("Date")); 
		outFieldPane.add(fullDate);
		frame.add(outFieldPane, BorderLayout.SOUTH); 
		
		//display journal entry
		outFieldPaneTwo = new JPanel(); 
		outFieldPaneTwo.setLayout(new GridLayout(1, 2));
		outFieldPaneTwo.add(new JLabel("Journal Entry")); 
		outFieldPaneTwo.add(fullJournalEntry);
		frame.add(outFieldPaneTwo); 
		
		
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
			outFieldPane.setVisible(true);
			outFieldPaneTwo.setVisible(true);
				
			
		}
		else if (e.getSource() == editEntries)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			back.setVisible(true);
			inFieldPane.setVisible(true);
			otherPane.setVisible(true); 
			savePane.setVisible(true);
			outFieldPane.setVisible(true);
			outFieldPaneTwo.setVisible(true);

		}
		else if (e.getSource() == back)
		{
			entryCreation.setVisible(true);
			editEntries.setVisible(true);
			back.setVisible(false);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false);
			outFieldPane.setVisible(false);
			outFieldPaneTwo.setVisible(false);
			

		}
		else if (e.getSource() == save)
		{
			String fullString = month.getText().trim() + "/" + day.getText().trim() + "/" + year.getText().trim();
			fullDate.setText(fullString); 
			String entryFullString = journalEntry.getText().trim(); 
			fullJournalEntry.setText(entryFullString); 
			
			
		}
		else
			; 
	}

		
	

}
