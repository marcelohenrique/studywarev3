package br.com.guarasoft.studyware.estudosemanal.gateway.entidade;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Data
@AllArgsConstructor
public class EstudoSemanal implements Entidade {

	private static final long serialVersionUID = -6647557331665653590L;

	private Date inicioSemana;
	private Long tempoEstudado;

}
