package br.com.guarasoft.studyware.estudomateriahistorico.casodeuso;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.EstudoMateriaHistoricoGateway;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;

public class GravaEstudoMateriaHistoricoImpl implements
		GravaEstudoMateriaHistorico {

	private EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway;

	public GravaEstudoMateriaHistoricoImpl(
			EstudoMateriaHistoricoGateway estudoMateriaHistoricoGateway) {
		this.estudoMateriaHistoricoGateway = estudoMateriaHistoricoGateway;
	}

	@Override
	public void gravar(EstudoMateriaHistorico historico) {
		this.estudoMateriaHistoricoGateway.persist(historico);

		ajusteHistorico(historico.getEstudo());
	}

	@Override
	public void ajusteHistorico(Estudo estudo) {
		DateTime inicioEstudo = new DateTime(estudo.getInicio())
				.withTimeAtStartOfDay();

		DateTime dia = new DateTime().minusDays(1).withTimeAtStartOfDay();

		for (EstudoMateriaHistorico estudoDia : estudoMateriaHistoricoGateway
				.findAll(estudo)) {

			DateTime diaEstudo = new DateTime(estudoDia.getHoraEstudo())
					.withTimeAtStartOfDay();

			dia = gravaHistoricoAnterior(diaEstudo, dia, estudo);
		}

		gravaHistoricoAnterior(inicioEstudo, dia, estudo);
	}

	private DateTime gravaHistoricoAnterior(DateTime diaBase, DateTime dia,
			Estudo estudo) {
		if (diaBase.isBefore(dia)) {
			do {
				EstudoMateriaHistorico estudoDia = new EstudoMateriaHistorico();
				estudoDia.setEstudo(estudo);
				estudoDia.setHoraEstudo(dia.toDate());
				estudoDia.setTempoEstudado(Duration.ZERO);
				estudoDia.setObservacao("Dia sem estudo!");
				estudoMateriaHistoricoGateway.persist(estudoDia);

				dia = dia.minusDays(1).withTimeAtStartOfDay();
			} while (diaBase.isBefore(dia));
		} else if (diaBase.isAfter(dia)) {
			return dia;
		}
		return dia.minusDays(1).withTimeAtStartOfDay();
	}
}
