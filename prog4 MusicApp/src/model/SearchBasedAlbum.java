package model;

import java.util.HashSet;

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

}
