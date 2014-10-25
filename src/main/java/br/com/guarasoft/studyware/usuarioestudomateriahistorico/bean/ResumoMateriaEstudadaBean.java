package br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class ResumoMateriaEstudadaBean {

	private UsuarioEstudoMateriaBean usuarioEstudoMateria;
	private Duration somaTempo;

}
