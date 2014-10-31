package br.com.guarasoft.studyware.orgao.persistence;

import br.com.guarasoft.studyware.infra.dao.Entidade;

public interface OrgaoRepository extends Entidade {
	public Orgao find();
}
