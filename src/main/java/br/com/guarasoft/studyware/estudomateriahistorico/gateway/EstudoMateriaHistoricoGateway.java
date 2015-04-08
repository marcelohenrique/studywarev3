package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;

public interface EstudoMateriaHistoricoGateway {
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada);

	public List<UsuarioEstudoMateriaHistoricoBean> findAll(Estudo estudo);

	List<ResumoMateriaEstudadaBean> buscaResumosMaterias(Estudo estudo);

	void merge(UsuarioEstudoMateriaHistoricoBean bean);
}
