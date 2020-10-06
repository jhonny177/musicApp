package controller;

import model.Album;

public class ManageAlbum{
	
	private Album root;
	
	//skapar ett root album
	public void createRootAlbum(String name) {;
	 	root = new Album(name);
	}
	
	//skapar ett subalbum åt ett annat album
	public void createSubAlbum(Album parent, String name) {
		parent.addSubAlbum(new Album(name));
	}
	
	//tar bort ett album och dess subalbum
	public void deleteAlbum(Album a) {
		root.deleteSubAlbum(a);
	}
	
	//Hittar ett album med hjälp av dess namn 
	public Album getAlbumByName(String name) {
		if (name.equals("root")) {
			return root;
		}
		return root.getAlbumByName(name);
	}
	
	//returnerar root albumet
	public Album getRootAlbum() {
		return root;
	}
	
	
}
