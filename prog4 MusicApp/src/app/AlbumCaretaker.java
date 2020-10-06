package app;

import java.util.Stack;

public class AlbumCaretaker {
	
	private final int capacityCap = 10;
	private Object saveObj;
	private Stack<Object> undoStack = new Stack<Object>();
	private Stack<Album> undoAlbums = new Stack<Album>();
	private Stack<Object> redoStack = new Stack<Object>();
	private Stack<Album> redoAlbums = new Stack<Album>();
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
	public Album undo() {
		Album a = undoAlbums.pop();
		saveRedoState(a);
		a.restoreObj(undoStack.pop());
		System.out.println("Stacksize: " + undoAlbums.size());
		return a;
	}
	
	private void saveRedoState(Album album) {
		saveObj = album.save();
		redoStack.push(saveObj);
		redoAlbums.push(album);
		System.out.println("RedoStackSize: " + redoAlbums.size());
		if(redoStack.size()>capacityCap) {
			redoStack.remove(capacityCap);
			redoAlbums.remove(capacityCap);
		}
	}
	
	public Album redo() {
		Album a = redoAlbums.pop();
		saveState(a);
		a.restoreObj(redoStack.pop());
		System.out.println("RedoStacksize: " + redoAlbums.size());
		return a;
	}
}
