package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;

public interface UsuarioEstudoMateriaHistoricoGateway {
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada);

	public List<UsuarioEstudoMateriaHistoricoBean> findAll(UsuarioEstudoBean estudo);

	List<ResumoMateriaEstudadaBean> buscaResumosMaterias(UsuarioEstudoBean usuarioEstudoBean);
}
