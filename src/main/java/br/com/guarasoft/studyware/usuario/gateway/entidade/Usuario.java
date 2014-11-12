package br.com.guarasoft.studyware.usuario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity
@Data
public class Usuario implements Entidade {

	private static final long serialVersionUID = 1363553678194698977L;

	@Id
	private String email;
	private Boolean ativo;

}
