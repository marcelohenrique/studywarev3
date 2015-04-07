package br.com.guarasoft.studyware.estudomateriahistorico.bean;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class ResumoMateriaEstudadaBean {

	private UsuarioEstudoMateriaBean usuarioEstudoMateria;
	private Duration somaTempo;
	private String observacao;

}
