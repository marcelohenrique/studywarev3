package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public interface MateriaGateway {

	List<Materia> buscaMaterias();

	void cadastrar(Materia materia);

	void alterar(Materia materia);

	void remover(Materia materia);

	List<Materia> buscaMateriasRestantes(Estudo estudo);

	Materia buscaPorId(Long id);

}
