package br.com.guarasoft.studyware.usuarioestudomateria.bean;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
public class UsuarioEstudoMateriaBean {

	private UsuarioEstudoBean usuarioEstudoBean;
	private MateriaBean materiaBean;
	private Duration tempoAlocado;
	private Long ordem;
}
