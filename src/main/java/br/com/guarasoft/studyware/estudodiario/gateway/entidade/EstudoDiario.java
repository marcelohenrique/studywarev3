package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.Dia;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Data
@AllArgsConstructor
public class EstudoDiario implements Entidade {

	private static final long serialVersionUID = 2509970879445306489L;

	private Date inicioSemana;
	private Dia diaSemana;
	private Long tempoAlocado;
	private Long tempoEstudado;

}
