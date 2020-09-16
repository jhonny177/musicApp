package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class MangaeFilesTests {
	
	ManageFiles test;
	ManageAlbum alb;
	@Test
	void creatFiletest() throws IOException {
		test = new ManageFiles();
		alb = new ManageAlbum();
		File file = new File("../../te.txt");
		SoundClip s = test.createFile(file);
		test.addFileToSub(alb.getRootAlbum(), s);
		
//		file.createNewFile();
//		test.createFile(file);
//		assertEquals("hello",test.root.getlistOfFiles().getFirst().toString());
	}

}
