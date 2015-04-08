package br.com.guarasoft.studyware.estudo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "consultaEstudoController")
@ViewScoped
// @Data
public class ConsultaEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private @Inject EstudoGateway estudoGateway;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	// private @Inject MateriaGateway materiaGateway;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	// private CadastrarEstudo cadastrarEstudo;

	// @Getter(AccessLevel.PRIVATE)
	// @Setter(AccessLevel.PRIVATE)
	private RemoverUsuarioEstudo removerUsuarioEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	// @Getter(AccessLevel.PRIVATE)
	@Setter
	private Usuario usuario;

	// private DualListModel<MateriaBean> materias;

	@Getter
	private List<Estudo> estudos;

	// @NotNull
	// private String nome;

	// private List<MateriaCicloView> ciclo;
	// private Duration totalCiclo = new Duration(0);

	// private List<EstudoDiaView> semana;
	// private Duration totalSemana = new Duration(0);

	@PostConstruct
	private void init() {
		// this.cadastrarEstudo = new CadastrarEstudo(this.estudoGateway);
		// this.removerUsuarioEstudo = new RemoverUsuarioEstudoImpl(
		// this.estudoGateway);

		// List<MateriaBean> materiasRestantes = null;
		// List<MateriaBean> materiasSelecionadas = new ArrayList<>();

		// ExternalContext ec = FacesContext.getCurrentInstance()
		// .getExternalContext();
		// Estudo bean = (Estudo) ec.getFlash().get("estudo");
		// if (bean == null) {
		// bean = new Estudo();
		// bean.setUsuario(this.usuario);
		// bean.setMaterias(new ArrayList<EstudoMateria>());
		// bean.setDias(new ArrayList<EstudoDiario>());

		// materiasRestantes = this.materiaGateway.buscaMaterias();
		// } else {
		// materiasRestantes = this.materiaGateway
		// .buscaMateriasRestantes(bean);

		// for (EstudoMateria usuarioEstudoMateriaBean : bean
		// .getMaterias()) {
		// materiasSelecionadas.add(usuarioEstudoMateriaBean.getMateria());
		// }
		// }

		// this.materias = new DualListModel<>(materiasRestantes,
		// materiasSelecionadas);

		this.estudos = this.estudoGateway.recuperaTodosEstudos(this.usuario
				.getEmail());
	}

	// public String onFlowProcess(FlowEvent event) {
	// String newStep = event.getNewStep();
	// if ("ciclo".equals(newStep)) {
	// this.atualizaCiclo();
	//
	// this.atualizaTotalCiclo();
	// } else if ("semana".equals(newStep)) {
	// this.semana = new ArrayList<>();
	//
	// EstudoDiaView dia = null;
	// EstudoDiario estudoDiario = null;
	// for (Dia diaBean : Dia.values()) {
	// estudoDiario = new EstudoDiario();
	// estudoDiario.setUsuarioEstudo(this.bean);
	// estudoDiario.setDia(diaBean);
	//
	// if (this.bean.getDias().contains(estudoDiario)) {
	// estudoDiario = this.bean.getDias().get(
	// this.bean.getDias().indexOf(estudoDiario));
	// }
	//
	// dia = new EstudoDiaView();
	// dia.setEstudoDiario(estudoDiario);
	// this.semana.add(dia);
	// }
	//
	// this.atualizaTotalSemana();
	// }
	// return newStep;
	// }

	// private void atualizaCiclo() {
	// Long ordem = 1L;
	// this.ciclo = new ArrayList<>();
	// List<EstudoMateria> materiasEstudo = this.bean.getMaterias();
	// this.bean.setMaterias(new ArrayList<EstudoMateria>());
	// MateriaCicloView materiaCiclo = null;
	// for (MateriaBean materia : this.materias.getTarget()) {
	// EstudoMateria usuarioEstudoMateriaBean = new
	// EstudoMateria();
	// usuarioEstudoMateriaBean.setMateria(materia);
	//
	// if (materiasEstudo.contains(usuarioEstudoMateriaBean)) {
	// usuarioEstudoMateriaBean = materiasEstudo.get(materiasEstudo
	// .indexOf(usuarioEstudoMateriaBean));
	// }
	// this.bean.getMaterias().add(usuarioEstudoMateriaBean);
	//
	// usuarioEstudoMateriaBean.setOrdem(ordem++);
	//
	// materiaCiclo = new MateriaCicloView();
	// materiaCiclo.setMateria(usuarioEstudoMateriaBean);
	// this.ciclo.add(materiaCiclo);
	// }
	// }

	// public String cadastrar() {
	// this.atualizaCiclo();
	// if (this.semana != null) {
	// this.bean.setDias(new ArrayList<EstudoDiario>());
	// for (EstudoDiaView diaView : this.semana) {
	// if (diaView.getEstudoDiario().getTempoAlocado() != null) {
	// this.bean.getDias().add(diaView.getEstudoDiario());
	// }
	// }
	// }
	//
	// FacesContext context = FacesContext.getCurrentInstance();
	// try {
	// this.cadastrarEstudo.execute(this.bean);
	// context.addMessage(null, new FacesMessage("Sucesso",
	// "Estudo cadastrado"));
	// return new MenuController().consultarUsuarioEstudo();
	// } catch (UsuarioEstudoJaExiste e) {
	// context.addMessage(null, new FacesMessage("Falha",
	// "Estudo já exite"));
	// }
	// return null;
	// }

	public String alterar(Estudo estudo) {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.getFlash().put("estudo", estudo);

		return new MenuController().cadastrarEstudo();
	}

	public void remover(Estudo estudo) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.removerUsuarioEstudo.execute(estudo);

			this.estudos = this.estudoGateway.recuperaTodosEstudos(this.usuario
					.getEmail());

			context.addMessage(null, new FacesMessage("Sucesso",
					"Estudo removido"));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro",
					"Erro ao remover o estudo"));
		}
	}

	// public void atualizaTotalCiclo() {
	// this.totalCiclo = new Duration(0);
	// for (MateriaCicloView materiaCicloView : this.ciclo) {
	// this.totalCiclo = this.totalCiclo.plus(materiaCicloView
	// .getMateria().getTempoAlocado());
	// }
	// }

	// public void atualizaTotalSemana() {
	// this.totalSemana = new Duration(0);
	// for (EstudoDiaView estudoDiaView : this.semana) {
	// this.totalSemana = this.totalSemana.plus(estudoDiaView
	// .getEstudoDiario().getTempoAlocado());
	// }
	// }

}
