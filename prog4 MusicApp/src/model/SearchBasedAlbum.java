package model;

import java.util.HashSet;
import java.util.Set;


public class SearchBasedAlbum extends Album {
	Album root;

	//constructor
	public SearchBasedAlbum(String name, Album root) {
		super();
		this.albumName = name;
		this.root = root;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Album getParentAlbum() {
		return root;
	}

	@Override
	public void addSong(SoundClip s) {
		listOfFiles.add(s);
	}

	@Override
	public void removeSong(SoundClip s) {
		listOfFiles.remove(s);
	}

	@Override
	public Memento save() {
		String mementoAlbumName = this.toString();
		Set<SoundClip> mementoListOfFiles = new HashSet<SoundClip>();
		mementoListOfFiles.addAll(listOfFiles);
		return new Memento(mementoAlbumName,mementoListOfFiles);
	}

	@Override
	public void restoreObj(Object al) {
		Memento memento = (Memento) al;
		albumName = memento.mementoAlbumName;
		listOfFiles = memento.mementoListOfFiles;
	}

}
