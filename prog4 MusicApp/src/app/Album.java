package app;



import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Album {	
	
	public static Album root;
	private String albumName;
	private LinkedList<SoundClip> listOfFiles;
	private Set<Album> subAlbums;
	
	public Album() {
		subAlbums = new HashSet<Album>();
	}
	
	public Album(String name) {
		subAlbums = new HashSet<Album>();
		this.albumName = name;
	}
	
	//söker och ser vilka subalbum som hör till objektet
	//returnerar alla subalbum och alla subalbums subalbum
	public Set<Album> listSubAlbums(){
		Set<Album> album = new HashSet<Album>();
		for(Album a:subAlbums) {
			album.add(a);
			album.addAll(a.listSubAlbums());
			}
		return album;
	}
	
	//Skapar ett subalbum till albumet
	public void addSubAlbum(Album a) {
		subAlbums.add(a);
	}
	
	//Lägger till en låt till albumet
	public void addSong(SoundClip s) {
		listOfFiles.add(s);
	}
	
	
	//Returnerar om en viss låt finns i albumet
	public boolean songExists(SoundClip s) {
		for(SoundClip sound:listOfFiles) {
			if(sound.equals(s)) {
				return true;
			}
		}
		return false;
	}
		
	@Override
	public String toString() {
		return albumName;
	}
	
	//Ändrar på albumets namn
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	
//	Ska tas bort...
//	public LinkedList<SoundClip> getListOfFiles(){
//		return listOfFiles;
//	}
	
	//Returnerar alla subalbum (endast denna nivå, inte rekursivt)
	public Set<Album> getSubAlbums(){
		return subAlbums;
	}
	
	//Ta bort...
//	Tar bort ett subalbum som finns i det här albumet
//	public void deleteSubAlbums(String name) {
//		for(Album a:subAlbums) {
//			if(a.toString().equals(name)) {
//				subAlbums.remove(a);
//			} else {
//				a.deleteSubAlbum(name);
//			}
//		}
//	}
	
	//rekusivt går igenom alla subalbum och tar bort det album som matchar input-parametern 
	public void deleteSubAlbum(Album b) {
		for (Album a:subAlbums) {
			if(a.equals(b)) {
				subAlbums.remove(a);
				break;
			} else {
				a.deleteSubAlbum(b);
			}
		}
	}

	//Söker efter ett album med ett visst namn och returnerar det
	//Går igenom albumet och alla sub album (och deras subalbum)  
	public Album getAlbumByName(String name) {
		Album toReturn = null;
		for (Album a:subAlbums) {
			if (a.toString().equals(name)) {
				toReturn = a;
				break;
			} else {
				toReturn =  a.getAlbumByName(name);
				if (toReturn != null) {
					return toReturn;
				}
			}
		}
		return toReturn;
	}
	public LinkedList<SoundClip> getlistOfFiles(){
		return listOfFiles;
	}

	public void removeSong(SoundClip s) {
		listOfFiles.remove(s);
	}
}
