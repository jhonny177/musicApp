package model;

import java.util.HashSet;
import java.util.Set;



public abstract class Album {

	protected String albumName;
	protected Set<SoundClip> listOfFiles;
	
	//constructor
	public Album() {
		listOfFiles = new HashSet<SoundClip>();
	}
	
	//returnerar en lista med alla låtar i albumet
	public Set<SoundClip> getListOfFiles(){
		return listOfFiles;
	}
	
	@Override
	public String toString() {
		return albumName;
	}
	
	public abstract Album getParentAlbum();
	public abstract void addSong(SoundClip s);
	public abstract void removeSong(SoundClip s);
	public abstract Memento save();
	public abstract void restoreObj(Object al);
	

	protected class Memento {
		
		protected String mementoAlbumName;
		protected RegularAlbum mementoParentAlbum;
		protected Set<SoundClip> mementoListOfFiles;
		protected Set<RegularAlbum> mementoSubAlbums;
	
		//constructor för då man skapar mementon av SearchBasedAlbum
		public Memento(String albumName, Set<SoundClip> listOfSoundFiles) {
			this.mementoAlbumName = albumName;
			this.mementoListOfFiles = listOfSoundFiles;
		}

		//constructor för då man skapar mementon av RegularAlbum
		public Memento(String albumName, RegularAlbum parentAlbum,Set<SoundClip> listOfSoundFiles,Set<RegularAlbum> subAlbums ) {
			this.mementoAlbumName = albumName;
			this.mementoParentAlbum = parentAlbum;
			this.mementoListOfFiles = listOfSoundFiles;
			this.mementoSubAlbums = subAlbums;
		}
		
	
	}
}
