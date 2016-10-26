package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoMateria;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public interface EstudoMateriaHistoricoGateway {
	public void persist(EstudoMateriaHistorico materiaEstudada);

	public void merge(EstudoMateriaHistorico bean);

	public void remove(EstudoMateriaHistorico estudoMateriaHistorico);

	public List<EstudoMateriaHistorico> findAll(Estudo estudo);

	public List<EstudoMateriaHistorico> findAll(Materia materia);

	public List<EstudoMateriaHistorico> findAll(Estudo estudo, Materia materia);

	public List<ResumoMateria> buscaResumosMaterias(Estudo estudo);
}
