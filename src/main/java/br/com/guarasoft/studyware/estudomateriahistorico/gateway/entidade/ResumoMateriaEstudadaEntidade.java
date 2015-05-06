package br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.com.guarasoft.studyware.materia.gateway.entidade.MateriaEntidade;

@Data
@AllArgsConstructor
public class ResumoMateriaEstudadaEntidade {

	private MateriaEntidade materia;
	private Long tempoAlocado;
	private Long somaTempo;

}
