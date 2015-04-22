package br.com.guarasoft.studyware.estudomateriahistorico.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

@Data
public class UsuarioEstudoMateriaHistoricoBean implements Serializable {

	private static final long serialVersionUID = -7563980892483912839L;

	private Long id;
	private EstudoMateria estudoMateria;
	private Date horaEstudo;
	private Duration tempoEstudado;
	private String observacao;

}
