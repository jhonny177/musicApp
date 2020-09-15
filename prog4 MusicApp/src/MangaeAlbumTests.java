import static org.junit.jupiter.api.Assertions.*;

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
	}

}
