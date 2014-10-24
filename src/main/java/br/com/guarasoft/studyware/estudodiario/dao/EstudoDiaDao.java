package br.com.guarasoft.studyware.estudodiario.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudodiario.entidade.EstudoDiario;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface EstudoDiaDao {
	public List<EstudoDiario> findAll(UsuarioEstudoBean estudo);
}
