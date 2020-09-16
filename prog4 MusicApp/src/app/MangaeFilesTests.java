package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class MangaeFilesTests {
	
	ManageFiles test;
	@Test
	void creatFiletest() {
		test.createFile(new File("hello"));
	}

}
