
import java.util.HashSet;
import java.util.Set;


public class Album {	
	
	private String albumName;
	private Set<SoundClip> listOfFiles;
	private Set<Album> ContainsSubAlbum;
	
	public Set<Album> subAlbum(){
		Set<Album> album = new HashSet<Album>();
		for(Album a:ContainsSubAlbum) {
			album.add(a);
			album.addAll(a.subAlbum());
			}
		return album;
	}
	public Set<SoundClip> files(){
		Set<SoundClip> clipy = new HashSet<SoundClip>();
		for(SoundClip s:listOfFiles) {
			clipy.add(s);
			clipy.addAll(s.files());
		}
		return clipy;
	}
	
	
	
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	public String getName() {
		return albumName;
	}
}
