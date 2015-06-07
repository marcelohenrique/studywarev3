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
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.Duration;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.controleestudo.presenter.GraficoDiario;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.gateway.EstudoDiaGateway;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;
import br.com.guarasoft.studyware.estudomateria.gateway.EstudoMateriaGateway;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.estudomateriahistorico.casodeuso.GravaEstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.casodeuso.GravaEstudoMateriaHistoricoImpl;
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

	private @Inject EstudoGateway estudoGateway;
	private @Inject MateriaGateway materiaGateway;
	private @Inject EstudoMateriaGateway estudoMateriaGateway;
	private @Inject EstudoSemanalGateway estudoSemanalGateway;
	private @Inject EstudoDiaGateway estudoDiaGateway;
	private @Inject EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway;

	private GravaEstudoMateriaHistorico gravaEstudoMateriaHistorico;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Long agoraInMillis;
	@Getter
	private Duration tempoTotalAlocado = Duration.ZERO;
	@Getter
	private Duration tempoEstudadoTotal = Duration.ZERO;

	private Estudo estudoSelecionado;

	private Collection<ResumoMateria> resumoMaterias;
	private List<ResumoEstudoMateria> resumoEstudoMaterias;
	private Materia materiaSelecionada;
	private List<EstudoMateriaHistorico> materiasEstudadas;
	private List<EstudoSemanalBean> estudosSemanais;
	private List<Materia> materiasDoEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private Usuario usuario;

	private List<Estudo> estudos;

	@Setter(AccessLevel.PRIVATE)
	private GraficoDiario graficoDiario;

	@Getter
	private double cicloTotal;

	@Getter
	@Setter
	@NotNull
	private String observacao;

	@PostConstruct
	private void init() {
		this.estudos = this.estudoGateway.recuperaEstudosValidos(this.usuario
				.getEmail());

		gravaEstudoMateriaHistorico = new GravaEstudoMateriaHistoricoImpl(
				estudoMateriaHistoricoGateway);
	}

	private void atualiza() {

		this.tempoTotalAlocado = Duration.ZERO;
		this.tempoEstudadoTotal = Duration.ZERO;

		Map<Long, Materia> mapaMateria = new HashMap<>();
		this.resumoEstudoMaterias = new ArrayList<>();
		for (EstudoMateria estudoMateria : estudoMateriaGateway
				.buscaEstudoMateria(estudoSelecionado)) {
			ResumoEstudoMateria resumoEstudoMateria = new ResumoEstudoMateria();
			resumoEstudoMateria.setEstudoMateria(estudoMateria);
			resumoEstudoMateria.setSomaTempo(Duration.ZERO);
			resumoEstudoMaterias.add(resumoEstudoMateria);

			this.tempoTotalAlocado = this.tempoTotalAlocado.plus(estudoMateria
					.getTempoAlocado());

			mapaMateria.put(estudoMateria.getMateria().getId(),
					estudoMateria.getMateria());
		}

		resumoMaterias = this.estudoMateriaHistoricoGateway
				.buscaResumosMaterias(this.estudoSelecionado);
		Map<Long, Duration> mapaMateriaTempo = new HashMap<>();
		for (ResumoMateria resumoMateria : resumoMaterias) {
			mapaMateriaTempo.put(resumoMateria.getMateria().getId(),
					resumoMateria.getSomaTempo());

			this.tempoEstudadoTotal = this.tempoEstudadoTotal
					.plus(resumoMateria.getSomaTempo());

			resumoMateria.setMateria(mapaMateria.get(resumoMateria.getMateria()
					.getId()));
		}

		boolean continua;
		do {
			continua = false;
			for (ResumoEstudoMateria resumoEstudoMateria : resumoEstudoMaterias) {

				Duration tempoMateria = mapaMateriaTempo
						.get(resumoEstudoMateria.getEstudoMateria()
								.getMateria().getId());
				if (tempoMateria.getMillis() > 0) {

					Duration tempoAlocado = resumoEstudoMateria
							.getEstudoMateria().getTempoAlocado();
					Duration tempoEstudoMateria = resumoEstudoMateria
							.getSomaTempo();

					if (tempoAlocado.isEqual(Duration.ZERO)) {
						tempoEstudoMateria = tempoMateria;
						tempoMateria = Duration.ZERO;
					} else if (tempoMateria.isShorterThan(tempoAlocado)
							|| tempoMateria.isEqual(tempoAlocado)) {
						tempoEstudoMateria = tempoEstudoMateria
								.plus(tempoMateria);
						tempoMateria = Duration.ZERO;
					} else {
						tempoEstudoMateria = tempoEstudoMateria
								.plus(tempoAlocado);
						tempoMateria = tempoMateria.minus(tempoAlocado);

						continua = true;
					}

					resumoEstudoMateria.setSomaTempo(tempoEstudoMateria);

					mapaMateriaTempo.put(resumoEstudoMateria.getEstudoMateria()
							.getMateria().getId(), tempoMateria);
				}
			}
		} while (continua);

		double sum = 0;
		int count = 0;
		for (ResumoEstudoMateria resumoEstudoMateria : resumoEstudoMaterias) {
			if (!resumoEstudoMateria.getEstudoMateria().getTempoAlocado()
					.isEqual(Duration.ZERO)) {
				sum += resumoEstudoMateria.getCiclo();
				count++;
			}
		}
		cicloTotal = sum / count;

		selecionaMateria();
		this.estudosSemanais = this.estudoSemanalGateway
				.findAll(this.estudoSelecionado);

		Collection<EstudoSemanal> estudosDiarios = this.estudoDiaGateway
				.findAll(this.estudoSelecionado.getId());

		graficoDiario = new GraficoDiario(estudosDiarios);
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
		materiaEstudada.setObservacao(observacao);

		gravaEstudoMateriaHistorico.gravar(materiaEstudada);

		selecionaMateria();
		observacao = null;
		this.atualiza();
	}

	public boolean getBtPausarDisabled() {
		return !this.btIniciarDisabled;
	}

	public void listaEstudoMaterias() {
		this.materiasDoEstudo = materiaGateway.buscaMaterias(estudoSelecionado);
		this.atualiza();
	}

	public void onRowEdit(RowEditEvent event) {
		EstudoMateriaHistorico entrada = (EstudoMateriaHistorico) event
				.getObject();
		entrada.setEstudo(estudoSelecionado);
		estudoMateriaHistoricoGateway.merge(entrada);
	}

	public void onRowCancel(RowEditEvent event) {
	}

	public void remover(EstudoMateriaHistorico historico) {
		estudoMateriaHistoricoGateway.remove(historico);

		selecionaMateria();
		this.atualiza();
	}

	public void selecionaMateria() {
		if (materiaSelecionada == null) {
			this.materiasEstudadas = this.estudoMateriaHistoricoGateway
					.findAll(this.estudoSelecionado);
		} else {
			this.materiasEstudadas = this.estudoMateriaHistoricoGateway
					.findAll(this.estudoSelecionado, this.materiaSelecionada);
		}
	}
}
