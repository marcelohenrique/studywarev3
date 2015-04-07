package br.com.guarasoft.studyware.estudo.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.gateway.entidade.UsuarioEstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.gateway.converter.UsuarioEstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
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
			bean.setMaterias(this.usuarioEstudoMateriaBuilder.convert(bean,
					entidade.getMaterias()));
		}

		if (this.converteDias) {
			bean.setDias(this.estudoDiarioConverter.convert(bean,
					entidade.getEstudoDiarios()));
		}

		return bean;
	}

	public EstudoEntidade convert(Estudo bean) {
		EstudoEntidade entidade = this.estudoEntidadeConverter.convert(bean);

		for (Usuario usuario : bean.getUsuarios()) {
			UsuarioEstudoEntidade usuarioEstudoEntidade = new UsuarioEstudoEntidade();
			usuarioEstudoEntidade.setUsuario(usuarioEntidadeConverter
					.convert(usuario));
			entidade.getParticipantes().add(usuarioEstudoEntidade);
		}

		if (this.converteMaterias) {
			entidade.setMaterias(this.usuarioEstudoMateriaBuilder.convert(
					entidade, bean.getMaterias()));
		}

		if (this.converteDias) {
			entidade.setEstudoDiarios(this.estudoDiarioConverter
					.convert(entidade, bean.getDias()));
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
