package br.com.guarasoft.studyware.estudomateriahistorico.bean;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

@Data
public class ResumoMateriaEstudadaBean {

	private EstudoMateria usuarioEstudoMateria;
	private Duration somaTempo;
	private String observacao;

}
