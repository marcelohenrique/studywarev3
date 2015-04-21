package br.com.guarasoft.studyware.estudo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.casodeuso.CadastraEstudo;
import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudo.view.EstudoDiaView;
import br.com.guarasoft.studyware.estudodiario.modelo.Dia;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoDiario;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "cadastraEstudoController")
@ViewScoped
public class CadastraEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	private CadastraEstudo cadastraEstudo;

	private @Inject EstudoGateway estudoGateway;

	private @Inject MateriaGateway materiaGateway;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	@Setter
	private Usuario usuario;

	@Getter
	private boolean estudoNovo;

	@NotNull
	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private Date fim;

	@Getter
	private Collection<Materia> materias;

	@Getter
	private List<EstudoMateria> ciclo = new ArrayList<>();

	@Getter
	private Duration totalCiclo = new Duration(0);

	@Getter
	private List<EstudoDiaView> semana;

	@Getter
	private Duration totalSemana = new Duration(0);

	@PostConstruct
	private void init() {
		this.cadastraEstudo = new CadastraEstudo(this.estudoGateway);

		this.materias = this.materiaGateway.buscaMaterias();

		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		Estudo estudo = (Estudo) ec.getFlash().get("estudo");
		if (estudo == null) {
			this.estudoNovo = true;
		} else {
			this.nome = estudo.getNome();
			this.fim = estudo.getFim();
			this.ciclo.addAll(estudo.getMaterias());
		}

		if (!this.estudoNovo) {
			this.atualizaTotalCiclo();

			this.semana = new ArrayList<>();

			EstudoDiaView diaView = null;
			EstudoDiario estudoDiario = null;
			for (Dia dia : Dia.values()) {
				estudoDiario = new EstudoDiario();
				estudoDiario.setEstudo(estudo);
				estudoDiario.setDia(dia);

				if (estudo.getDias().contains(estudoDiario)) {
					estudoDiario = estudo.getDias().get(
							estudo.getDias().indexOf(estudoDiario));
				}

				diaView = new EstudoDiaView();
				diaView.setEstudoDiario(estudoDiario);
				this.semana.add(diaView);
			}

			this.atualizaTotalSemana();
		}
	}

	public String cadastrar() {
		Estudo estudo = new Estudo(this.nome, this.usuario, this.fim,
				this.ciclo);

		if (this.semana != null) {
			for (EstudoDiaView diaView : this.semana) {
				if (diaView.getEstudoDiario().getTempoAlocado() != null) {
					estudo.add(diaView.getEstudoDiario());
				}
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastraEstudo.execute(estudo);
			context.addMessage(null, new FacesMessage("Sucesso",
					"Estudo cadastrado"));
			return new MenuController().consultarUsuarioEstudo();
		} catch (UsuarioEstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha",
					"Estudo já exite"));
		}
		return null;
	}

	public void atualizaTotalCiclo() {
		this.totalCiclo = new Duration(0);

		for (EstudoMateria estudoMateria : this.ciclo) {
			this.totalCiclo = this.totalCiclo.plus(estudoMateria.getTempoAlocado());
		}
	}

	public void atualizaTotalSemana() {
		this.totalSemana = new Duration(0);

		for (EstudoDiaView estudoDiaView : this.semana) {
			this.totalSemana = this.totalSemana.plus(estudoDiaView
					.getEstudoDiario().getTempoAlocado());
		}
	}

	public void adiciona(Materia materia) {
		EstudoMateria estudoMateria = new EstudoMateria();
		estudoMateria.setMateria(materia);
		this.ciclo.add(estudoMateria);
	}

}
