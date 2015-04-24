package br.com.guarasoft.studyware.controleestudo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.joda.time.Duration;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.controleestudo.presenter.GraficoDiario;
import br.com.guarasoft.studyware.controller.converter.DurationConverter;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.gateway.EstudoDiaGateway;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;
import br.com.guarasoft.studyware.estudomateria.gateway.EstudoMateriaGateway;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.EstudoMateriaHistoricoGateway;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoEstudoMateria;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoMateria;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.EstudoSemanalGateway;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "controleestudo")
@ViewScoped
@Data
public class ControleEstudoController implements Serializable {

	private static final long serialVersionUID = -5358580904420656733L;

	private final Logger logger = LoggerFactory
			.getLogger(ControleEstudoController.class);

	@Inject
	private EstudoGateway estudoGateway;
	@Inject
	private EstudoMateriaGateway estudoMateriaGateway;
	@Inject
	private EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway;
	@Inject
	private EstudoSemanalGateway estudoSemanalGateway;
	@Inject
	private EstudoDiaGateway estudoDiaGateway;
	private @Inject MateriaGateway materiaGateway;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Long agoraInMillis;
	private Duration tempoTotalAlocado = new Duration(0);
	private Duration tempoEstudadoTotal = new Duration(0);

	private Estudo estudoSelecionado;

	private List<ResumoEstudoMateria> resumoEstudoMaterias;
	private Materia materiaSelecionada;
	private EstudoMateriaHistorico materiaEstudada;
	private List<EstudoMateriaHistorico> materiasEstudadas;
	private List<EstudoSemanalBean> estudosSemanais;
	private List<Materia> materiasDoEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private Usuario usuario;

	private List<Estudo> estudos;

	@Setter(AccessLevel.PRIVATE)
	private GraficoDiario graficoDiario;

	@PostConstruct
	private void init() {
		this.materiaEstudada = this.build();

		this.estudos = this.estudoGateway.recuperaEstudosValidos(this.usuario
				.getEmail());
	}

	private void atualiza() {

		this.tempoTotalAlocado = new Duration(0);
		this.tempoEstudadoTotal = new Duration(0);

		Collection<ResumoMateria> resumoMaterias = this.estudoMateriaHistoricoGateway
				.buscaResumosMaterias(this.estudoSelecionado);
		Map<Long, ResumoMateria> mapaResumoMaterias = new HashMap<>();
		for (ResumoMateria resumoMateria : resumoMaterias) {
			mapaResumoMaterias.put(resumoMateria.getMateria().getId(),
					resumoMateria);

			this.tempoEstudadoTotal = this.tempoEstudadoTotal
					.plus(resumoMateria.getSomaTempo());
		}

		this.resumoEstudoMaterias = new ArrayList<>();
		for (EstudoMateria estudoMateria : estudoMateriaGateway
				.buscaEstudoMateria(estudoSelecionado)) {
			ResumoEstudoMateria resumoEstudoMateria = new ResumoEstudoMateria();
			resumoEstudoMateria.setEstudoMateria(estudoMateria);
			resumoEstudoMaterias.add(resumoEstudoMateria);

			this.tempoTotalAlocado = this.tempoTotalAlocado.plus(estudoMateria
					.getTempoAlocado());
		}

		boolean continua;
		do {
			continua = false;
			for (ResumoEstudoMateria resumoEstudoMateria : resumoEstudoMaterias) {

				ResumoMateria resumoMateria = mapaResumoMaterias
						.get(resumoEstudoMateria.getEstudoMateria()
								.getMateria().getId());
				Duration tempoMateria = resumoMateria.getSomaTempo();

				if (tempoMateria.getMillis() > 0) {

					Duration tempoAlocado = resumoEstudoMateria
							.getEstudoMateria().getTempoAlocado();
					Duration tempoEstudoMateria = resumoMateria.getSomaTempo();

					if (tempoMateria.getMillis() <= tempoAlocado.getMillis()) {
						tempoEstudoMateria = tempoEstudoMateria
								.plus(tempoMateria);
						tempoMateria = new Duration(0);
					} else {
						tempoEstudoMateria = tempoEstudoMateria
								.plus(tempoAlocado);
						tempoMateria = tempoMateria.minus(tempoAlocado);

						continua = true;
					}

					resumoMateria.setSomaTempo(tempoMateria);
					resumoEstudoMateria.setSomaTempo(tempoEstudoMateria);

				}
			}
		} while (continua);

		this.materiasEstudadas = this.estudoMateriaHistoricoGateway
				.findAll(this.estudoSelecionado);
		this.estudosSemanais = this.estudoSemanalGateway
				.findAll(this.estudoSelecionado);

		Collection<EstudoSemanal> estudosDiarios = this.estudoDiaGateway
				.findAll(this.estudoSelecionado.getId());

		graficoDiario = new GraficoDiario(estudosDiarios);
	}

	private EstudoMateriaHistorico build() {
		EstudoMateriaHistorico materiaEstudada = new EstudoMateriaHistorico();
		materiaEstudada.setMateria(new Materia());
		return materiaEstudada;
	}

	public void iniciar() {
		this.btIniciarDisabled = true;
		this.btZerarDisabled = false;
		this.btGravarDisabled = false;
	}

	public void pausar() {
		this.btIniciarDisabled = false;
	}

	public void zerar() {
		this.btIniciarDisabled = false;
		this.btZerarDisabled = true;
		this.btGravarDisabled = true;

		this.horasEstudadaInMillis = null;
		this.materiaEstudada = this.build();
	}

	public void gravar() {
		this.btIniciarDisabled = false;
		this.btZerarDisabled = true;
		this.btGravarDisabled = true;

		EstudoMateriaHistorico materiaEstudada = new EstudoMateriaHistorico();
		materiaEstudada.setEstudo(estudoSelecionado);
		materiaEstudada.setMateria(this.materiaSelecionada);
		materiaEstudada.setHoraEstudo(new Date());
		materiaEstudada.setTempoEstudado(new Duration(
				this.horasEstudadaInMillis));

		this.logger.info(materiaEstudada.toString());
		this.estudoMateriaHistoricoGateway.persist(materiaEstudada);
		this.materiasEstudadas = this.estudoMateriaHistoricoGateway
				.findAll(this.estudoSelecionado);
		this.materiaEstudada = this.build();
		this.atualiza();
	}

	public boolean getBtPausarDisabled() {
		return !this.btIniciarDisabled;
	}

	public void listaEstudoMaterias() {
		this.materiasDoEstudo = materiaGateway.buscaMaterias(estudoSelecionado);
		this.atualiza();
	}

	public String getTempoTotalAlocadoView() {
		return new DurationConverter().toString(tempoTotalAlocado);
	}

	public String getTempoEstudadoTotalView() {
		return new DurationConverter().toString(tempoEstudadoTotal);
	}

	public void onRowEdit(RowEditEvent event) {
		EstudoMateriaHistorico entrada = (EstudoMateriaHistorico) event
				.getObject();
		entrada.setEstudo(estudoSelecionado);
		estudoMateriaHistoricoGateway.merge(entrada);

	}

	public void onRowCancel(RowEditEvent event) {
	}
}
