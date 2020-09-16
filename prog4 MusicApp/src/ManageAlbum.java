package src;

import java.io.File;
import java.util.LinkedList;
import java.util.Set;

public class ManageAlbum{
	
	private LinkedList<Album> albums;
	
	//skapar ett root album
	public void createRootAlbum(String name) {;
		Album a = new Album();
		albums = new LinkedList<Album>();
		a.setName(name);
		albums.add(a);
	}
	//lägger till det nya sound clippet i root albumet
	public void createFile(File f) {
		SoundClip s = new SoundClip(f);
		albums.get(0).getListOfFiles().add(s);
	}
	//lagar ett sub album åt ett redan existerande album
	public void createSubAlbum(String parentName, String name) {
		Album a = new Album();
		a.setName(name);
		albums.add(a);
		for(Album al: albums) {
			if(al.toString().equals(parentName)) {
				al.addSubAlbum(a);
				
			}
		}
	}
	//tar bort ett album och dess subalbum och ser till att albums listan uppdateras där efter
	public void deleteAlbum(String name) {
		albums.get(0).s
	}
	//hittar förälder albummet till albummet
	public Album findParentAlbum(Album findParent) {
		for(Album a:albums) {
			for (Album b:a.getContainsSubAlbum()){
				if(b.toString().equals(findParent.toString())){
					return a;
				}
			}
		}
		return null;
	}
	
	
	
	public LinkedList<Album> getAlbumList() {
		return albums;
	}
	
	
}
