package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface EstudoSemanalGateway {
	public List<EstudoSemanalBean> findAll(UsuarioEstudoBean estudo);
}
