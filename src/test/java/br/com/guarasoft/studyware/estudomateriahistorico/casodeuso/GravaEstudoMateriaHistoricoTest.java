package br.com.guarasoft.studyware.estudomateriahistorico.casodeuso;

import org.junit.Test;

import br.com.guarasoft.studyware.estudomateriahistorico.gateway.EstudoMateriaHistoricoGateway;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;

public class GravaEstudoMateriaHistoricoTest {

	private EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway;
	
	@Test
	public void testGravar() {
		EstudoMateriaHistorico historico = new EstudoMateriaHistorico();
		GravaEstudoMateriaHistorico gravaEstudoMateriaHistorico = new GravaEstudoMateriaHistoricoImpl(estudoMateriaHistoricoGateway);
		gravaEstudoMateriaHistorico.gravar(historico);
	}
}
