package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity
@Data
public class UsuarioEstudoMateria implements Entidade {

	private static final long serialVersionUID = 6291112509470918058L;

	@EmbeddedId
	private UsuarioEstudoMateriaPK usuarioEstudoMateriaPK;

	@Column(name = "TEMPO_ALOCADO")
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private Long tempoAlocado;

	@Column(name = "NR_ORDEM")
	private Long ordem;

}
