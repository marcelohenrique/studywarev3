package br.com.guarasoft.studyware.usuario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	private String email;
	private Boolean ativo;

}
