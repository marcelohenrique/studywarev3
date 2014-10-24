package br.com.guarasoft.studyware.resumomateriaestudada.gateway;

import java.util.List;

import br.com.guarasoft.studyware.resumomateriaestudada.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface ResumoMateriaEstudadaDao {
	public List<ResumoMateriaEstudadaBean> findAll(UsuarioEstudoBean estudoSelecionado);
}
