package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoMateria;

public interface EstudoMateriaHistoricoGateway {
	public void persist(EstudoMateriaHistorico materiaEstudada);

	public List<EstudoMateriaHistorico> findAll(Estudo estudo);

	List<ResumoMateria> buscaResumosMaterias(Estudo estudo);

	void merge(EstudoMateriaHistorico bean);

	void remove(EstudoMateriaHistorico estudoMateriaHistorico);
}
