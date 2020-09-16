package app;
import java.io.File;
import java.util.Set;

public class ManageFiles {
	
	Album root = Album.root;
	
	//lägger till det nya sound clippet i root albumet
		public SoundClip createFile(File f) {
			SoundClip s = new SoundClip(f);
			return s;
		}
		
	//tabort en fil i root albummet
		public void deleteFile(SoundClip s) {
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
		public void deleteFromSubAlbum(Album a,SoundClip s) {
			a.removeSong(s);
		}
		
	//kontrollera om en ljud fil existerar i ett sub album
		public boolean fileExists(Album a,SoundClip s) {
			return a.songExists(s);
		}
}