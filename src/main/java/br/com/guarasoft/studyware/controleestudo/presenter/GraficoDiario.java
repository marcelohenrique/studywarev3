package br.com.guarasoft.studyware.controleestudo.presenter;

import java.util.Collection;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;

public class GraficoDiario {

	private static final Double SEGUNDO = 1000.0;
	private static final Double MINUTO = SEGUNDO * 60;
	private static final Double HORA = MINUTO * 60;

	private LineChartModel graficoEstudoDiario;
	private Collection<EstudoSemanal> estudosDiarios;

	public GraficoDiario(Collection<EstudoSemanal> estudosDiarios) {
		this.estudosDiarios = estudosDiarios;

		montaGraficoEstudoDiario();
	}

	private void montaGraficoEstudoDiario() {
		this.graficoEstudoDiario = new LineChartModel();
		this.graficoEstudoDiario.setAnimate(true);
		this.graficoEstudoDiario.setZoom(true);
		// this.graficoEstudoDiario.setLegendPosition("e");
		graficoEstudoDiario.setExtender("extLegend");

		Axis eixoY = this.graficoEstudoDiario.getAxis(AxisType.Y);
		eixoY.setMin(0);
		eixoY.setMax(12);

		DateAxis axisX = new DateAxis();
		axisX.setTickAngle(-50);
		axisX.setTickFormat("%d/%m/%y");

		this.graficoEstudoDiario.getAxes().put(AxisType.X, axisX);

		LineChartSeries planejado = new LineChartSeries();
		planejado.setLabel("Planejado");
		this.graficoEstudoDiario.addSeries(planejado);

		LineChartSeries executado = new LineChartSeries();
		executado.setLabel("Executado");
		this.graficoEstudoDiario.addSeries(executado);

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		for (EstudoSemanal estudoDiario : this.estudosDiarios) {
			if (estudoDiario != null) {
				String date = formatter.print(estudoDiario.getInicioSemana()
						.getTime());
				Double tempoAlocado = estudoDiario.getTempoAlocado()
						.getMillis() / HORA;
				Double tempoEstudado = estudoDiario.getTempoEstudado()
						.getMillis() / HORA;
				planejado.set(date, tempoAlocado);
				executado.set(date, tempoEstudado);
			}
		}
	}

	public LineChartModel getGraficoEstudoDiario() {
		return graficoEstudoDiario;
	}

}
