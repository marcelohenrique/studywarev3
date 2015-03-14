package br.com.guarasoft.studyware.estudo.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class UsuarioEstudoBean {

	private Long id;
	private Usuario usuario;
	private String nome;
	private Date fim;
	private List<UsuarioEstudoMateriaBean> materias;
	private List<UsuarioEstudoDiarioBean> dias;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		UsuarioEstudoBean other = (UsuarioEstudoBean) obj;
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}
		if (this.usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!this.usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		result = prime * result + ((this.usuario == null) ? 0 : this.usuario.hashCode());
		return result;
	}

}
