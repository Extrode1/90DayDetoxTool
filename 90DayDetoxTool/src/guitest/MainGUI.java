package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.*; 

import java.io.*; 
import java.util.*;
import javax.swing.UIManager.*; 
import java.time. *; 

public class MainGUI implements ActionListener, ListSelectionListener, WindowListener
{
	//declare JFrame
	private JFrame frame; 
	
	//declare buttons
	private JButton entryCreation = new JButton("Create a new journal entry"); 
	private JButton editEntries = new JButton("Edit current journal entries");
	private JButton startDetox = new JButton("Start a 90 Day Detox"); 
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
	
	
	//create JMenu
	private JMenuBar menuBar; 
	private JMenu a, b, c, d, e; 
	private JMenuItem a1, b1, b2, b3, b4, b5, b6, c1, d1, e1;  
	private JRadioButtonMenuItem rbMenuItem; 
	private JCheckBoxMenuItem cbMenuItem; 
	//Java IO to save file
	private String fileName = "JournalEntries"; 
	
	//try to fix nullpointerexception
	ArrayList<JournalEntry> dataList; 
	
	//add undo manager
	UndoManager undo = new UndoManager(); 
	Function_Edit edit = new Function_Edit(this); 
	
	//create about page
	private JPanel aboutPane;
	private JLabel aboutTheApp; 
	private JLabel aboutTheApp2; 
	
	//create JLabel that replaces original anonymous objects
	private JLabel titleBox; 
	private JLabel dayBox; 
	private JLabel monthBox; 
	private JLabel yearBox; 
	private JLabel entryBox; 
	//create text editor features (bold, italic, underline) 
	//create button for bold text
	private JButton bold; 
	//create italic button
	private JButton italic; 
	//create underline button
	private JButton underline; 
	//try to add JTextPane
	private JTextPane journalEntryPane; 
	//add JCheckBox
	private JCheckBox boldCheckBox; 
	
	public MainGUI()
	{
		frame = new JFrame ("90 Day Detox Tool"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(1100, 900); 
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
		//create bold button
		String boldSymbol = new String("𝐁");
		bold = new JButton(boldSymbol);
		boldCheckBox = new JCheckBox(boldSymbol); 
		//create italic button
		String italicSymbol = new String("𝐼"); 
		italic = new JButton(italicSymbol); 
		//create underline symbol
		underline = new JButton("<html><u>U</u></html>"); 
		//change size of text change JButtons		
		bold.setPreferredSize(new Dimension(115, 50));
		italic.setPreferredSize(new Dimension(50, 50));
		underline.setPreferredSize(new Dimension(115, 50));
		//change font of button
		entryCreation.setFont(new Font("Calibri", Font.BOLD, 30));
		editEntries.setFont(new Font("Calibri", Font.BOLD, 30)); 
		startDetox.setFont(new Font("Calibri", Font.BOLD, 30));		
		bold.setFont(new Font("serif", Font.BOLD, 30));
		italic.setFont(new Font("serif", Font.BOLD, 30));
		underline.setFont(new Font("serif", Font.BOLD, 30));
		back.setFont(new Font("Calibri", Font.PLAIN, 17)); 
		save.setFont(new Font("Calibri", Font.PLAIN, 17));
		saveExisting.setFont(new Font("Calibri", Font.PLAIN, 17));
		deleteEntry.setFont(new Font("Calibri", Font.PLAIN, 17));
		editEntry.setFont(new Font("Calibri", Font.PLAIN, 17));
		//add buttons to JFrame
		frame.add(mainPane); 
		frame.add(entryCreation); 
		frame.add(editEntries); 
		frame.add(back);
		frame.add(startDetox);

		//add action listener
		entryCreation.addActionListener(this);
		editEntries.addActionListener(this);
		back.addActionListener(this); 
		bold.addActionListener(this); 
		//create JTextFields
		inFieldPane = new JPanel();
		otherPane = new JPanel(); 
		day = new JTextField (15);
		day.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e)
					{
						//remember the edit and update menus
						undo.addEdit(e.getEdit());
						
					}
				}
				
				
				);
		month = new JTextField (15); 
		month.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e)
					{
						//remember the edit and update menus
						undo.addEdit(e.getEdit());
						
					}
				}
				
				
				);
		year = new JTextField (15); 
		year.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e)
					{
						//remember the edit and update menus
						undo.addEdit(e.getEdit());
						
					}
				}
				
				
				);;
		fullDate = new JTextField (15); 
		title = new JTextField(15); 
		title.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e)
					{
						//remember the edit and update menus
						undo.addEdit(e.getEdit());
						
					}
				}
				
				
				);
		fullDate.setEditable(false); 
		journalEntry = new JTextArea (15, 30);
		journalEntry.setLineWrap(true);
		journalEntry.setWrapStyleWord(true); 
		journalEntry.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e)
					{
						//remember the edit and update menus
						undo.addEdit(e.getEdit());
						
					}
				}
				
				
				);
		journalEntryPane = new JTextPane (); 
		
		otherPane.setLayout(new BorderLayout());
		//create labelled fields		
		frame.add(inFieldPane); 
		frame.add(otherPane); 
		inFieldPane.setLayout(new GridLayout(0, 2)); 
		//create user input in GUI
		titleBox = new JLabel("Entry Title"); 
		inFieldPane.add(titleBox); 
		inFieldPane.add(title); 
		title.addActionListener(this); 
		
		yearBox = new JLabel("Year"); 
		inFieldPane.add(yearBox); 
		inFieldPane.add(year); 
		year.addActionListener(this);
		
		monthBox = new JLabel("Month"); 
		inFieldPane.add(monthBox); 
		inFieldPane.add(month); 
		month.addActionListener(this);
		
		dayBox = new JLabel("Day"); 
		inFieldPane.add(dayBox); 
		inFieldPane.add(day); 
		day.addActionListener(this);
		
		
		//add JScrollPane to journal entry box
		JScrollPane scrollPane = new JScrollPane(journalEntry); 
		//create journal entry box
		entryBox = new JLabel("Journal Entry"); 
		//add components into otherPane
		otherPane.add(entryBox, BorderLayout.NORTH); 
		otherPane.add(scrollPane, BorderLayout.SOUTH); 
