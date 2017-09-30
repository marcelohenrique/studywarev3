package br.com.guarasoft.studyware.banca.gateway.converter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;

import br.com.guarasoft.studyware.banca.gateway.entidade.BancaEntidade;
import br.com.guarasoft.studyware.banca.modelo.Banca;

public class BancaBuilder {

	public Banca convert(BancaEntidade entidade) {
		Banca bean = new Banca();
		try {
			BeanUtils.copyProperties(bean, entidade);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return bean;
	}

	public BancaEntidade convert(Banca banca) {
		BancaEntidade entidade = new BancaEntidade();
		try {
			BeanUtils.copyProperties(entidade, banca);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return entidade;
	}

	public Collection<Banca> convert(Collection<BancaEntidade> entidades) {
		Collection<Banca> bancas = new ArrayList<>();
		for (BancaEntidade entidade : entidades) {
			bancas.add(convert(entidade));
		}
		return bancas;
	}

}
