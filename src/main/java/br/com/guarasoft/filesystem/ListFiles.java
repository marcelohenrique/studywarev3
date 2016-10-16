package br.com.guarasoft.filesystem;

import java.io.File;
import java.util.Collection;

public interface ListFiles {

	Collection<String> listFilesForFolder(File folder);

}
