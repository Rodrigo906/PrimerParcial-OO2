package ar.unrn.parcial.persistencia;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.RepositorioCombustibles;

public class RegistroCombustibleEnMemoria implements RepositorioCombustibles {

	private List<Combustible> combustibles = new ArrayList<Combustible>();

	@Override
	public void registrarCombustible(Combustible combustible) {
		combustibles.add(combustible);
	}

	@Override
	public Combustible obtenerCombustible(String descripcion) {
		for (Combustible c : combustibles) {
			if (c.obtenerDescripcion().equals(descripcion)) {
				return c;
			}
		}
		return null;
	}

}
