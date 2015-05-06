package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.casodeuso.GravaEstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.casodeuso.GravaEstudoMateriaHistoricoImpl;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.EstudoMateriaHistoricoGateway;

public class CadastraEstudo {

	private final EstudoGateway usuarioEstudoGateway;
	private final GravaEstudoMateriaHistorico gravaEstudoMateriaHistorico;

	public CadastraEstudo(EstudoGateway usuarioEstudoGateway, EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
		gravaEstudoMateriaHistorico = new GravaEstudoMateriaHistoricoImpl(estudoMateriaHistoricoGateway);
	}

	public void execute(Estudo estudo) {
		try {
			this.usuarioEstudoGateway.cadastrar(estudo);
		} catch (Exception e) {
			throw new UsuarioEstudoJaExiste();
		}
		gravaEstudoMateriaHistorico.ajusteHistorico(estudo);
	}

}
