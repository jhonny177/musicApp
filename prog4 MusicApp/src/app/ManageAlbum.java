package app;

public class ManageAlbum{
	
//	private LinkedList<Album> albums;
	private Album root;
	
	//skapar ett root album
	public void createRootAlbum(String name) {;
	 	root = new Album(name);
//	 	Album.root = root;
//		Album a = new Album();
//		albums = new LinkedList<Album>();
//		a.setName(name);
//		albums.add(a);
	}
	
	//skapar ett subalbum åt ett annat album
	public void createSubAlbum(Album parent, String name) {
		parent.addSubAlbum(new Album(name));
	}
	
	//tar bort ett album och dess subalbum
	public void deleteAlbum(Album a) {
		root.deleteSubAlbum(a);
	}
	
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
