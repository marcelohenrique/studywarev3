package br.com.guarasoft.studyware.usuarioestudo.gateway.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiario;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
@Data
public class UsuarioEstudo implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	private Long id;

	private String email;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@OneToMany(mappedBy = "usuarioEstudo", cascade = { CascadeType.MERGE })
	private List<UsuarioEstudoMateria> materias;

	@OneToMany(mappedBy = "pk.usuarioEstudo")
	private List<UsuarioEstudoDiario> usuarioEstudoDiarios;

}
