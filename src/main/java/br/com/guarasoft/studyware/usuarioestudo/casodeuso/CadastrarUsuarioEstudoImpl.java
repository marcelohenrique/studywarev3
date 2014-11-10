package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

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
