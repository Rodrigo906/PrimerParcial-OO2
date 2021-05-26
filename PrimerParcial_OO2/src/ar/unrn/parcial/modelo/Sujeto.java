package ar.unrn.parcial.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sujeto {

	private List<ObservadorVentas> observadores;

	public Sujeto() {
		observadores = new ArrayList<ObservadorVentas>();
	}

	public void agregarObservador(ObservadorVentas observador) {
		this.observadores.add(observador);
	}

	public void notificar(Map<String, String> datos) {
		for (ObservadorVentas obs : this.observadores) {
			obs.actualizar(datos);
		}
	}
}
