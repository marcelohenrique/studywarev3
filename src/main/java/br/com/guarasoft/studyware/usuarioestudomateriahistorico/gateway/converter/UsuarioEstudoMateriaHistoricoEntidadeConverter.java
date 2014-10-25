package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.converter;

import javax.inject.Inject;

import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;

	public UsuarioEstudoMateriaHistorico convert(
			UsuarioEstudoMateriaHistoricoBean bean) {
		UsuarioEstudoMateriaHistorico entidade = new UsuarioEstudoMateriaHistorico();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter
				.convert(bean.getUsuarioEstudoMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

}
