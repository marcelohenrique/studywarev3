package br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class UsuarioEstudoMateriaHistoricoBean {

	private Long id;
	private UsuarioEstudoMateriaBean usuarioEstudoMateria;
	private Date horaEstudo;
	private Duration tempoEstudado;
	private String observacao;

}
