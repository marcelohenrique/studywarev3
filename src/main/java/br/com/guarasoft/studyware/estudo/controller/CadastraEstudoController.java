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
import br.com.guarasoft.studyware.estudo.view.MateriaCicloView;
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
	private Estudo estudo;

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

	// @Getter
	// private DualListModel<Materia> materias;

	@Getter
	private List<MateriaCicloView> ciclo = new ArrayList<>();

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

		// List<Materia> materiasRestantes = null;
		// List<Materia> materiasSelecionadas = new ArrayList<>();

		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		this.estudo = (Estudo) ec.getFlash().get("estudo");
		if (this.estudo == null) {
			this.estudoNovo = true;

			// materiasRestantes = this.materiaGateway.buscaMaterias();
		} else {
			this.nome = this.estudo.getNome();
			this.fim = this.estudo.getFim();

			// materiasRestantes = this.materiaGateway
			// .buscaMateriasRestantes(estudo);

			for (EstudoMateria estudoMateria : this.estudo.getMaterias()) {
				// materiasSelecionadas.add(estudoMateria.getMateria());
				MateriaCicloView materiaCiclo = new MateriaCicloView();
				materiaCiclo.setMateria(estudoMateria);
				this.ciclo.add(materiaCiclo);
			}
		}

		// this.materias = new DualListModel<>(materiasRestantes,
		// materiasSelecionadas);

		if (!this.estudoNovo) {
			// this.atualizaCiclo(this.estudo);

			this.atualizaTotalCiclo();

			this.semana = new ArrayList<>();

			EstudoDiaView diaView = null;
			EstudoDiario estudoDiario = null;
			for (Dia dia : Dia.values()) {
				estudoDiario = new EstudoDiario();
				estudoDiario.setEstudo(this.estudo);
				estudoDiario.setDia(dia);

				if (this.estudo.getDias().contains(estudoDiario)) {
					estudoDiario = this.estudo.getDias().get(
							this.estudo.getDias().indexOf(estudoDiario));
				}

				diaView = new EstudoDiaView();
				diaView.setEstudoDiario(estudoDiario);
				this.semana.add(diaView);
			}

			this.atualizaTotalSemana();
		}
	}

	private void atualizaCiclo(Estudo estudo) {
		Long ordem = 1L;
		this.ciclo = new ArrayList<>();
		List<EstudoMateria> materiasEstudo = estudo.getMaterias();
		MateriaCicloView materiaCiclo = null;
		for (Materia materia : this.materias) {
			EstudoMateria estudoMateria = new EstudoMateria();
			estudoMateria.setMateria(materia);

			if (materiasEstudo.contains(estudoMateria)) {
				estudoMateria = materiasEstudo.get(materiasEstudo
						.indexOf(estudoMateria));
			}

			estudoMateria.setOrdem(ordem++);

			materiaCiclo = new MateriaCicloView();
			materiaCiclo.setMateria(estudoMateria);
			this.ciclo.add(materiaCiclo);
		}
	}

	public String cadastrar() {
		Estudo estudo = new Estudo(this.nome, this.usuario, this.fim);
		this.atualizaCiclo(estudo);

		// for (MateriaCicloView materiaCicloView : this.ciclo) {
		// estudo.add(materiaCicloView.getMateria());
		// }

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
		// for (MateriaCicloView materiaCicloView : this.ciclo) {
		// this.totalCiclo = this.totalCiclo.plus(materiaCicloView
		// .getMateria().getTempoAlocado());
		// }
	}

	public void atualizaTotalSemana() {
		this.totalSemana = new Duration(0);
		for (EstudoDiaView estudoDiaView : this.semana) {
			this.totalSemana = this.totalSemana.plus(estudoDiaView
					.getEstudoDiario().getTempoAlocado());
		}
	}

	public void adiciona(Materia materia) {
	}

}
