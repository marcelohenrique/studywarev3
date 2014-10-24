package br.com.guarasoft.studyware.usuarioestudo.gateway;

import java.util.Date;
import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface UsuarioEstudoGateway {

	void cadastrar(String email, String nomeEstudo, Date fim);

	List<UsuarioEstudoBean> recuperaEstudos(String email);

}
