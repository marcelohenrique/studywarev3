package br.com.guarasoft.studyware.estudodiario.bean;

import lombok.Data;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
@ToString(exclude = { "usuarioEstudo" })
public class UsuarioEstudoDiarioBean {

	private Long id;
	private UsuarioEstudoBean usuarioEstudo;
	private DiaBean dia;
	private Duration tempoAlocado;

}
