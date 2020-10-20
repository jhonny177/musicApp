package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class Album {

	//constructor
	public Album() {
		listOfFiles = new HashSet<SoundClip>();
	}
	
	protected String albumName;
	protected Set<SoundClip> listOfFiles;
	
	public abstract Album getParentAlbum();

	@Override
	public String toString() {
		return albumName;
	}

	//returnerar en lista med alla låtar i albumet
	public Set<SoundClip> getListOfFiles(){
		return listOfFiles;
	}
}
