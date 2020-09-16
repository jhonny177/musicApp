package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class MangaeFilesTests {
	
	ManageFiles test;
	@Test
	void creatFiletest() throws IOException {
		File file = new File("C:\\Users\\3jonn\\git\\musicApp\\prog4 MusicApp\\src\\te.txt");
		file.createNewFile();
		test.createFile(file);
		assertEquals("hello",test.root.getlistOfFiles().getFirst().toString());
	}

}
