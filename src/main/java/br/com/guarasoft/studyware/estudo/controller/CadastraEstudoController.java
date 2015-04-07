package br.com.guarasoft.studyware.estudo.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import br.com.guarasoft.studyware.estudo.casodeuso.CadastrarEstudo;
import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudo.view.EstudoDiaView;
import br.com.guarasoft.studyware.estudo.view.MateriaCicloView;
import br.com.guarasoft.studyware.estudodiario.bean.DiaBean;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "cadastraEstudoController")
@ViewScoped
// @Data
public class CadastraEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	private @Inject EstudoGateway estudoGateway;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	private @Inject MateriaGateway materiaGateway;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	private CadastrarEstudo cadastrarEstudo;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	// private RemoverUsuarioEstudo removerEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	// @Getter(AccessLevel.PRIVATE)
	private Usuario usuario;

	// private List<Estudo> estudos;

	@NotNull
	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private DateTime fim;

	@Getter
	private boolean estudoNovo;

	@Getter
	private DualListModel<MateriaBean> materias;

	@Getter
	private List<MateriaCicloView> ciclo;

	@Getter
	private Duration totalCiclo = new Duration(0);

	@Getter
	private List<EstudoDiaView> semana;

	@Getter
	private Duration totalSemana = new Duration(0);

	@PostConstruct
	private void init() {
		this.cadastrarEstudo = new CadastrarEstudo(this.estudoGateway);
		// this.removerEstudo = new
		// RemoverUsuarioEstudoImpl(this.estudoGateway);

		List<MateriaBean> materiasRestantes = null;
		List<MateriaBean> materiasSelecionadas = new ArrayList<>();

		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		Estudo estudo = (Estudo) ec.getFlash().get("estudo");
		if (estudo == null) {
			estudoNovo = true;

			// bean = new Estudo();
			// bean.setUsuario(this.usuario);
			// bean.setMaterias(new ArrayList<UsuarioEstudoMateriaBean>());
			// bean.setDias(new ArrayList<UsuarioEstudoDiarioBean>());

			materiasRestantes = this.materiaGateway.buscaMaterias();
		} else {
			materiasRestantes = this.materiaGateway
					.buscaMateriasRestantes(estudo);

			for (UsuarioEstudoMateriaBean usuarioEstudoMateriaBean : estudo
					.getMaterias()) {
				materiasSelecionadas.add(usuarioEstudoMateriaBean.getMateria());
			}

			this.atualizaCiclo(estudo);

			this.atualizaTotalCiclo();

			this.semana = new ArrayList<>();

			EstudoDiaView dia = null;
			UsuarioEstudoDiarioBean estudoDiario = null;
			for (DiaBean diaBean : DiaBean.values()) {
				estudoDiario = new UsuarioEstudoDiarioBean();
				estudoDiario.setUsuarioEstudo(estudo);
				estudoDiario.setDia(diaBean);

				if (estudo.getDias().contains(estudoDiario)) {
					estudoDiario = estudo.getDias().get(
							estudo.getDias().indexOf(estudoDiario));
				}

				dia = new EstudoDiaView();
				dia.setEstudoDiario(estudoDiario);
				this.semana.add(dia);
			}

			this.atualizaTotalSemana();
		}

		this.materias = new DualListModel<>(materiasRestantes,
				materiasSelecionadas);

		// this.estudos = this.estudoGateway.recuperaTodosEstudos(this.usuario
		// .getEmail());
	}

	public String onFlowProcess(FlowEvent event) {
		String newStep = event.getNewStep();
		if ("ciclo".equals(newStep)) {
		} else if ("semana".equals(newStep)) {
		}
		return newStep;
	}

	private void atualizaCiclo(Estudo estudo) {
		Long ordem = 1L;
		this.ciclo = new ArrayList<>();
		List<UsuarioEstudoMateriaBean> materiasEstudo = estudo.getMaterias();
		estudo.setMaterias(new ArrayList<UsuarioEstudoMateriaBean>());
		MateriaCicloView materiaCiclo = null;
		for (MateriaBean materia : this.materias.getTarget()) {
			UsuarioEstudoMateriaBean usuarioEstudoMateriaBean = new UsuarioEstudoMateriaBean();
			usuarioEstudoMateriaBean.setMateria(materia);

			if (materiasEstudo.contains(usuarioEstudoMateriaBean)) {
				usuarioEstudoMateriaBean = materiasEstudo.get(materiasEstudo
						.indexOf(usuarioEstudoMateriaBean));
			}
			estudo.getMaterias().add(usuarioEstudoMateriaBean);

			usuarioEstudoMateriaBean.setOrdem(ordem++);

			materiaCiclo = new MateriaCicloView();
			materiaCiclo.setMateria(usuarioEstudoMateriaBean);
			this.ciclo.add(materiaCiclo);
		}
	}

	public String cadastrar() {
		Estudo estudo = new Estudo(this.nome, this.usuario, null);
		this.atualizaCiclo(estudo);
		if (this.semana != null) {
			estudo.setDias(new ArrayList<UsuarioEstudoDiarioBean>());
			for (EstudoDiaView diaView : this.semana) {
				if (diaView.getEstudoDiario().getTempoAlocado() != null) {
					estudo.getDias().add(diaView.getEstudoDiario());
				}
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarEstudo.execute(estudo);
			context.addMessage(null, new FacesMessage("Sucesso",
					"Estudo cadastrado"));
			return new MenuController().consultarUsuarioEstudo();
		} catch (UsuarioEstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha",
					"Estudo já exite"));
		}
		return null;
	}

	// public String alterar(Estudo estudo) {
	// ExternalContext ec = FacesContext.getCurrentInstance()
	// .getExternalContext();
	// ec.getFlash().put("usuarioEstudo", estudo);
	//
	// return new MenuController().cadastrarEstudo();
	// }

	// public void remover(Estudo usuarioEstudo) {
	// FacesContext context = FacesContext.getCurrentInstance();
	// try {
	// this.removerEstudo.execute(usuarioEstudo);
	//
	// this.estudos = this.estudoGateway.recuperaTodosEstudos(this.usuario
	// .getEmail());
	//
	// context.addMessage(null, new FacesMessage("Sucesso",
	// "Estudo removido"));
	// } catch (Exception e) {
	// context.addMessage(null, new FacesMessage("Erro",
	// "Erro ao remover o estudo"));
	// }
	// }

	public void atualizaTotalCiclo() {
		this.totalCiclo = new Duration(0);
		for (MateriaCicloView materiaCicloView : this.ciclo) {
			this.totalCiclo = this.totalCiclo.plus(materiaCicloView
					.getMateria().getTempoAlocado());
		}
	}

	public void atualizaTotalSemana() {
		this.totalSemana = new Duration(0);
		for (EstudoDiaView estudoDiaView : this.semana) {
			this.totalSemana = this.totalSemana.plus(estudoDiaView
					.getEstudoDiario().getTempoAlocado());
		}
	}

}
