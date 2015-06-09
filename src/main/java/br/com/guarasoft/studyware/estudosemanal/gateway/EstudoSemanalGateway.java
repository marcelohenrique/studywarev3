package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.bean.IntervaloEstudo;

public interface EstudoSemanalGateway {
	List<EstudoSemanalBean> findAll(Estudo estudo, IntervaloEstudo intervalo);
}
