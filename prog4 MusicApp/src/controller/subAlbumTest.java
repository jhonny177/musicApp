package controller;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.RegularAlbum;
import model.AlbumCaretaker;
import model.SoundClip;

class subAlbumTest {

	private RegularAlbum test;
	private RegularAlbum sub;
	private RegularAlbum subSub;
	
	@Test
	void test() {
		test = new RegularAlbum("god");
		sub = new RegularAlbum("morgon");
		subSub = new RegularAlbum("igen");
		sub.addSubAlbum(subSub);
		test.addSubAlbum(sub);
		//testar outputten av subAlbum()
		Set<RegularAlbum> output = test.listSubAlbums();
		String outStr = "";
		for(RegularAlbum a: output) {
			outStr += a;
		}
		//gör settet som det skall se ut
		Set<RegularAlbum> isNow = new HashSet<RegularAlbum>();
		isNow.add(sub);
		isNow.add(subSub);
		String now = "";
		for(RegularAlbum b:isNow) {
			now += b;
		}
		assertEquals(now,outStr);
	}
	
	@Test
	void createSubalbums() {
		RegularAlbum root = new RegularAlbum("root");
		root.addSubAlbum(new RegularAlbum("subalbum"));
		root.getSubAlbums().iterator().next().addSubAlbum(new RegularAlbum("subsubalbum"));
		assertEquals("subsubalbum", root.getSubAlbums().iterator().next().getSubAlbums().iterator().next().toString());
		assertEquals(root,root.getSubAlbums().iterator().next().getParentAlbum());
		assertEquals(root.getSubAlbums().iterator().next(),root.getSubAlbums().iterator().next().getSubAlbums().iterator().next().getParentAlbum());
	}
	
	@Test
	void removeAlbum() {
		RegularAlbum root = new RegularAlbum("root");
		root.addSubAlbum(new RegularAlbum("sub"));
		//root should have one sub album
		assertEquals(1, root.getSubAlbums().size());
		RegularAlbum subA = root.getSubAlbums().iterator().next();
		subA.setName("subalbum1");
		//delete sub album
		root.deleteSubAlbum(subA);
		//root should have zero sub albums
		assertEquals(0, root.getSubAlbums().size());
	}
	
	@Test
	void changeName() {
		RegularAlbum rootAlbum = new RegularAlbum("root");
		assertEquals("root", rootAlbum.toString());
		rootAlbum.setName("roten");
		assertEquals("roten",rootAlbum.toString());
	}
	@Test
	void save() {
		
		RegularAlbum root = new RegularAlbum("root");
		root.addSubAlbum(new RegularAlbum("sub"));
		root.getSubAlbums().iterator().next().addSong(new SoundClip(new File("Sången")));
		AlbumCaretaker care = new AlbumCaretaker();

		//Save
		care.saveUndoState(root);
		assertEquals("[sub]", root.getSubAlbums().toString());
		
		//Delete
		root.deleteSubAlbum(root.getAlbumByName("sub"));
		assertEquals("[]", root.getSubAlbums().toString());

		//Restore
		care.undo();
		assertEquals("[sub]", root.getSubAlbums().toString());
		assertEquals("[Sången]", root.getSubAlbums().iterator().next().getListOfFiles().toString());
	}

}
