package br.com.guarasoft.studyware.estudo.casodeuso;

import java.util.Collection;

import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class ConsultaEstudoImpl implements ConsultaEstudo {

	private EstudoGateway estudoGateway;

	public ConsultaEstudoImpl(EstudoGateway estudoGateway) {
		this.estudoGateway = estudoGateway;
	}

	@Override
	public Collection<Estudo> consulta(Usuario usuario) {
		Collection<Estudo> estudos = estudoGateway.recuperaTodosEstudos(usuario
				.getEmail());
		return estudos;
	}

}
