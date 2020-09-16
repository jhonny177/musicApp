package app;
import java.io.File;
import java.util.Set;

public class ManageFiles extends Album {

	//lägger till det nya sound clippet i root albumet
		public void createFile(File f) {
			SoundClip s = new SoundClip(f);
			getListOfFiles().add(s);
		}
	//tabort en fil i root albummet
		public void deleteFile(SoundClip s) {
			getListOfFiles().remove(s);
		}
	//sätta till en fil i ett sub album
		public void addFileToSub(Album a ,SoundClip s) {
			for(Album al:getSubAlbums()) {
				if(al.equals(a)) {
					al.getListOfFiles().add(s);
				}
			}
		}
	//tabort en fil i ett subAlbum och tar bort den från dess sub album
		public void deleteFromSubAlbum(Album a,SoundClip s) {
			for(Album al:getSubAlbums()) {
				if(al.equals(a)) {
					al.getListOfFiles().remove(s);
				}
				Set<Album> temp = al.listSubAlbums();
			}
		}
		
		
	//kontrollera om en ljud fil existerar i ett sub album
		public boolean fileExists(Album a,SoundClip s) {
			for(Album al:getSubAlbums()) {
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