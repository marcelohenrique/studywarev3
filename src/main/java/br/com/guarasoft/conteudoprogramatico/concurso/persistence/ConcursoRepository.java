package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import java.util.List;

import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;

public interface ConcursoRepository extends Entidade {

	public Concurso findById(Long id);
	public List<Concurso> findAll();

}