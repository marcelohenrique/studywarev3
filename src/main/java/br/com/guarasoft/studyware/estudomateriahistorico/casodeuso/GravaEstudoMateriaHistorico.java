package br.com.guarasoft.studyware.estudomateriahistorico.casodeuso;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;

public interface GravaEstudoMateriaHistorico {

	public void gravar(EstudoMateriaHistorico historico);

	public void ajusteHistorico(Estudo estudo);

}
