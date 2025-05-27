package guitest;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 
import java.io.*; 

public class JournalEntry implements Serializable {
	
	private static final long serialVersionUID = -455091837724834099L;
	private String dayString; 
	private String monthString; 
	private String yearString; 
	private String entryFullString; 
	private String titleString; 
	public JournalEntry (String dayString, String monthString, String yearString, String entryFullString, String titleString)
	{
		this.dayString = dayString; 
		this.monthString = monthString; 
		this.yearString = yearString; 
		this.entryFullString = entryFullString; 
		this.titleString = titleString; 
	}
	public String getDay()
	{
		return this.dayString; 
	}
	public String getMonth()
	{
		return this.monthString; 
	}
	public String getYear()
	{
		return this.yearString; 
	}
	public String getJournalEntry()
	{
		return this.entryFullString; 
	}
	public String getTitleString ()
	{
		return this.titleString; 
	}
	public void setDay(String dayString)
	{
		this.dayString = dayString; 
	}
	public void setMonth (String monthString)
	{
		this.monthString = monthString; 
	}
	public void setYear (String yearString)
	{
		this.yearString = yearString; 
	}
	public void setJournalEntry (String entryFullString)
	{
		this.entryFullString = entryFullString; 
	}
	public void setTitleString (String titleString)
	{
		this.titleString = titleString; 
	}
	@Override
	public String toString()
	{
		return titleString; 
	}

	

}
