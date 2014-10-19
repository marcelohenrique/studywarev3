package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;
import java.util.List;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;

public interface EstudoUsuarioGateway {

	void cadastrar(String email, String nomeEstudo, Date fim);

	List<EstudoUsuarioBean> recuperaEstudos(String email);

}
