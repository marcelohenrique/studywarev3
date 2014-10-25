package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.List;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

public interface UsuarioEstudoMateriaGateway {
	public UsuarioEstudoMateriaBean find(UsuarioEstudoBean usuarioEstudoBean, MateriaBean materiaBean);
	public List<UsuarioEstudoMateriaBean> findAll(UsuarioEstudoBean estudo);
}
