package ar.unrn.parcial.variables;

import java.time.LocalDateTime;

import ar.unrn.parcial.modelo.Fecha;

public class FechaImp implements Fecha {

	@Override
	public LocalDateTime hoy() {
		return LocalDateTime.now();
	}

}
