package br.com.guarasoft.studyware.usuarioestudo.bean;

import java.util.Date;

import lombok.Data;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class MateriaCicloBean {

	private UsuarioEstudoMateriaBean materia;
	private Date tempoAlocado;

}
