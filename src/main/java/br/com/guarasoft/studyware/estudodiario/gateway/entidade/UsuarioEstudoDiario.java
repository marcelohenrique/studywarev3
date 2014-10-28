package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class UsuarioEstudoDiario {
	@EmbeddedId
	private UsuarioEstudoDiarioPK pk;
	private Long tempoAlocado;
}
