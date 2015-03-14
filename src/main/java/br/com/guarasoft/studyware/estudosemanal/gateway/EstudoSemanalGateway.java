package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;

public interface EstudoSemanalGateway {
	public List<EstudoSemanalBean> findAll(UsuarioEstudoBean estudo);
}
