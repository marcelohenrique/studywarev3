package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;

public interface EstudoDiaGateway {
	public List<EstudoSemanal> findAll(String nomeEstudo, String email);
}
