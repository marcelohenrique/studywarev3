package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.excecao.CampoObrigatorioNaoInformado;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;

public class CadastrarMateriaImpl implements CadastrarMateria {

	private final MateriaGateway materiaGateway;

	public CadastrarMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(String sigla, String nome) {
		this.verificaCampo(sigla, "Sigla");
		this.verificaCampo(nome, "Nome");

		Materia materia = new Materia();
		materia.setSigla(sigla);
		materia.setNome(nome);
		this.materiaGateway.cadastrar(materia);
	}

	private void verificaCampo(String campo, String nomeCampo) {
		if (campo == null || "".equals(campo)) {
			throw new CampoObrigatorioNaoInformado(nomeCampo);
		}
	}
}
