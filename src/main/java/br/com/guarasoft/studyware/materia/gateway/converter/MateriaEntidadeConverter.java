package br.com.guarasoft.studyware.materia.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

public class MateriaEntidadeConverter {

	public MateriaBean convert(Materia materia) {
		MateriaBean materiaBean = new MateriaBean();

		materiaBean.setId(materia.getId());
		materiaBean.setSigla(materia.getSigla());
		materiaBean.setNome(materia.getNome());

		return materiaBean;
	}

	public Materia convert(MateriaBean materiaBean) {
		Materia materia = new Materia();

		materia.setId(materiaBean.getId());
		materia.setSigla(materiaBean.getSigla());
		materia.setNome(materiaBean.getNome());

		return materia;
	}

	public List<MateriaBean> convert(List<Materia> materias) {
		List<MateriaBean> materiasBean = new ArrayList<>();

		for (Materia materia : materias) {
			materiasBean.add(convert(materia));
		}

		return materiasBean;
	}

}
