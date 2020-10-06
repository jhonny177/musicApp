package app;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class subAlbumTest {

	private Album test;
	private Album sub;
	private Album subSub;
	
	@Test
	void test() {
		test = new Album("god");
		sub = new Album("morgon");
		subSub = new Album("igen");
		sub.addSubAlbum(subSub);
		test.addSubAlbum(sub);
		//testar outputten av subAlbum()
		Set<Album> output = test.listSubAlbums();
		String outStr = "";
		for(Album a: output) {
			outStr += a;
		}
		//gör settet som det skall se ut
		Set<Album> isNow = new HashSet<Album>();
		isNow.add(sub);
		isNow.add(subSub);
		String now = "";
		for(Album b:isNow) {
			now += b;
		}
		assertEquals(now,outStr);
	}
	
	@Test
	void createSubalbums() {
		Album root = new Album("root");
		root.addSubAlbum(new Album("subalbum"));
		root.getSubAlbums().iterator().next().addSubAlbum(new Album("subsubalbum"));
		assertEquals("subsubalbum", root.getSubAlbums().iterator().next().getSubAlbums().iterator().next().toString());
		assertEquals(root,root.getSubAlbums().iterator().next().getParentAlbum());
		assertEquals(root.getSubAlbums().iterator().next(),root.getSubAlbums().iterator().next().getSubAlbums().iterator().next().getParentAlbum());
	}
	
	@Test
	void removeAlbum() {
		Album root = new Album("root");
		root.addSubAlbum(new Album("sub"));
		//root should have one sub album
		assertEquals(1, root.getSubAlbums().size());
		Album subA = root.getSubAlbums().iterator().next();
		subA.setName("subalbum1");
		//delete sub album
		root.deleteSubAlbum(subA);
		//root should have zero sub albums
		assertEquals(0, root.getSubAlbums().size());
	}
	
	@Test
	void changeName() {
		Album rootAlbum = new Album("root");
		assertEquals("root", rootAlbum.toString());
		rootAlbum.setName("roten");
		assertEquals("roten",rootAlbum.toString());
	}
	@Test
	void save() {
		
		Album root = new Album("root");
		root.addSubAlbum(new Album("sub"));
		root.getSubAlbums().iterator().next().addSong(new SoundClip(new File("Sången")));
		AlbumCaretaker care = new AlbumCaretaker();

		//Save
		care.saveState(root);
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
