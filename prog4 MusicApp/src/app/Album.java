package app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Album {
	
	private String albumName;
	private Album parentAlbum;
	private Set<SoundClip> listOfFiles;
	private Set<Album> subAlbums;
	
	//constructor
	public Album(String name) {
		subAlbums = new HashSet<Album>();
		listOfFiles = new HashSet<SoundClip>();
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
		a.setParentAlbum(this);
	}
	
	
	//Lägger till en låt till albumet och alla föräldra album får låten satt i sig
	public void addSong(SoundClip s) {
//		if (!listOfFiles.contains(s)) {
		listOfFiles.add(s);
//		}
		if(getParentAlbum()!=null) {
			getParentAlbum().addSong(s);
		}
	}
	
	public void addAllSongs(Set<SoundClip> s) {
		for (SoundClip o: s) {
			addSong(o);
		}
	}
	
	//Returnerar om en viss låt finns i albumet
	public boolean songExists(SoundClip s) {
		return listOfFiles.contains(s);
	}
		
	@Override
	public String toString() {
		return albumName;
	}
	
	//Ändrar på albumets namn
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	//sätter albummet att peka på sitt förälder album förutom root albummet
	public void setParentAlbum(Album parent) {
		parentAlbum = parent;
	}
	//hämtar förälderalbummet
	public Album getParentAlbum() {
		return parentAlbum;
	}
	
	
	//Returnerar alla subalbum (endast denna nivå, inte rekursivt)
	public Set<Album> getSubAlbums(){
		return subAlbums;
	}
	
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
	
	//returnerar en lista med alla låtar i albumet
	public Set<SoundClip> getListOfFiles(){
		return listOfFiles;
	}

	//Tar bort en låt från albumet
	public void removeSong(SoundClip s) {
		listOfFiles.remove(s);
		for (Album a:subAlbums) {
			a.removeSong(s);
		}
	}
	//tar bort alla songer i allbumet
	public void removeAllSongs(Set<SoundClip> s) {
		for (SoundClip o: s) {
			removeSong(o);
		}
	}
		
	//sparar objectet som det är 
	public Memento save() {
		String mementoAlbumName = this.toString();
		Album mementoParentAlbum = this.getParentAlbum();
		Set<SoundClip> mementoListOfFiles = new HashSet<SoundClip>();
		mementoListOfFiles.addAll(listOfFiles);
		Set<Album> mementoSubAlbums = new HashSet<Album>();
		mementoSubAlbums.addAll(subAlbums);
		
		return new Memento(mementoAlbumName,mementoParentAlbum,mementoListOfFiles,mementoSubAlbums);
	}
	//återsteller senaste ändringen
	public void restoreObj(Object al) {
		Memento memento = (Memento) al;
		albumName = memento.mementoAlbumName;
		parentAlbum = memento.mementoParentAlbum;
		listOfFiles = memento.mementoListOfFiles;
		subAlbums = memento.mementoSubAlbums;
	}
	
	private class Memento {
		
		private String mementoAlbumName;
		private Album mementoParentAlbum;
		private Set<SoundClip> mementoListOfFiles;
		private Set<Album> mementoSubAlbums;
	
		public Memento(String albumName, Album parentAlbum,Set<SoundClip> listOfSoundFiles,Set<Album> subAlbums ) {
			this.mementoAlbumName = albumName;
			this.mementoParentAlbum = parentAlbum;
			this.mementoListOfFiles = listOfSoundFiles;
			this.mementoSubAlbums = subAlbums;
		}
		
	
	}
}
