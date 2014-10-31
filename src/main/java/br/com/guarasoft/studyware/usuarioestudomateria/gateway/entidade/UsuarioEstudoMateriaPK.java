package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Embeddable
@Data
public class UsuarioEstudoMateriaPK implements Serializable {

	private static final long serialVersionUID = 9133924471471666635L;

	@JoinColumn(name = "usuarioEstudo", referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(name = "materia", referencedColumnName = "id")
	@ManyToOne
	private Materia materia;

}