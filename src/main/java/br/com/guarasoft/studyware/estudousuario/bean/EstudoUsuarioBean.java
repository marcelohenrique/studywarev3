package br.com.guarasoft.studyware.estudousuario.bean;

import java.util.Date;

import lombok.Data;

@Data
public class EstudoUsuarioBean {

	private Long id;
	private String email;
	private String nome;
	private Date fim;

}
