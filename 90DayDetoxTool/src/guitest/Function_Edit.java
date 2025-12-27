package guitest;

public class Function_Edit {
	MainGUI gui; 
	/**
	 * Constructor for gui
	 * @param gui
	 */
	public Function_Edit(MainGUI gui) { 
		this.gui = gui; 
	}
	/**
	 * Undo method 
	 */
	public void undo() {
		gui.undo.undo(); 
	}
	/**
	 * Redo method
	 */
	public void redo() {
		gui.undo.redo(); 
	}
}
