package br.com.guarasoft.studyware.usuarioestudo.gateway.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class UsuarioEstudo {

	@Id
	private Long id;
	private String email;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date fim;

}
