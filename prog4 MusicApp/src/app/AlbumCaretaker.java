package app;

import java.util.Stack;

public class AlbumCaretaker {
	
	private final int capacityCap = 10;
	private Object saveObj;
	private Stack<Object> oldAlbums = new Stack<Object>();
	//sparar objectet och pushar det på en stack som aldrig blir större än size 10
	public void saveState(Album album) {
		saveObj = album.save();
		oldAlbums.push(saveObj);
		if(oldAlbums.size()>capacityCap) {
			oldAlbums.remove(capacityCap);
		}
	}
	//hämtar tillbaka den senaste ändringen som är högst upp på stacken
	public void restoreState(Album album) {
		album.restoreObj(oldAlbums.pop());
	}
}
