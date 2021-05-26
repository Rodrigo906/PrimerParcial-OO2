package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public abstract class Combustible {

	private double precioPorLitro;
	private String descripcion;
	private Fecha fecha;

	public Combustible(double precioPorLitro, String descripcion, Fecha fecha) {
		this.precioPorLitro = precioPorLitro;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Combustible() {

	}

	public double calcularMontoAPagar(String cantLitros) {
		try {
			return obtenerPrecioPorLitro() * Double.valueOf(cantLitros);
		} catch (NumberFormatException e) {
			throw new RuntimeException("No ingreso una cantidad de litros", e);
		}
	}

	private double obtenerPrecioPorLitro() {
		return this.precioPorLitro;
	}

	public String obtenerDescripcion() {
		return this.descripcion;
	}

	public LocalDateTime obtenerFecha() {
		return this.fecha.hoy();
	}

}
