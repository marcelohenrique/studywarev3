package br.com.guarasoft.studyware.estudo.gateway.entidade;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioEntidade;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity(name = "Estudo")
@Table(name = "Estudo")
@Data
@EqualsAndHashCode(of = { "id" })
public class EstudoEntidade implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EstudoSeq")
	@SequenceGenerator(name = "EstudoSeq", sequenceName = "EstudoSeq", allocationSize = 1)
	private Long id;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@OneToMany(mappedBy = "estudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Collection<UsuarioEstudoEntidade> participantes;

	@OneToMany(mappedBy = "estudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("ordem")
	private Set<EstudoMateriaEntidade> materias;

	@OneToMany(mappedBy = "estudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<EstudoDiarioEntidade> estudoDiarios;

}
