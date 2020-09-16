package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class ManageFilesTests {
	
	ManageFiles test;
	ManageAlbum alb;
	@Test
	void creatFiletest() throws IOException {
		test = new ManageFiles();
		alb = new ManageAlbum();
		File file = new File("hello");
		alb.createRootAlbum("root");
		SoundClip s = test.createFile(file);
		test.addFileToSub(alb.getRootAlbum(), s);
		assertEquals("hello",alb.getRootAlbum().getListOfFiles().get(0).toString());
	}
	@Test
	void deleteFiletest() {
		test = new ManageFiles();
		alb = new ManageAlbum();
		File file = new File("lalasång");
		alb.createRootAlbum("root");
		SoundClip s = test.createFile(file);
		test.addFileToSub(alb.getRootAlbum(), s);
		test.deleteFile(alb.getRootAlbum(), s);
		assertEquals(0,alb.getRootAlbum().getListOfFiles().size());
	}
	
}
