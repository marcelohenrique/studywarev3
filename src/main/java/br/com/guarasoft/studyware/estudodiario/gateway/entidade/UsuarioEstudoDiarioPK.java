package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Embeddable
@Data
@ToString(exclude = { "usuarioEstudo" })
public class UsuarioEstudoDiarioPK implements Serializable {

	private static final long serialVersionUID = -2250995666721304321L;

	@JoinColumn(name = "usuarioEstudo", referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(name = "dia", referencedColumnName = "id")
	@ManyToOne
	private Dia dia;

}
