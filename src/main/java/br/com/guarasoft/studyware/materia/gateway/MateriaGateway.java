package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface MateriaGateway {

	List<MateriaBean> buscaMaterias();

	void cadastrar(MateriaBean materiaBean);

	void alterar(MateriaBean materiaBean);

	void remover(MateriaBean materiaBean);

	List<MateriaBean> buscaMateriasRestantes(UsuarioEstudoBean usuarioEstudoBean);

}
