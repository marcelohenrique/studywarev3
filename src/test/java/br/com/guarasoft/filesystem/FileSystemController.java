package br.com.guarasoft.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Getter;
import java.io.Serializable;

@Named
@ViewScoped
public class FileSystemController implements Serializable {

	private static final long serialVersionUID = 352623247053676549L;

	@Getter
	private String folderName;

	@Getter
	private File currentFolder;

	@PostConstruct
	public void init() {
		setFolderName(".");
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
		this.currentFolder = new File(folderName);
	}

	public void setLocation(String folderName) {
		setFolderName(this.folderName + folderName);
	}

	public StreamedContent download(File file) throws FileNotFoundException {
		return new DefaultStreamedContent(new FileInputStream(file), null, file.getName());
	}

}
