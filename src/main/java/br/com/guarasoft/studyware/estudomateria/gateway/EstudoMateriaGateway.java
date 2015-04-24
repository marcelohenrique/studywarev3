package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

public interface EstudoMateriaGateway {

	// public EstudoMateria find(Estudo usuarioEstudoBean,
	// Materia materiaBean);

	public List<EstudoMateria> buscaEstudoMateria(Estudo estudo);

	public EstudoMateria buscaPorId(Long id);

}
