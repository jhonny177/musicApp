package app;

import java.util.Stack;

public class AlbumCaretaker {
	
	private final int capacityCap = 10;
	private Object saveObj;
	private Stack<Object> undoStack = new Stack<Object>();
	private Stack<Album> undoAlbums = new Stack<Album>();
	private Stack<Object> redoStack = new Stack<Object>();
	//sparar objectet och pushar det på en stack som aldrig blir större än size 10
	public void saveState(Album album) {
		saveObj = album.save();
		undoStack.push(saveObj);
		undoAlbums.push(album);
		System.out.println("STacksize: " + undoAlbums.size());
		if(undoStack.size()>capacityCap) {
			undoStack.remove(capacityCap);
			undoAlbums.remove(capacityCap);
		}
	}
	//hämtar tillbaka den senaste ändringen som är högst upp på stacken
	public Album undo(Album album) {
		Album a = undoAlbums.pop();
		a.restoreObj(undoStack.pop());
		System.out.println("Stacksize: " + undoAlbums.size());
		return a;
	}
	
	public void redo(Album album) {
		album.restoreObj(redoStack.pop());
	}
}
