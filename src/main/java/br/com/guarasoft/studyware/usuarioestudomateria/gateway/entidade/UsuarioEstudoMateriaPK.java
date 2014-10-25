package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Embeddable
@Data
public class UsuarioEstudoMateriaPK implements Serializable {

	private static final long serialVersionUID = 9133924471471666635L;

	@JoinColumn(referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(referencedColumnName = "id")
	@OneToOne
	private Materia materia;

}