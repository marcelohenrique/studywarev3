package br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.UsuarioEstudoMateria;

@Data
@AllArgsConstructor
public class ResumoMateriaEstudada {

	private UsuarioEstudoMateria usuarioEstudoMateria;
	private Long somaTempo;
	private String observacao;

}
