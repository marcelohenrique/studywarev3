package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface EstudoDiaGateway {
	public List<EstudoDiarioBean> findAll(UsuarioEstudoBean estudo);
}
