package app;



import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Album {	
	
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
	
	public void addSubAlbum(Album a) {
		subAlbums.add(a);
	}
	
	public void addSong(SoundClip s) {
		listOfFiles.add(s);
	}
	
	
	@Override
	public String toString() {
		return albumName;
	}
	
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	public LinkedList<SoundClip> getListOfFiles(){
		return listOfFiles;
	}
	public Set<Album> getSubAlbums(){
		return subAlbums;
	}
	
	public void deleteSubAlbums(String name) {
		for(Album a:subAlbums) {
			if(a.toString().equals(name)) {
				subAlbums.remove(a);
			}
		}
	}
	
	//rekusivt går igenom alla subalbum och tar bort den som matchar input-parametern 
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
}
