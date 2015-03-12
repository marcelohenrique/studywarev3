package br.com.guarasoft.studyware.usuario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity
@Table(name="Usuario")
@Data
public class UsuarioEntity implements Entidade {

	private static final long serialVersionUID = 1363553678194698977L;

	@Id
	private String email;
	private Boolean ativo;

}
