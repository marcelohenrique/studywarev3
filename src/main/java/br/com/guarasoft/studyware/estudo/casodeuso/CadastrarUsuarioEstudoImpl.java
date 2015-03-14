package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.UsuarioEstudoGateway;

public class CadastrarUsuarioEstudoImpl implements CadastrarUsuarioEstudo {

	private final UsuarioEstudoGateway usuarioEstudoGateway;

	public CadastrarUsuarioEstudoImpl(UsuarioEstudoGateway usuarioEstudoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
	}

	@Override
	public void execute(UsuarioEstudoBean usuarioEstudoBean) {
		try {
			this.usuarioEstudoGateway.cadastrar(usuarioEstudoBean);
		} catch (Exception e) {
			throw new UsuarioEstudoJaExiste();
		}
	}
}
