package ar.unrn.parcial.modelo;

public interface RepositorioCombustibles {

	public void registrarCombustible(Combustible combustible);

	public Combustible obtenerCombustible(String descripcion);

}
