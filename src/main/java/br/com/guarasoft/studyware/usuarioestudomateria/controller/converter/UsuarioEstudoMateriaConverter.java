package br.com.guarasoft.studyware.usuarioestudomateria.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;

@Named("estudomateriaconverter")
public class UsuarioEstudoMateriaConverter implements Converter {

	private final Logger logger = LoggerFactory.getLogger(UsuarioEstudoMateriaConverter.class);

	@Inject
	private UsuarioEstudoGateway usuarioEstudoGateway;

	@Inject
	private MateriaGateway materiaGateway;

	@Inject
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value == null) || "".equals(value)) {
			return value;
		}

		String[] values = value.split("-");

		UsuarioEstudoMateriaBean bean = null;
		if ("null".equals(values[0])) {
			UsuarioEstudoBean usuarioEstudo = this.usuarioEstudoGateway.buscaPorId(Long.parseLong(values[1]));

			MateriaBean materia = this.materiaGateway.buscaPorId(Long.parseLong(values[2]));

			bean = new UsuarioEstudoMateriaBean();
			bean.setUsuarioEstudo(usuarioEstudo);
			bean.setMateria(materia);
		} else {
			Long usuarioEstudoMateriaId = Long.parseLong(values[0]);

			bean = this.usuarioEstudoMateriaGateway.buscaPorId(usuarioEstudoMateriaId);
		}

		this.logger.info(bean.toString());

		return bean;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ("".equals(value)) {
			return "";
		}

		UsuarioEstudoMateriaBean bean = (UsuarioEstudoMateriaBean) value;

		String valor = bean.getId() + "-" + bean.getUsuarioEstudo().getId() + "-" + bean.getMateria().getId();

		this.logger.info(valor);

		return valor;
	}

}
