package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;

public interface MateriaGateway {

	List<MateriaBean> buscaMaterias();

	void cadastrar(MateriaBean materiaBean);

	void alterar(MateriaBean materiaBean);

	void remover(MateriaBean materiaBean);

	List<MateriaBean> buscaMateriasRestantes(Estudo estudo);

	MateriaBean buscaPorId(Long id);

}
