
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Album {	
	
	private String albumName;
	private LinkedList<SoundClip> listOfFiles;
	private Set<Album> containsSubAlbum = new HashSet<Album>();
	
	
	//söker och ser vilka sub album som hör till objektet
	public Set<Album> subAlbum(){
		Set<Album> album = new HashSet<Album>();
		for(Album a:containsSubAlbum) {
			album.add(a);
			album.addAll(a.subAlbum());
			}
		return album;
	}
	
	
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	public String getName() {
		return albumName;
	}
	public LinkedList<SoundClip> getListOfFiles(){
		return listOfFiles;
	}
	public Set<Album> getContainsSubAlbum(){
		return containsSubAlbum;
	}
	public void setContainsSubAlbum(Set<Album> containsSubAlbum){
		this.containsSubAlbum = containsSubAlbum;
	}
	// initizialiserar containsSubAlbum set
	public void initilizeContainsSubAlbum() {
		containsSubAlbum = new HashSet<Album>();
	}
	public void deleteSubAlbums(String name) {
		for(Album a:containsSubAlbum) {
			if(a.getName()==name) {
				containsSubAlbum.remove(a);
			}
		}
	}
}
