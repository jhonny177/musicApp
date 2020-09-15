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
		test.initilizeContainsSubAlbum();
		sub.initilizeContainsSubAlbum();
		subSub.initilizeContainsSubAlbum();
		sub.getContainsSubAlbum().add(subSub);
		test.getContainsSubAlbum().add(sub);
		//testar outoutten av subAlbum()
		Set<Album> output = test.subAlbum();
		String outStr = "";
		for(Album a: output) {
			outStr += a.getName();
		}
		//gör settet som det skall se ut
		Set<Album> isNow = new HashSet<Album>();
		isNow.add(sub);
		isNow.add(subSub);
		String now = "";
		for(Album b:isNow) {
			now += b.getName();
		}
		assertEquals(now,outStr);
	}

}
