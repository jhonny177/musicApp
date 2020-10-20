package controller;

import model.RegularAlbum;

public class ManageAlbum{
	
	private RegularAlbum root;
	
	//skapar ett root album
	public void createRootAlbum(String name) {;
	 	root = new RegularAlbum(name);
	}
	
	//skapar ett subalbum åt ett annat album
	public void createSubAlbum(RegularAlbum parent, String name) {
		parent.addSubAlbum(new RegularAlbum(name));
	}
	
	//tar bort ett album och dess subalbum
	public void deleteAlbum(RegularAlbum a) {
		root.deleteSubAlbum(a);
	}
	
	//Hittar ett album med hjälp av dess namn 
	public RegularAlbum getAlbumByName(String name) {
		if (name.equals("root")) {
			return root;
		}
		return root.getAlbumByName(name);
	}
	
	//returnerar root albumet
	public RegularAlbum getRootAlbum() {
		return root;
	}
	
	
}
