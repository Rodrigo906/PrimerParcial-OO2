package ar.unrn.parcial.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CombustibleSuper extends Combustible {

	private double descuentoDomingo = 0.1;
	private double descuentoSabado = 0.12;
	private double minLitrosParaDescuento = 20;

	public CombustibleSuper(double precioPorLitro, String descripcion, double descuentoDomingo, double descuentoSabado,
			double minLitrosParaDescuento) {
		super(precioPorLitro, descripcion);
		this.descuentoDomingo = descuentoDomingo;
		this.descuentoSabado = descuentoSabado;
		this.minLitrosParaDescuento = minLitrosParaDescuento;
	}

	@Override
	public double calcularMontoAPagar(double cantLitros) {
		double montoAPagarSinDescuento = super.calcularMontoAPagar(cantLitros);
		double descuentoTotal = 0;
		double montoTotalAPagar = 0;
		if (esDomingo()) {
			descuentoTotal += montoAPagarSinDescuento * descuentoDomingo;
		}
		if (esSabado() && alcanzoLitrosMinimosParaDescuento(cantLitros)) {
			descuentoTotal += montoAPagarSinDescuento * descuentoSabado;
		}
		montoTotalAPagar = montoAPagarSinDescuento - descuentoTotal;
		return montoTotalAPagar;
	}

	private boolean esDomingo() {
		if (obtenerNombreDia().equals("domingo")) {
			return true;
		}
		return false;
	}

	private boolean esSabado() {
		if (obtenerNombreDia().equals("sábado")) {
			return true;
		}
		return false;

	}

	private boolean alcanzoLitrosMinimosParaDescuento(double canLitros) {
		if (canLitros > minLitrosParaDescuento) {
			return true;
		}
		return false;
	}

	private String obtenerNombreDia() {
		LocalDate fechaActual = LocalDate.now();
		DayOfWeek dia = fechaActual.getDayOfWeek();
		String nombreDia = dia.getDisplayName(TextStyle.FULL, new Locale("ES"));
		return nombreDia;
	}

}
