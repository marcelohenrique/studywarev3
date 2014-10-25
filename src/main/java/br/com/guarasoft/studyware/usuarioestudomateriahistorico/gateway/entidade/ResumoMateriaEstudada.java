package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

//@Entity
//@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResumoMateriaEstudada/* implements Entidade */{

	// private static final long serialVersionUID = 312482194973348722L;

	// @Id
	// @OneToOne
	// @JoinColumn(name = "ID")
	private UsuarioEstudoMateria usuarioEstudoMateria;

	// @Column(name = "SOMA_TEMPO")
	private Long somaTempo;

	public ResumoMateriaEstudada(UsuarioEstudoMateria usuarioEstudoMateria,
			Long somaTempo) {
		super();
		this.usuarioEstudoMateria = usuarioEstudoMateria;
		this.somaTempo = somaTempo;
	}

	// public Duration getSomaTempo() {
	// if (somaTempoLong == null) {
	// return new Duration(0);
	// }
	// return new Duration(somaTempoLong);
	// }

}
