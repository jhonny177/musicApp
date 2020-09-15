import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MangaeAlbumTests {

	ManageAlbum test;
	
	@Test
	void testCreateRootAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("Ölsånger");
		String output = test.getLinkedAlbumList().get(0).getName();
		assertEquals("Ölsånger",output);
	}
	@Test
	void testCreateSubAlbum() {
		test = new ManageAlbum();
		test.createRootAlbum("root");
	}

}
