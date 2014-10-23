package br.com.guarasoft.studyware.estudousuario.gateway.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class EstudoUsuario {

	@Id
	private Long id;
	private String email;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date fim;

}
