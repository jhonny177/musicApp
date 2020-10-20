package controller;
import java.io.File;


import model.RegularAlbum;
import model.SoundClip;

public class ManageFiles {
	
	//lägger till det nya sound clippet i root albumet
	public SoundClip createFile(File f) {
		SoundClip s = new SoundClip(f);
		return s;
	}
		
	//ta bort en fil i root albumet
	public void deleteFile(RegularAlbum root,SoundClip s) {
		root.removeSong(s);
		for(RegularAlbum a:root.getSubAlbums()) {
			if(fileExists(a,s)==true) {
				a.removeSong(s);
			}
		}
	}
		
	//sätta till en fil i ett sub album
	public void addFileToSub(RegularAlbum a ,SoundClip s) {
		a.addSong(s);
	}
		
	//tabort en fil i ett subAlbum och tar bort den från dess sub album
	public void deleteFileFromSub(RegularAlbum a,SoundClip s) {
		a.removeSong(s);
	}
		
	//kontrollera om en ljud fil existerar i ett sub album
	public boolean fileExists(RegularAlbum a,SoundClip s) {
		return a.songExists(s);
	}
}