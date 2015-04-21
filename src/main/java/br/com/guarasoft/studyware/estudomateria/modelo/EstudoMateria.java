package br.com.guarasoft.studyware.estudomateria.modelo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.materia.modelo.Materia;

@Data
@EqualsAndHashCode(exclude = { "estudo" })
@ToString(exclude = { "estudo" })
public class EstudoMateria implements Serializable {

	private static final long serialVersionUID = -3093337347641486259L;

	private Long id;
	private Estudo estudo;
	private Materia materia;
	private Duration tempoAlocado;
	private Long ordem;

}
