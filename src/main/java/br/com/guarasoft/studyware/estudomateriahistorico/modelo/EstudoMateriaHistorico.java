package br.com.guarasoft.studyware.estudomateriahistorico.modelo;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.materia.modelo.Materia;
import lombok.Data;

@Data
public class EstudoMateriaHistorico implements Serializable {

	private static final long serialVersionUID = -7563980892483912839L;

	private Long id;
	// private EstudoMateria estudoMateria;
	private Estudo estudo;
	private Materia materia;
	private Date horaEstudo;
	private Duration tempoEstudado;
	private String observacao;

}
