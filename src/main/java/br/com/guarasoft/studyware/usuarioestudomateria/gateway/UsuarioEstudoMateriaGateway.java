package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

public interface UsuarioEstudoMateriaGateway {
	public UsuarioEstudoMateriaBean findById(Long id);
	public List<UsuarioEstudoMateriaBean> findAll(UsuarioEstudoBean estudo);
}
