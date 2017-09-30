package br.com.guarasoft.studyware.infra.faces;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil implements Serializable {

	private static final long serialVersionUID = -7221403577307364568L;

	public void addInfo(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(titulo, mensagem));
	}

}
