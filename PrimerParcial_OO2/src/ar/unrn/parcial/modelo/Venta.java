package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {

	private LocalDateTime fechaVenta;
	private double litrosCargados;
	private double montoFacturado;
	private String combustibleCargado;

	public Venta(LocalDateTime fechaVenta, double litrosCargados, Combustible combustibleCargado) {
		this.fechaVenta = fechaVenta;
		this.litrosCargados = litrosCargados;
		this.montoFacturado = combustibleCargado.calcularMontoAPagar(litrosCargados);
		this.combustibleCargado = combustibleCargado.obtenerDescripcion();
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
		String fechaVenta = obtenerFechaVenta().format(dtf);
		return fechaVenta + ";" + litrosCargados + ";" + montoFacturado + ";" + combustibleCargado;
	}

	public double obtenerMonto() {
		return this.montoFacturado;
	}

	public LocalDateTime obtenerFechaVenta() {
		return this.fechaVenta;
	}

	public double obtenerLitrosCargados() {
		return this.litrosCargados;
	}

	public String obtenerCombustibleCargado() {
		return this.combustibleCargado;
	}
}
