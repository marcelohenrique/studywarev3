package br.com.guarasoft.studyware.estudo.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public interface EstudoGateway {

	void cadastrar(Estudo estudo);

	List<Estudo> recuperaTodosEstudos(String email);

	List<Estudo> recuperaEstudosValidos(String email);

	Estudo buscaPorId(Long id);

	void remover(Estudo estudo);

}
