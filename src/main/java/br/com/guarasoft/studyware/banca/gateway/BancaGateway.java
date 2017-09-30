package br.com.guarasoft.studyware.banca.gateway;

import java.io.Serializable;
import java.util.Collection;

import br.com.guarasoft.studyware.banca.modelo.Banca;

public interface BancaGateway extends Serializable {

	public Collection<Banca> consulta();

	public void cadastra(Banca banca);

}
