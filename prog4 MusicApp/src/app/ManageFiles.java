package app;
import java.io.File;
import java.util.Set;

public class ManageFiles {
	
	Album root;
	//lägger till det nya sound clippet i root albumet
		public void createFile(File f) {
			SoundClip s = new SoundClip(f);
			root.getListOfFiles().add(s);
		}
	//tabort en fil i root albummet
		public void deleteFile(SoundClip s) {
			root.getListOfFiles().remove(s);
			for(Album a:root.getSubAlbums()) {
				if(fileExists(a,s)==true) {
					a.getListOfFiles().remove(s);
				}
			}
		}
	//sätta till en fil i ett sub album
		public void addFileToSub(Album a ,SoundClip s) {
			for(Album al:root.getSubAlbums()) {
				if(al.equals(a)) {
					al.getListOfFiles().add(s);
				}
			}
		}
	//tabort en fil i ett subAlbum och tar bort den från dess sub album
		public void deleteFromSubAlbum(Album a,SoundClip s) {
			Set<Album> temp = null;
			for(Album al:root.getSubAlbums()) {
				if(al.equals(a)) {
					al.getListOfFiles().remove(s);
				}
				temp = al.listSubAlbums();
			}
			for(Album b:root.getSubAlbums()) {
				for(Album t:temp) {
					if(b.equals(t)) {
						b.getListOfFiles().remove(s);
					}
				}
			}
		}
		
		
	//kontrollera om en ljud fil existerar i ett sub album
		public boolean fileExists(Album a,SoundClip s) {
			for(Album al:root.getSubAlbums()) {
				if(al.equals(a)) { {
					for(SoundClip sound:al.getListOfFiles()) {
						if(sound.equals(s)) {
							return true;
						}
					}
				}
			}
		}
		return false;
		}
}