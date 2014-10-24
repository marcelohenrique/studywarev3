package br.com.guarasoft.studyware.usuarioestudo.bean;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioEstudoBean {

	private Long id;
	private String email;
	private String nome;
	private Date fim;

}
