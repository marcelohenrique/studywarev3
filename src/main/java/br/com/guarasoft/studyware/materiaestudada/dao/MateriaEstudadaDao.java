package br.com.guarasoft.studyware.materiaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.materiaestudada.entidade.MateriaEstudada;

public interface MateriaEstudadaDao {
	public void persist( MateriaEstudada materiaEstudada );
	public List<MateriaEstudada> findAll(EstudoUsuarioBean estudo);
}
