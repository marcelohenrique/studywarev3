package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public class AlterarMateriaImpl implements AlterarMateria {

	private final MateriaGateway materiaGateway;

	public AlterarMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(Materia materiaAlterada) {
		this.materiaGateway.alterar(materiaAlterada);
	}

}
