package br.com.guarasoft.studyware.banca.casodeuso;

import java.util.Collection;

import br.com.guarasoft.studyware.banca.modelo.Banca;

public interface ConsultaBanca {
	public Collection<Banca> consulta();
}
