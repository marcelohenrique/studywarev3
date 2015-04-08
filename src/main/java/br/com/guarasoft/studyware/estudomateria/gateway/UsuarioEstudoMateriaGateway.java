package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

public interface UsuarioEstudoMateriaGateway {

	// public EstudoMateria find(Estudo usuarioEstudoBean,
	// MateriaBean materiaBean);

	public List<EstudoMateria> buscaPorUsuarioEstudo(String nomeEstudo, String emailUsuario);

	public EstudoMateria buscaPorId(Long id);

}
