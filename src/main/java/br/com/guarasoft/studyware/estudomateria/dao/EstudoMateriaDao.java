package br.com.guarasoft.studyware.estudomateria.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;
import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;

public interface EstudoMateriaDao {
	public EstudoMateria findById(Long id);
	public List<EstudoMateria> findAll(EstudoUsuarioBean estudo);
}
