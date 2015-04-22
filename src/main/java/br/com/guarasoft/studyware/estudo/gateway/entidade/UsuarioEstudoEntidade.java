package br.com.guarasoft.studyware.estudo.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntidade;

@Entity
@Table(name = "UsuarioEstudo")
@Data
@ToString(exclude = { "estudo" })
public class UsuarioEstudoEntidade implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoSeq")
	@SequenceGenerator(name = "UsuarioEstudoSeq", sequenceName = "UsuarioEstudoSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "usuario", referencedColumnName = "email")
	@ManyToOne
	private UsuarioEntidade usuario;

	@ManyToOne
	@JoinColumn(name = "estudo", referencedColumnName = "id")
	private EstudoEntidade estudo;

}
