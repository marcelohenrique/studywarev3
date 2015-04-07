package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;

public interface UsuarioEstudoMateriaGateway {

	// public UsuarioEstudoMateriaBean find(Estudo usuarioEstudoBean,
	// MateriaBean materiaBean);

	public List<UsuarioEstudoMateriaBean> buscaPorUsuarioEstudo(String nomeEstudo, String emailUsuario);

	public UsuarioEstudoMateriaBean buscaPorId(Long id);

}
