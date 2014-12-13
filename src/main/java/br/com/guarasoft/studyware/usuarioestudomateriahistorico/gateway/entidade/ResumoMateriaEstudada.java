package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Data
@AllArgsConstructor
public class ResumoMateriaEstudada {

	private UsuarioEstudoMateria usuarioEstudoMateria;
	private Long somaTempo;
	private String observacao;

}
