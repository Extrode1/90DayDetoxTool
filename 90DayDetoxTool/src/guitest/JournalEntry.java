package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 

public class JournalEntry {
	
	private JTextField day; 
	private JTextField month; 
	private JTextField year; 
	private JTextArea journalEntry; 
	private static int dayCounter = 0; 
	public JournalEntry (JTextField day, JTextField month, JTextField year, JTextArea journalEntry)
	{
		this.day = day; 
		this.month = month; 
		this.year = year; 
		this.journalEntry = journalEntry; 
		dayCounter++; 
	}
	public JTextField getDay()
	{
		return this.day; 
	}
	public JTextField getMonth()
	{
		return this.month; 
	}
	public JTextField getYear()
	{
		return this.year; 
	}
	public JTextArea getJournalEntry()
	{
		return this.journalEntry; 
	}
	public void setDay(JTextField day)
	{
		this.day = day; 
	}
	public void setMonth (JTextField month)
	{
		this.month = month; 
	}
	public void setYear (JTextField year)
	{
		this.year = year; 
	}
	public void setJournalEntry (JTextArea journalEntry)
	{
		this.journalEntry = journalEntry; 
	}
	

}
