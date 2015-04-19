package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public class RemoverMateriaImpl implements RemoverMateria {

	private final MateriaGateway materiaGateway;

	public RemoverMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(Materia materia) {
		this.materiaGateway.remover(materia);
	}

}
