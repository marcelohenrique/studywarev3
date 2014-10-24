package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;

public interface MateriaEstudadaDao {
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada);

	public List<UsuarioEstudoMateriaHistoricoBean> findAll(
			UsuarioEstudoBean estudo);
}
