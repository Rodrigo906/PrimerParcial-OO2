package ar.unrn.parcial.modelo;

public abstract class Combustible {

	private double precioPorLitro;
	private String descripcion;

	public Combustible(double precioPorLitro, String descripcion) {
		this.precioPorLitro = precioPorLitro;
		this.descripcion = descripcion;
	}

	public double calcularMontoAPagar(double cantLitros) {
		return obtenerPrecioPorLitro() * cantLitros;
	}

	private double obtenerPrecioPorLitro() {
		return this.precioPorLitro;
	}

	public String obtenerDescripcion() {
		return this.descripcion;
	}

}
