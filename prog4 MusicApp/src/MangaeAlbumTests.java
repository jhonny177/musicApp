package src;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class MangaeAlbumTests {

	ManageAlbum test;
	
	@Test
	void testCreateRootAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("Ölsånger");
		String output = test.getAlbumList().get(0).toString();
		assertEquals("Ölsånger",output);
	}
	@Test
	void testCreateSubAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
		test.createSubAlbum( "root", "anivå1");
		test.createSubAlbum("root", "bnivå1");
		test.createSubAlbum("anivå1", "anivå2");
		Set<Album> output = test.getAlbumList().get(0).getContainsSubAlbum();
		String outStr = "";
		for(Album a: output) {
			outStr += a.toString()+" ";
		}
		assertEquals("bnivå1 anivå1 ",outStr);
		output = test.getAlbumList().get(1).getContainsSubAlbum();
		outStr = "";
		for(Album a: output) {
			outStr += a.toString()+" ";
		}
		assertEquals("anivå2 ",outStr);
	}
	@Test
	void testDeleteAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
		test.createSubAlbum("root", "anivå1");
		test.createSubAlbum("root", "bnivå1");
		test.createSubAlbum("anivå1", "anivå2");
		test.deleteAlbum("bnivå1");
		Set<Album> output = test.getAlbumList().get(0).getContainsSubAlbum();
		String outStr = "";
		for(Album a: output) {
			outStr += a+" ";
		}
		assertEquals("anivå1 ",outStr);
		
	}
	@Test
	void testFindparent() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
		test.createSubAlbum( "root", "anivå1");
		test.createSubAlbum("root", "bnivå1");
		test.createSubAlbum("anivå1", "anivå2");
		test.findParentAlbum(findParent)
	}

}
