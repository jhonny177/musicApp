package app;
import java.io.File;
import java.util.Set;

public class ManageFiles {
	
	//lägger till det nya sound clippet i root albumetasdasdadasdasdasdasd
	public SoundClip createFile(File f) {
		SoundClip s = new SoundClip(f);
		return s;
	}
		
	//ta bort en fil i root albumet
	public void deleteFile(Album root,SoundClip s) {
		root.removeSong(s);
		for(Album a:root.getSubAlbums()) {
			if(fileExists(a,s)==true) {
				a.removeSong(s);
			}
		}
	}
		
	//sätta till en fil i ett sub album
	public void addFileToSub(Album a ,SoundClip s) {
		a.addSong(s);
	}
		
	//tabort en fil i ett subAlbum och tar bort den från dess sub album
	public void deleteFileFromSub(Album a,SoundClip s) {
		a.removeSong(s);
	}
		
	//kontrollera om en ljud fil existerar i ett sub album
	public boolean fileExists(Album a,SoundClip s) {
		return a.songExists(s);
	}
}