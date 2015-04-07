package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;

public interface EstudoDiaGateway {
	public List<EstudoDiarioBean> findAll(String nomeEstudo, String email);
}
