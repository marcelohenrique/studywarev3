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

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.Duration;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudoImpl;
import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.estudo.view.EstudoDiaView;
import br.com.guarasoft.studyware.estudo.view.MateriaCicloView;
import br.com.guarasoft.studyware.estudodiario.bean.DiaBean;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@ManagedBean(name = "usuarioEstudo")
@ViewScoped
@Data
public class UsuarioEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private UsuarioEstudoGateway usuarioEstudoGateway;

	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private MateriaGateway materiaGateway;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private CadastrarUsuarioEstudo cadastrarUsuarioEstudo;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private RemoverUsuarioEstudo removerUsuarioEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	@Getter(AccessLevel.PRIVATE)
	private Usuario usuario;

	private DualListModel<MateriaBean> materias;

	private List<UsuarioEstudoBean> estudos;

	private UsuarioEstudoBean bean;

	private List<MateriaCicloView> ciclo;
	private Duration totalCiclo = new Duration(0);

	private List<EstudoDiaView> semana;
	private Duration totalSemana = new Duration(0);

	@PostConstruct
	private void init() {
		this.cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(this.usuarioEstudoGateway);
		this.removerUsuarioEstudo = new RemoverUsuarioEstudoImpl(this.usuarioEstudoGateway);

		List<MateriaBean> materiasRestantes = null;
		List<MateriaBean> materiasSelecionadas = new ArrayList<>();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		this.bean = (UsuarioEstudoBean) ec.getFlash().get("usuarioEstudo");
		if (this.bean == null) {
			this.bean = new UsuarioEstudoBean();
			this.bean.setUsuario(this.usuario);
			this.bean.setMaterias(new ArrayList<UsuarioEstudoMateriaBean>());
			this.bean.setDias(new ArrayList<UsuarioEstudoDiarioBean>());

			materiasRestantes = this.materiaGateway.buscaMaterias();
		} else {
			materiasRestantes = this.materiaGateway.buscaMateriasRestantes(this.bean);

			for (UsuarioEstudoMateriaBean usuarioEstudoMateriaBean : this.bean.getMaterias()) {
				materiasSelecionadas.add(usuarioEstudoMateriaBean.getMateria());
			}
		}

		this.materias = new DualListModel<>(materiasRestantes, materiasSelecionadas);

		this.estudos = this.usuarioEstudoGateway.recuperaTodosEstudos(this.usuario.getEmail());
	}

	public String onFlowProcess(FlowEvent event) {
		String newStep = event.getNewStep();
		if ("ciclo".equals(newStep)) {
			this.atualizaCiclo();

			this.atualizaTotalCiclo();
		} else if ("semana".equals(newStep)) {
			this.semana = new ArrayList<>();

			EstudoDiaView dia = null;
			UsuarioEstudoDiarioBean estudoDiario = null;
			for (DiaBean diaBean : DiaBean.values()) {
				estudoDiario = new UsuarioEstudoDiarioBean();
				estudoDiario.setUsuarioEstudo(this.bean);
				estudoDiario.setDia(diaBean);

				if (this.bean.getDias().contains(estudoDiario)) {
					estudoDiario = this.bean.getDias().get(this.bean.getDias().indexOf(estudoDiario));
				}

				dia = new EstudoDiaView();
				dia.setEstudoDiario(estudoDiario);
				this.semana.add(dia);
			}

			this.atualizaTotalSemana();
		}
		return newStep;
	}

	private void atualizaCiclo() {
		Long ordem = 1L;
		this.ciclo = new ArrayList<>();
		List<UsuarioEstudoMateriaBean> materiasEstudo = this.bean.getMaterias();
		this.bean.setMaterias(new ArrayList<UsuarioEstudoMateriaBean>());
		MateriaCicloView materiaCiclo = null;
		for (MateriaBean materia : this.materias.getTarget()) {
			UsuarioEstudoMateriaBean usuarioEstudoMateriaBean = new UsuarioEstudoMateriaBean();
			usuarioEstudoMateriaBean.setMateria(materia);

			if (materiasEstudo.contains(usuarioEstudoMateriaBean)) {
				usuarioEstudoMateriaBean = materiasEstudo.get(materiasEstudo.indexOf(usuarioEstudoMateriaBean));
			}
			this.bean.getMaterias().add(usuarioEstudoMateriaBean);

			usuarioEstudoMateriaBean.setOrdem(ordem++);

			materiaCiclo = new MateriaCicloView();
			materiaCiclo.setMateria(usuarioEstudoMateriaBean);
			this.ciclo.add(materiaCiclo);
		}
	}

	public String cadastrar() {
		this.atualizaCiclo();
		if (this.semana != null) {
			this.bean.setDias(new ArrayList<UsuarioEstudoDiarioBean>());
			for (EstudoDiaView diaView : this.semana) {
				if (diaView.getEstudoDiario().getTempoAlocado() != null) {
					this.bean.getDias().add(diaView.getEstudoDiario());
				}
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarUsuarioEstudo.execute(this.bean);
			context.addMessage(null, new FacesMessage("Sucesso", "Estudo cadastrado"));
			return new MenuController().consultarUsuarioEstudo();
		} catch (UsuarioEstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha", "Estudo já exite"));
		}
		return null;
	}

	public String alterar(UsuarioEstudoBean usuarioEstudoBean) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.getFlash().put("usuarioEstudo", usuarioEstudoBean);

		return new MenuController().cadastrarUsuarioEstudo();
	}

	public void remover(UsuarioEstudoBean usuarioEstudo) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.removerUsuarioEstudo.execute(usuarioEstudo);

			this.estudos = this.usuarioEstudoGateway.recuperaTodosEstudos(this.usuario.getEmail());

			context.addMessage(null, new FacesMessage("Sucesso", "Estudo removido"));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro", "Erro ao remover o estudo"));
		}
	}

	public void atualizaTotalCiclo() {
		this.totalCiclo = new Duration(0);
		for (MateriaCicloView materiaCicloView : this.ciclo) {
			this.totalCiclo = this.totalCiclo.plus(materiaCicloView.getMateria().getTempoAlocado());
		}
	}

	public void atualizaTotalSemana() {
		this.totalSemana = new Duration(0);
		for (EstudoDiaView estudoDiaView : this.semana) {
			this.totalSemana = this.totalSemana.plus(estudoDiaView.getEstudoDiario().getTempoAlocado());
		}
	}

}
