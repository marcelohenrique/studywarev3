package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Embeddable
@Data
public class UsuarioEstudoMateriaPK {

	@JoinColumn(referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(referencedColumnName = "id")
	@OneToOne
	private Materia materia;

}