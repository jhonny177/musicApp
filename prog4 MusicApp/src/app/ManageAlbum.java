package app;


import java.io.File;
import java.util.LinkedList;
import java.util.Set;

public class ManageAlbum{
	
//	private LinkedList<Album> albums;
	private Album root;
	
	//skapar ett root album
	public void createRootAlbum(String name) {;
	 	root = new Album(name);
//		Album a = new Album();
//		albums = new LinkedList<Album>();
//		a.setName(name);
//		albums.add(a);
	}
	//lägger till det nya sound clippet i root albumet
	public void createFile(File f) {
		SoundClip s = new SoundClip(f);
		root.getListOfFiles().add(s);	//TODO ändra till addSong()
	}
	//lagar ett sub album åt ett redan existerande album
	public void createSubAlbum(Album parent, String name) {
		parent.addSubAlbum(new Album(name));
	}
	//tar bort ett album och dess subalbum och ser till att albums listan uppdateras där efter
	public void deleteAlbum(Album a) {
		root.deleteSubAlbum(a);
	}
	//hittar förälder albummet till albummet
//	public Album findParentAlbum(Album findParent) {
//		for(Album a:albums) {
//			for (Album b:a.getContainsSubAlbum()){
//				if(b.toString().equals(findParent.toString())){
//					return a;
//				}
//			}
//		}
//		return null;
//	}
	
	public Album getAlbumByName(String name) {
		if (name.equals("root")) {
			return root;
		}
		return root.getAlbumByName(name);
	}
	
	public Album getRootAlbum() {
		return root;
	}
	
	
}
