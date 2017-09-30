package br.com.guarasoft.studyware.infra.dao;

import javax.persistence.Id;

//@Entity
public class EntidadeAbstrata implements Entidade {

	private static final long serialVersionUID = -5415749934704028368L;

	@Id
	protected Long id;

}
