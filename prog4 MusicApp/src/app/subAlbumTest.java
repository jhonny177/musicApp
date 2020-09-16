package app;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class subAlbumTest {

	private Album test;
	private Album sub;
	private Album subSub;
	
	@Test
	void test() {
		test = new Album();
		sub = new Album();
		subSub = new Album();
		test.setName("god");
		sub.setName("morgon");
		subSub.setName("igen");
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
	}
	
	@Test
	void removeAlbum() {
		Album root = new Album();
		root.addSubAlbum(new Album());
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

}
