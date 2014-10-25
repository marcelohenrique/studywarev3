package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResumoMateriaEstudada implements Entidade {

	private static final long serialVersionUID = 312482194973348722L;

	@Id
	@OneToOne
	@JoinColumn(name = "ID")
	private UsuarioEstudoMateria usuarioEstudoMateria;

	@Column(name = "SOMA_TEMPO")
	private Long somaTempoLong;

	public Duration getSomaTempo() {
		if (somaTempoLong == null) {
			return new Duration(0);
		}
		return new Duration(somaTempoLong);
	}

}