//		otherPane.add(bold, BorderLayout.WEST); 
		otherPane.add(italic, BorderLayout.CENTER); 
		otherPane.add(underline, BorderLayout.EAST); 
		otherPane.add(boldCheckBox, BorderLayout.WEST); 
		
		//change JLabel font to Calibri
		titleBox.setFont(new Font("Calibri", Font.ITALIC, 15));
		yearBox.setFont(new Font("Calibri", Font.ITALIC, 15));
		monthBox.setFont(new Font("Calibri", Font.ITALIC, 15));
		dayBox.setFont(new Font("Calibri", Font.ITALIC, 15));
		entryBox.setFont(new Font("Calibri", Font.ITALIC, 15));

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
		list.setFont(new Font("Calibri", Font.PLAIN, 15));

		//put list into JScrollPane
		entryList = new JScrollPane(list); 
		entryList.setPreferredSize(new Dimension(200, 600)); 
		entryPane = new JPanel(new BorderLayout()); 
		entryLabel = new JLabel("Entries"); 
		entryLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
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
		
		//instantiate JMenu
		menuBar = new JMenuBar(); 
		a = new JMenu("File"); 
		b = new JMenu("Edit"); 
		c = new JMenu("Settings"); 
		d = new JMenu("View"); 
		e = new JMenu("Help"); 
		//create submenu items
		a1 = new JMenuItem("New"); 
		b1 = new JMenuItem("Undo                                                                    Ctrl+Z");
		b1.addActionListener(this);
		b2 = new JMenuItem("Redo                                                                    Ctrl+Y");
		b2.addActionListener(this);
		b3 = new JMenuItem("Move caret forward                                      Ctrl+B");
		b3.addActionListener(this);
		b4 = new JMenuItem("Move caret backward                                   Ctrl+F");
		b4.addActionListener(this);
		b5 = new JMenuItem("Move caret down one line                         Ctrl+N"); 
		b5.addActionListener(this);
		b6 = new JMenuItem("Move caret up one line                               Ctrl+P"); 
		b6.addActionListener(this); 
		c1 = new JMenuItem("Properties"); 
		c1.addActionListener(this); 
		d1 = new JMenuItem("Change theme"); 
		d1.addActionListener(this);
		e1 = new JMenuItem("About");
		e1.addActionListener(this); 
		//change fonts to Calibri
		a.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		c.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		d.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		e.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		//change submenu fonts to Calibri
		a1.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b1.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b2.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b3.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b4.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b5.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		b6.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		c1.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		d1.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		e1.setFont(new Font ("Calibri", Font.PLAIN, 15)); 
		//add all sub-menu items to menu
		a.add(a1);
		b.add(b1);
		b.add(b2);
		b.add(b3); 
		b.add(b4); 
		b.add(b5); 
		b.add(b6); 
		c.add(c1);
		d.add(d1); 
		e.add(e1); 
		menuBar.add(a); 
		menuBar.add(b); 
		menuBar.add(c);
		menuBar.add(d);
		menuBar.add(e); 
		a.setMargin(new Insets(6, 6, 6, 6));
		b.setMargin(new Insets(6, 6, 6, 6));
		c.setMargin(new Insets(6, 6, 6, 6));
		d.setMargin(new Insets(6, 6, 6, 6));
		e.setMargin(new Insets(6, 6, 6, 6));
		frame.setJMenuBar(menuBar); 
		
		//create input map for every text box
		InputMap inputMap1 = journalEntry.getInputMap(JComponent.WHEN_FOCUSED); 
		InputMap inputMap2 = day.getInputMap(JComponent.WHEN_FOCUSED); 
		InputMap inputMap3 = month.getInputMap(JComponent.WHEN_FOCUSED); 
		InputMap inputMap4 = year.getInputMap(JComponent.WHEN_FOCUSED); 
		InputMap inputMap5 = title.getInputMap(JComponent.WHEN_FOCUSED); 
		//create action map for every text box
		ActionMap actionMap1 = journalEntry.getActionMap();
		ActionMap actionMap2 = day.getActionMap();
		ActionMap actionMap3 = month.getActionMap();
		ActionMap actionMap4 = year.getActionMap();
		ActionMap actionMap5 = title.getActionMap(); 
		//set key bind to Ctrl-B
		KeyStroke keyBack = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK); 
		inputMap1.put(keyBack, DefaultEditorKit.backwardAction);
		inputMap2.put(keyBack, DefaultEditorKit.backwardAction);
		inputMap3.put(keyBack, DefaultEditorKit.backwardAction);
		inputMap4.put(keyBack, DefaultEditorKit.backwardAction);
		inputMap5.put(keyBack, DefaultEditorKit.backwardAction);

		//add keybinding for moving caret forwards in journal entry box
		KeyStroke keyForward = KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK); 
		inputMap1.put(keyForward, DefaultEditorKit.forwardAction);
		inputMap2.put(keyForward, DefaultEditorKit.forwardAction);
		inputMap3.put(keyForward, DefaultEditorKit.forwardAction);
		inputMap5.put(keyForward, DefaultEditorKit.forwardAction);
		inputMap5.put(keyForward, DefaultEditorKit.forwardAction);
		//add keybinding to move caret down one line
		KeyStroke keyDown = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK); 
		inputMap1.put(keyDown, DefaultEditorKit.downAction);
		inputMap2.put(keyDown, DefaultEditorKit.downAction);
		inputMap3.put(keyDown, DefaultEditorKit.downAction);
		inputMap5.put(keyDown, DefaultEditorKit.downAction);
		inputMap5.put(keyDown, DefaultEditorKit.downAction);
		//add keybinding to move caret up one line
		KeyStroke keyUp = KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK); 
		inputMap1.put(keyUp, DefaultEditorKit.upAction);
		inputMap2.put(keyUp, DefaultEditorKit.upAction);
		inputMap3.put(keyUp, DefaultEditorKit.upAction);
		inputMap5.put(keyUp, DefaultEditorKit.upAction);
		inputMap5.put(keyUp, DefaultEditorKit.upAction);
		//set up undo key
		KeyStroke undoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
		inputMap1.put(undoKey, "Undo");
		inputMap2.put(undoKey, "Undo");
		inputMap3.put(undoKey, "Undo");
		inputMap4.put(undoKey, "Undo");
		inputMap5.put(undoKey, "Undo");
		//set up redo key 
		KeyStroke redoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
		inputMap1.put(redoKey, "Redo");
		inputMap2.put(redoKey, "Redo");
		inputMap3.put(redoKey, "Redo");
		inputMap4.put(redoKey, "Redo");
		inputMap5.put(redoKey, "Redo");
		//set up actionMap for undo
		actionMap1.put("Undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
						if (undo.canUndo())
							undo.undo(); 
						
					}
					catch (CannotRedoException ex) {
						JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);

					}
			}
		
			
		});
		actionMap2.put("Undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
						if (undo.canUndo())
							undo.undo(); 
						
					}
					catch (CannotRedoException ex) {
						JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);

					}
			}
		
			
		});
		actionMap3.put("Undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
						if (undo.canUndo())
							undo.undo(); 
						
					}
					catch (CannotRedoException ex) {
						JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);

					}
			}
		
			
		});
		actionMap4.put("Undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
						if (undo.canUndo())
							undo.undo(); 
						
					}
					catch (CannotRedoException ex) {
						JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);

					}
			}
		
			
		});
		actionMap5.put("Undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
						if (undo.canUndo())
							undo.undo(); 
						
					}
					catch (CannotRedoException ex) {
						JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);

					}
			}
		
			
		});
		//set up action map for redo
				actionMap1.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});
				actionMap1.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});	
				actionMap2.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});		
				actionMap3.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});		
				actionMap4.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});		
				actionMap5.put("Redo", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
								if(undo.canRedo())
									undo.redo(); 
								
							}
							catch (CannotRedoException ex) {
								JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "redo error", JOptionPane.ERROR_MESSAGE);

							}
					}
				
					
				});		
				
		//add information for about page
		aboutPane = new JPanel(new BorderLayout()); 
		frame.add(aboutPane);
		aboutTheApp = new JLabel("<html>This application was made to help those who struggle with a video game addiction. This app specifically <br/> helps you with keeping track of your thoughts and feelings during the 90 day detox. To use this app, you </html>");
		aboutTheApp2 = new JLabel("<html> can create a new entry every single day of the 90 day detox. </html>"); 
		
		aboutTheApp.setFont(new Font("Calibri",  Font.PLAIN, 20)); 
		aboutTheApp2.setFont(new Font("Calibri",  Font.PLAIN, 20)); 

		aboutPane.add(aboutTheApp, BorderLayout.NORTH); 
		aboutPane.add(aboutTheApp2, BorderLayout.SOUTH); 

		aboutPane.setVisible(false);
		
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
		//change look and feel to nimbus
		try {
	
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName()); 
					break; 
				}
			}
		} catch (Exception e) {
			//use another look and feel if nimbus isn't available
		}
		new MainGUI();
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == entryCreation)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			startDetox.setVisible(false); 
			back.setVisible(true);
			inFieldPane.setVisible(true);
			otherPane.setVisible(true); 
			savePane.setVisible(true);
			mainPane.setVisible(false);
			aboutPane.setVisible(false);
			
				
			
		}
		else if (e.getSource() == editEntries)
		{
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			startDetox.setVisible(false);
			back.setVisible(true);
			mainPane.setVisible(false);
			entryList.setVisible(true); 
			entryLabel.setVisible(true); 
			deleteEntry.setVisible(true);
			editEntry.setVisible(true);
			aboutPane.setVisible(false);

			

		}
		else if (e.getSource() == back)
		{
			entryCreation.setVisible(true);
			editEntries.setVisible(true);
			startDetox.setVisible(true);
			back.setVisible(false);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false);
			mainPane.setVisible(true);
			entryList.setVisible(false); 
			entryLabel.setVisible(false);
			deleteEntry.setVisible(false);
			editEntry.setVisible(false);
			aboutPane.setVisible(false);


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
			aboutPane.setVisible(false);

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
			startDetox.setVisible(false);
			mainPane.setVisible(false);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false); 
			entryList.setVisible(true); 
			entryLabel.setVisible(true); 
			deleteEntry.setVisible(true);
			editEntry.setVisible(true);	
			aboutPane.setVisible(false);

			//reset fields to blank
			day.setText(""); 
			month.setText("");
			year.setText(""); 
			journalEntry.setText(""); 
			title.setText(""); 
			back.setEnabled(true); 
			
			//original entry editing shows
	

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
			startDetox.setVisible(false); 
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
			aboutPane.setVisible(false);

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
		else if (e.getSource() == b1)
		{
			try
			{
				if (undo.canUndo())
					undo.undo(); 
			}
			catch (CannotUndoException ex) {
				JOptionPane.showMessageDialog(frame, "Unable to undo: " + ex, "Undo error", JOptionPane.ERROR_MESSAGE);
				
		
			}
		}
		else if (e.getSource() == b2)
		{
			try {
				if (undo.canRedo())
					undo.redo(); 
			}
			catch (CannotRedoException ex) {
				JOptionPane.showMessageDialog(frame, "Unable to redo: " + ex, "Redo error", JOptionPane.ERROR_MESSAGE);

			}
			
		}
		else if (e.getSource() == e1)
		{
			//hide information when about is clicked
			entryCreation.setVisible(false);
			editEntries.setVisible(false);
			startDetox.setVisible(false); 
			back.setVisible(true);
			inFieldPane.setVisible(false);
			otherPane.setVisible(false); 
			savePane.setVisible(false);
			mainPane.setVisible(false);
			aboutPane.setVisible(true);
			
			//make entry information hidden  
			entryList.setVisible(false); 
			entryLabel.setVisible(false); 
			deleteEntry.setVisible(false);
			editEntry.setVisible(false);

		}
		else if (e.getSource() == bold)
		{
			//change text to bold
			Font font = journalEntry.getFont();
			journalEntry.setFont(font.deriveFont(Font.BOLD));
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
