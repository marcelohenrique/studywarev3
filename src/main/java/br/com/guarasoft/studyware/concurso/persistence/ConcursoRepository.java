package br.com.guarasoft.studyware.concurso.persistence;

import java.util.List;

import br.com.guarasoft.studyware.concurso.entidade.Concurso;
import br.com.guarasoft.studyware.infra.dao.Entidade;

public interface ConcursoRepository extends Entidade {

	public Concurso findById(Long id);
	public List<Concurso> findAll();

}