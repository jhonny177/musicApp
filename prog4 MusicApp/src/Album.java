package src;


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
	
	//söker och ser vilka sub album som hör till objektet
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
	public Set<Album> getContainsSubAlbum(){
		return subAlbums;
	}
	
	public void deleteSubAlbums(String name) {
		for(Album a:subAlbums) {
			if(a.toString().equals(name)) {
				subAlbums.remove(a);
			}
		}
	}
}
