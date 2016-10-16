package br.com.guarasoft.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class ListFilesImpl implements ListFiles {

	@Override
	public Collection<String> listFilesForFolder(File folder) {
		Collection<String> files = new ArrayList<>();
		for (File file : folder.listFiles()) {
			String filename = file.getName();
			files.add(filename);
			System.out.println(filename);
		}
		return files;
	}

}
