package br.com.guarasoft.studyware.estudosemanal.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudosemanal.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;

public interface EstudoSemanalDao {
	public List<EstudoSemanal> findAll(EstudoUsuarioBean estudo);
}
