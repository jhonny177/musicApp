package app;

import java.util.Stack;

public class AlbumCaretaker {
	
	private final int capacityCap = 10;
	private Object saveObj;
	private Stack<Object> undoStack = new Stack<Object>();
	private Stack<Object> redoStack = new Stack<Object>();
	//sparar objectet och pushar det på en stack som aldrig blir större än size 10
	public void saveState(Album album) {
		saveObj = album.save();
		undoStack.push(saveObj);
		if(undoStack.size()>capacityCap) {
			undoStack.remove(capacityCap);
		}
	}
	//hämtar tillbaka den senaste ändringen som är högst upp på stacken
	public void undo(Album album) {
		album.restoreObj(undoStack.pop());
	}
	
	public void redo(Album album) {
		album.restoreObj(redoStack.pop());
	}
}
