package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;

public interface EstudoSemanalGateway {
	public List<EstudoSemanalBean> findAll(Estudo estudo);
}
