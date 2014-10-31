package br.com.guarasoft.studyware.usuarioestudo.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
@ToString(exclude = { "materias" })
public class UsuarioEstudoBean {

	private Long id;
	private String email;
	private String nome;
	private Date fim;
	private List<UsuarioEstudoMateriaBean> materias;

}
