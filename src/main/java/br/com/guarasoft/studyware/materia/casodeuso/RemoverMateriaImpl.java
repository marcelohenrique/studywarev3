package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

public class RemoverMateriaImpl implements RemoverMateria {

	private final MateriaGateway materiaGateway;

	public RemoverMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(MateriaBean materiaBean) {
		this.materiaGateway.remover(materiaBean);
	}

}
