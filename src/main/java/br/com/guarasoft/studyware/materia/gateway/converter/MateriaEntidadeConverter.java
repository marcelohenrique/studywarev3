package br.com.guarasoft.studyware.materia.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.materia.gateway.entidade.MateriaEntidade;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public class MateriaEntidadeConverter {

	public Materia convert(MateriaEntidade materiaEntidade) {
		if(materiaEntidade == null) {
			return null;
		}
		Materia materia = new Materia();

		materia.setId(materiaEntidade.getId());
		materia.setSigla(materiaEntidade.getSigla());
		materia.setNome(materiaEntidade.getNome());

		return materia;
	}

	public MateriaEntidade convert(Materia materia) {
		if(materia == null) {
			return null;
		}
		MateriaEntidade materiaEntidade = new MateriaEntidade();

		materiaEntidade.setId(materia.getId());
		materiaEntidade.setSigla(materia.getSigla());
		materiaEntidade.setNome(materia.getNome());

		return materiaEntidade;
	}

	public List<Materia> convert(List<MateriaEntidade> materiaEntidades) {
		List<Materia> materiasBean = new ArrayList<>();

		for (MateriaEntidade materiaEntidade : materiaEntidades) {
			materiasBean.add(this.convert(materiaEntidade));
		}

		return materiasBean;
	}

}
