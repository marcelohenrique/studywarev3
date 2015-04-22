package br.com.guarasoft.studyware.estudo.gateway.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.gateway.entidade.UsuarioEstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.gateway.converter.UsuarioEstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioEntidade;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.usuario.gateway.converter.UsuarioEntidadeConverter;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class EstudoBuilder {

	private final EstudoEntidadeConverter estudoEntidadeConverter = new EstudoEntidadeConverter();
	private final UsuarioEntidadeConverter usuarioEntidadeConverter = new UsuarioEntidadeConverter();
	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();
	private final UsuarioEstudoDiarioEntidadeConverter estudoDiarioConverter = new UsuarioEstudoDiarioEntidadeConverter();

	private boolean converteMaterias;
	private boolean converteDias;

	public EstudoBuilder converteMaterias() {
		this.converteMaterias = true;
		return this;
	}

	public EstudoBuilder converteDias() {
		this.converteDias = true;
		return this;
	}

	public Estudo convert(EstudoEntidade entidade) {
		Estudo bean = this.estudoEntidadeConverter.convert(entidade);

		for (UsuarioEstudoEntidade participantes : entidade.getParticipantes()) {
			bean.add(this.usuarioEntidadeConverter.convert(participantes
					.getUsuario()));
		}

		if (this.converteMaterias) {
			for (EstudoMateriaEntidade estudoMateriaEntidade : entidade
					.getMaterias()) {
				bean.add(this.usuarioEstudoMateriaBuilder
						.convert(estudoMateriaEntidade));
			}
		}

		if (this.converteDias) {
			for (EstudoDiarioEntidade estudoDiarioEntidade : entidade
					.getEstudoDiarios()) {
				bean.add(this.estudoDiarioConverter.convert(bean,
						estudoDiarioEntidade));
			}
		}

		return bean;
	}

	public EstudoEntidade convert(Estudo bean) {
		EstudoEntidade entidade = this.estudoEntidadeConverter.convert(bean);

		Collection<UsuarioEstudoEntidade> participantes = new LinkedHashSet<>();
		for (Usuario usuario : bean.getParticipantes()) {
			UsuarioEstudoEntidade usuarioEstudoEntidade = new UsuarioEstudoEntidade();
			usuarioEstudoEntidade.setUsuario(this.usuarioEntidadeConverter
					.convert(usuario));
			usuarioEstudoEntidade.setEstudo(entidade);
			participantes.add(usuarioEstudoEntidade);
		}
		entidade.setParticipantes(participantes);

		if (this.converteMaterias) {
			entidade.setMaterias(this.usuarioEstudoMateriaBuilder.convert(
					entidade, bean.getMaterias()));
		}

		if (this.converteDias) {
			entidade.setEstudoDiarios(this.estudoDiarioConverter.convert(
					entidade, bean.getDias()));
		}

		return entidade;
	}

	public List<Estudo> convert(List<EstudoEntidade> entidades) {
		List<Estudo> beans = new ArrayList<>();

		Estudo bean = null;
		for (EstudoEntidade estudo : entidades) {
			bean = this.convert(estudo);

			beans.add(bean);
		}

		return beans;
	}

}
