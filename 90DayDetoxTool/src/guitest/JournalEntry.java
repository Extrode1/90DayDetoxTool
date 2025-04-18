package guitest;
import java.util.ArrayList; 

public class JournalEntry {
	
	private int day; 
	private int month; 
	private int year; 
	private String journalEntry; 
	private static int dayCounter = 0; 
	public JournalEntry (int day, int month, int year, String journalEntry)
	{
		this.day = day; 
		this.month = month; 
		this.year = year; 
		this.journalEntry = journalEntry; 
		dayCounter++; 
	}
	public int getDay()
	{
		return this.day; 
	}
	public int getMonth()
	{
		return this.month; 
	}
	public int getYear()
	{
		return this.year; 
	}
	public String getJournalEntry()
	{
		return this.journalEntry; 
	}
	public void setDay(int day)
	{
		this.day = day; 
	}
	public void setMonth (int month)
	{
		this.month = month; 
	}
	public void setYear (int year)
	{
		this.year = year; 
	}
	public void setJournalEntry (String journalEntry)
	{
		this.journalEntry = journalEntry; 
	}
	

}
