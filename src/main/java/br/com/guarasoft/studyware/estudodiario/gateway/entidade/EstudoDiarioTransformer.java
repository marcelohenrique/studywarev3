package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Data
@AllArgsConstructor
public class EstudoDiarioTransformer implements Entidade {

	private static final long serialVersionUID = 2509970879445306489L;

	private Date inicioSemana;
	private Integer diaSemana;
	private Long tempoAlocado;
	private Long tempoEstudado;

}
