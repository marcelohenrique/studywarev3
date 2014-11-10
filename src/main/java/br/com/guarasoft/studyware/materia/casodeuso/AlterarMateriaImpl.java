package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

public class AlterarMateriaImpl implements AlterarMateria {

	private final MateriaGateway materiaGateway;

	public AlterarMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(MateriaBean materiaAlterada) {
		this.materiaGateway.alterar(materiaAlterada);
	}

}
