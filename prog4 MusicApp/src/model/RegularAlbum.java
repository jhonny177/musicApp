package model;

import java.util.HashSet;
import java.util.Set;


public class RegularAlbum extends Album {

	
	private RegularAlbum parentAlbum;
	private Set<RegularAlbum> subAlbums;
	
	//constructor
	public RegularAlbum(String name) {
		super();
		subAlbums = new HashSet<RegularAlbum>();
		this.albumName = name;
	}
	
	//söker och ser vilka subalbum som hör till objektet
	//returnerar alla subalbum och alla subalbums subalbum
	public Set<RegularAlbum> listSubAlbums(){
		Set<RegularAlbum> album = new HashSet<RegularAlbum>();
		for(RegularAlbum a:subAlbums) {
			album.add(a);
			album.addAll(a.listSubAlbums());
			}
		return album;
	}
	
	//Skapar ett subalbum till albumet
	public void addSubAlbum(RegularAlbum a) {
		subAlbums.add(a);
		a.setParentAlbum(this);
	}
	
	
	//Lägger till en låt till albumet och alla föräldraalbum får låten satt i sig
	public void addSong(SoundClip s) {
		listOfFiles.add(s);
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
	
	//Ändrar på albumets namn
	public void setName(String albumName) {
		this.albumName = albumName;
	}
	//sätter albummet att peka på sitt förälder album förutom root albummet
	public void setParentAlbum(RegularAlbum parent) {
		parentAlbum = parent;
	}
	//hämtar förälderalbummet
	@Override
	public RegularAlbum getParentAlbum() {
		return parentAlbum;
	}
	
	
	//Returnerar alla subalbum (endast denna nivå, inte rekursivt)
	public Set<RegularAlbum> getSubAlbums(){
		return subAlbums;
	}
	
	//rekusivt går igenom alla subalbum och tar bort det album som matchar input-parametern 
	public void deleteSubAlbum(RegularAlbum b) {
		for (RegularAlbum a:subAlbums) {
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
	public RegularAlbum getAlbumByName(String name) {
		RegularAlbum toReturn = null;
		for (RegularAlbum a:subAlbums) {
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

	//Tar bort en låt från albumet
	public void removeSong(SoundClip s) {
		listOfFiles.remove(s);
		for (RegularAlbum a:subAlbums) {
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
		RegularAlbum mementoParentAlbum = this.getParentAlbum();
		Set<SoundClip> mementoListOfFiles = new HashSet<SoundClip>();
		mementoListOfFiles.addAll(listOfFiles);
		Set<RegularAlbum> mementoSubAlbums = new HashSet<RegularAlbum>();
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
		private RegularAlbum mementoParentAlbum;
		private Set<SoundClip> mementoListOfFiles;
		private Set<RegularAlbum> mementoSubAlbums;
	
		public Memento(String albumName, RegularAlbum parentAlbum,Set<SoundClip> listOfSoundFiles,Set<RegularAlbum> subAlbums ) {
			this.mementoAlbumName = albumName;
			this.mementoParentAlbum = parentAlbum;
			this.mementoListOfFiles = listOfSoundFiles;
			this.mementoSubAlbums = subAlbums;
		}
		
	
	}
}
