package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public class CombustibleComun extends Combustible {

	private double descuentoDe_8a10_Am = 0.05;

	public CombustibleComun(double precioPorLitro, String descripcion, double descuentoDeMañana) {
		super(precioPorLitro, descripcion);
		this.descuentoDe_8a10_Am = descuentoDeMañana;
	}

	@Override
	public double calcularMontoAPagar(double cantLitros) {
		double montoSinDescuento = super.calcularMontoAPagar(cantLitros);
		double descuento = 0;
		double montoTotalAPagar = 0;
		if (esHorarioDeDescuento()) {
			descuento = montoSinDescuento * descuentoDe_8a10_Am;
		}
		montoTotalAPagar = montoSinDescuento - descuento;
		return montoTotalAPagar;
	}

	private boolean esHorarioDeDescuento() {
		LocalDateTime fechaActual = LocalDateTime.now();
		int hora = fechaActual.getHour();
		if (hora > 8 && hora < 10) {
			return true;
		}
		return false;

	}

}
