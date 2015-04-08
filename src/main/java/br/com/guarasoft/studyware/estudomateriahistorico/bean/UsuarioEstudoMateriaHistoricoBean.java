package br.com.guarasoft.studyware.estudomateriahistorico.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

@Data
public class UsuarioEstudoMateriaHistoricoBean {

	private Long id;
	private EstudoMateria usuarioEstudoMateria;
	private Date horaEstudo;
	private Duration tempoEstudado;
	private String observacao;

}
