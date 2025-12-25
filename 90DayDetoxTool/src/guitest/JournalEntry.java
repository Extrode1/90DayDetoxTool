package guitest;
import java.io.*; 

public class JournalEntry implements Serializable {
	
	private static final long serialVersionUID = -455091837724834099L;
	private String dayString; 
	private String monthString; 
	private String yearString; 
	private String entryFullString; 
	private String titleString; 
	/**
	 * Creates a journal entry object. 
	 * @param dayString
	 * @param monthString
	 * @param yearString
	 * @param entryFullString
	 * @param titleString
	 */
	public JournalEntry (String dayString, String monthString, String yearString, String entryFullString, String titleString) { 
		this.dayString = dayString; 
		this.monthString = monthString; 
		this.yearString = yearString; 
		this.entryFullString = entryFullString; 
		this.titleString = titleString; 
	}
	/**
	 * Getter method for day
	 * @return dayString
	 */
	public String getDay() { 
		return this.dayString; 
	}
	/**
	 * Getter method for month
	 * @return monthString
	 */
	/**
	 * Getter method for month
	 * @return monthString
	 */
	public String getMonth() { 
		return this.monthString; 
	}
	/**
	 * Getter method for year
	 * @return yearString
	 */
	public String getYear() { 
		return this.yearString; 
	}
	/**
	 * Getter method for journal entries
	 * @return entryFullString
	 */
	public String getJournalEntry() { 
		return this.entryFullString; 
	}
	/**
	 * Getter method for title
	 * @return titleString
	 */
	public String getTitleString () { 
		return this.titleString; 
	}
	/**
	 * Setter method for day
	 * @param dayString
	 */
	public void setDay(String dayString) { 
		this.dayString = dayString; 
	}
	/**
	 * Setter method for month
	 * @param monthString
	 */
	public void setMonth (String monthString) { 
		this.monthString = monthString; 
	}
	/**
	 * Setter method for year
	 * @param yearString
	 */
	public void setYear (String yearString) { 
		this.yearString = yearString; 
	}
	/**
	 * Setter method for journal entry
	 * @param entryFullString
	 */
	public void setJournalEntry (String entryFullString) { 
		this.entryFullString = entryFullString; 
	}
	/**
	 * Setter method for title
	 * @param titleString
	 */
	public void setTitleString (String titleString) { 
		this.titleString = titleString; 
	}
	/**
	 * toString method that returns the title
	 * @return titleString
	 */
	@Override
	public String toString() { 
		return titleString; 
	}
}
