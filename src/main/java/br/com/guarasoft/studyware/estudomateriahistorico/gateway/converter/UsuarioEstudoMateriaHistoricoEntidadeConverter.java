package br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.gateway.converter.EstudoEntidadeConverter;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.EstudoMateriaHistoricoEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	private final EstudoEntidadeConverter estudoEntidadeConverter = new EstudoEntidadeConverter();
	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();

	public EstudoMateriaHistoricoEntidade convert(EstudoMateriaHistorico bean) {
		EstudoMateriaHistoricoEntidade entidade = new EstudoMateriaHistoricoEntidade();

		entidade.setId(bean.getId());
		entidade.setEstudo(estudoEntidadeConverter.convert(bean.getEstudo()));
		entidade.setMateria(materiaEntidadeConverter.convert(bean.getMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

	public EstudoMateriaHistorico convert(Estudo beanPai,
			EstudoMateriaHistoricoEntidade entidade) {
		EstudoMateriaHistorico bean = new EstudoMateriaHistorico();

		bean.setId(entidade.getId());
		bean.setEstudo(beanPai);
		bean.setMateria(materiaEntidadeConverter.convert(entidade.getMateria()));
		bean.setHoraEstudo(entidade.getHoraEstudo());
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));
		bean.setObservacao(entidade.getObservacao());

		return bean;
	}

	public List<EstudoMateriaHistorico> convert(Estudo beanPai,
			List<EstudoMateriaHistoricoEntidade> entidades) {
		List<EstudoMateriaHistorico> beans = new ArrayList<>();

		for (EstudoMateriaHistoricoEntidade entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

}
