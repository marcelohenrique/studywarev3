package br.com.guarasoft.studyware.resumomateriaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;

public interface ResumoMateriaEstudadaDao {
	public List<ResumoMateriaEstudada> findAll(EstudoUsuarioBean estudoSelecionado);
}
