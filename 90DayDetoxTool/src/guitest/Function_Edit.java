package guitest;

public class Function_Edit {
	MainGUI gui; 
	public Function_Edit(MainGUI gui) { 
		this.gui = gui; 
	}
	public void undo() {
		gui.undo.undo(); 
	}
	public void redo() {
		gui.undo.redo(); 
	}
}
