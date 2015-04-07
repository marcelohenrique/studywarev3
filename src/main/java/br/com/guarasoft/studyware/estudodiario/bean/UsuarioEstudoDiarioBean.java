package br.com.guarasoft.studyware.estudodiario.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;

@Data
@ToString(exclude = { "usuarioEstudo" })
@EqualsAndHashCode(of = { "usuarioEstudo", "dia" })
public class UsuarioEstudoDiarioBean {

	private Long id;
	private Estudo usuarioEstudo;
	private DiaBean dia;
	private Duration tempoAlocado;

}
