package ar.unrn.parcial.modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class CombustibleSuper extends Combustible {

	private double descuentoDomingo;
	private double descuentoSabado;
	private double minLitrosParaDescuento;

	public CombustibleSuper(double precioPorLitro, String descripcion, double descuentoDomingo, double descuentoSabado,
			double minLitrosParaDescuento, Fecha fecha) {
		super(precioPorLitro, descripcion, fecha);
		this.descuentoDomingo = descuentoDomingo;
		this.descuentoSabado = descuentoSabado;
		this.minLitrosParaDescuento = minLitrosParaDescuento;
	}

	public CombustibleSuper() {
	}

	@Override
	public double calcularMontoAPagar(String cantLitros) {
		double montoAPagarSinDescuento = super.calcularMontoAPagar(cantLitros);
		double descuentoTotal = 0;
		double montoTotalAPagar = 0;
		if (esDomingo()) {
			descuentoTotal += montoAPagarSinDescuento * descuentoDomingo;
		}
		if (esSabadoYalcanzoLitrosMinimosParaDescuento(Double.valueOf(cantLitros))) {
			descuentoTotal += montoAPagarSinDescuento * descuentoSabado;
		}
		montoTotalAPagar = montoAPagarSinDescuento - descuentoTotal;
		return montoTotalAPagar;
	}

	private boolean esDomingo() {
		return obtenerNombreDia().equals("domingo");
	}

	private boolean esSabadoYalcanzoLitrosMinimosParaDescuento(double canLitros) {
		if (obtenerNombreDia().equals("sábado") && canLitros > minLitrosParaDescuento)
			return true;
		return false;

	}

	private String obtenerNombreDia() {
		LocalDateTime fechaActual = this.obtenerFecha();
		DayOfWeek dia = fechaActual.getDayOfWeek();
		String nombreDia = dia.getDisplayName(TextStyle.FULL, new Locale("ES"));
		return nombreDia;
	}

}
