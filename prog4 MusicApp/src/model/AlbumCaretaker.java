package model;

import java.util.Stack;

public class AlbumCaretaker {
	
	private final int capacityCap = 10;
	private Object saveObj;
	private Stack<Object> undoStack = new Stack<Object>();
	private Stack<RegularAlbum> undoAlbums = new Stack<RegularAlbum>();
	private Stack<Object> redoStack = new Stack<Object>();
	private Stack<RegularAlbum> redoAlbums = new Stack<RegularAlbum>();
	
	
	//sparar objectet och pushar det på en stack som aldrig blir större än size 10
	public void saveUndoState(RegularAlbum album) {
		saveObj = album.save();
		undoStack.push(saveObj);
		undoAlbums.push(album);
		if(undoStack.size()>capacityCap) {
			undoStack.remove(capacityCap);
			undoAlbums.remove(capacityCap);
		}
	}

	//Sparar albumets tillstånd då man trycker på undo till redoStacken för att kunna göras om
	private void saveRedoState(RegularAlbum album) {
		saveObj = album.save();
		redoStack.push(saveObj);
		redoAlbums.push(album);
		if(redoStack.size()>capacityCap) {
			redoStack.remove(capacityCap);
			redoAlbums.remove(capacityCap);
		}
	}
	
	//hämtar tillbaka den senaste ändringen som är högst upp på stacken
	public RegularAlbum undo() {
		RegularAlbum a = undoAlbums.pop();
		saveRedoState(a);
		a.restoreObj(undoStack.pop());
		return a;
	}
	
	//Gör om senaste undo
	public RegularAlbum redo() {
		RegularAlbum a = redoAlbums.pop();
		saveUndoState(a);
		a.restoreObj(redoStack.pop());
		return a;
	}
	public boolean isUndoEmpty() {
		if(undoStack.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isRedoEmpty() {
		if(redoStack.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}
}
