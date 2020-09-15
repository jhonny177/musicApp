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
		test.createSubAlbum("root", "anivå1");
		test.createSubAlbum("root", "bnivå1");
		test.createSubAlbum("anivå1", "anivå2");
		Set<Album> output = test.getLinkedAlbumList().get(0).getContainsSubAlbum();
		String outStr = "";
		for(Album a: output) {
			outStr += a.getName()+" ";
		}
		assertEquals("bnivå1 anivå1 ",outStr);
		output = test.getLinkedAlbumList().get(1).getContainsSubAlbum();
		outStr = "";
		for(Album a: output) {
			outStr += a.getName()+" ";
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
		Set<Album> output = test.getLinkedAlbumList().get(0).getContainsSubAlbum();
		String outStr = "";
		for(Album a: output) {
			outStr += a.getName()+" ";
		}
		assertEquals("anivå1 ",outStr);
		
	}

}
