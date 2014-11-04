package br.com.guarasoft.studyware.usuarioestudomateria.bean;

import lombok.Data;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
@ToString(exclude = { "usuarioEstudoBean" })
public class UsuarioEstudoMateriaBean {

	private Long id;
	private UsuarioEstudoBean usuarioEstudoBean;
	private MateriaBean materiaBean;
	private Duration tempoAlocado;
	private Long ordem;
}
