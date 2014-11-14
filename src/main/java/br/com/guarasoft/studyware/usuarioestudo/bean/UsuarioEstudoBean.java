package br.com.guarasoft.studyware.usuarioestudo.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class UsuarioEstudoBean {

	private Long id;
	private UsuarioBean usuario;
	private String nome;
	private Date fim;
	private List<UsuarioEstudoMateriaBean> materias;
	private List<UsuarioEstudoDiarioBean> dias;

}
