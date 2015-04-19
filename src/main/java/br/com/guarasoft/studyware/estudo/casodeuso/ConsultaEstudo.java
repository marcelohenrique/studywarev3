package br.com.guarasoft.studyware.estudo.casodeuso;

import java.util.Collection;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public interface ConsultaEstudo {

	Collection<Estudo> consulta(Usuario usuario);

}
