package br.com.guarasoft.filesystem;

import java.io.File;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Test;

public class ListFilesTest {

	@Test
	public void test() {
		File file = new File(".");

		ListFiles listFiles = new ListFilesImpl();
		Collection<String> files = listFiles.listFilesForFolder(file);

		assertNotNull(files);
	}

}
