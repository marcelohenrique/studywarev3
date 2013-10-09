package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import java.util.List;

import br.com.guarasoft.concursos.infra.dao.Entidade;

public interface ConcursoRepository extends Entidade {

	public List<Concurso> findAll();

}