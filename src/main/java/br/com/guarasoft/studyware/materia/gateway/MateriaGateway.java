package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;

public interface MateriaGateway {

	List<MateriaBean> buscaMaterias();

	void cadastrar(MateriaBean materiaBean);

	void alterar(MateriaBean materia);

}
