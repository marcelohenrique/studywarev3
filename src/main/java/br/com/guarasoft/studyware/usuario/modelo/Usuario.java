package br.com.guarasoft.studyware.usuario.modelo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "email" })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2911216730469825660L;

	private String email;
	private boolean ativo;

}
