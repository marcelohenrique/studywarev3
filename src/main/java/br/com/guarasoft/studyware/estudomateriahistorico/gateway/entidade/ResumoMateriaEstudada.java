package br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;

@Data
@AllArgsConstructor
public class ResumoMateriaEstudada {

	private EstudoMateriaEntidade estudoMateria;
	private Long somaTempo;
	private String observacao;

}
