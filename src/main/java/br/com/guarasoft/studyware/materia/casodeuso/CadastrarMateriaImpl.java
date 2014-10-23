package br.com.guarasoft.studyware.materia.casodeuso;

import br.com.guarasoft.studyware.excecao.CampoObrigatorioNaoInformado;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

public class CadastrarMateriaImpl implements CadastrarMateria {

	private MateriaGateway materiaGateway;

	public CadastrarMateriaImpl(MateriaGateway materiaGateway) {
		this.materiaGateway = materiaGateway;
	}

	@Override
	public void execute(String sigla, String nome) {
		verificaCampo(sigla, "Sigla");
		verificaCampo(nome, "Nome");

		MateriaBean materia = new MateriaBean();
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
