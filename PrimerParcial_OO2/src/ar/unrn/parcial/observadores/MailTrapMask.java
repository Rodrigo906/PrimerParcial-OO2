package ar.unrn.parcial.observadores;

import java.util.Map;

import ar.unrn.parcial.modelo.ObservadorVentas;

public class MailTrapMask implements ObservadorVentas {

	private String mail;

	@Override
	public void actualizar(Map<String, String> datos) {
		this.mail = "mail enviado";
	}

	public String contenido() {
		return this.mail;
	}

}
