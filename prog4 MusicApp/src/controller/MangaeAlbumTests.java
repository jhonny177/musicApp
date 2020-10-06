package controller;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Album;

class MangaeAlbumTests {

	ManageAlbum test;
	
	@Test
	void testCreateRootAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("Ölsånger");
		String output = test.getRootAlbum().toString();
		assertEquals("Ölsånger",output);
	}
	
	@Test
	void testCreateSubAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
		test.createSubAlbum(test.getRootAlbum(), "anivå1");
		test.createSubAlbum(test.getRootAlbum(), "bnivå1");
		test.createSubAlbum(test.getAlbumByName("anivå1"), "anivå2");
		Album[] output = new Album[0]; 
		output = (Album[]) test.getRootAlbum().getSubAlbums().toArray(output);
		assertTrue(output[0].toString().equals("anivå1")&&output[1].toString().equals("bnivå1")||output[0].toString().equals("bnivå1")&&output[1].toString().equals("anivå1"));
		output = test.getAlbumByName("anivå1").getSubAlbums().toArray(output);
		assertEquals("anivå2",output[0].toString());
	}
	@Test
	void testDeleteAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
		test.createSubAlbum(test.getRootAlbum(), "anivå1");
		test.createSubAlbum(test.getRootAlbum(), "bnivå1");
		test.createSubAlbum(test.getAlbumByName("anivå1"), "anivå2");
		test.deleteAlbum(test.getAlbumByName("bnivå1"));
		Set<Album> output = test.getRootAlbum().getSubAlbums();
		String outStr = "";
		for(Album a: output) {
			outStr += a+" ";
		}
		assertEquals("anivå1 ",outStr);
		
	}

}
